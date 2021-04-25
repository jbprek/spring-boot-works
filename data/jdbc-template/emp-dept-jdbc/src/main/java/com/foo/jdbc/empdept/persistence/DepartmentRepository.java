package com.foo.jdbc.empdept.persistence;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;

@Repository
@AllArgsConstructor
public class DepartmentRepository {

    private JdbcTemplate jdbcTemplate;


    public <S extends Department> S create(S s) {
        jdbcTemplate.update(
                "insert into departments (id, name) values(?,?)",
                s.getId(), s.getName());
        return s;
    }

    private static class FindDepartmentStoredProcedure extends StoredProcedure {
        public FindDepartmentStoredProcedure(JdbcTemplate jdbcTemplate, String name) {
            super(jdbcTemplate, name);
            setFunction(false);
        }
    }

    public Optional<Department> findById(Integer deptId) {

        FindDepartmentStoredProcedure sproc = new FindDepartmentStoredProcedure(jdbcTemplate, "sproc_find_dept");
        SqlParameter inDeptId = new SqlParameter("in_id", Types.INTEGER);
        SqlOutParameter outDeptId = new SqlOutParameter("out_id", Types.INTEGER);
        SqlOutParameter outDeptName = new SqlOutParameter("out_name", Types.VARCHAR);
        sproc.setParameters(inDeptId, outDeptId, outDeptName);
        sproc.compile();

        var result = sproc.execute(inDeptId);
        if (result.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(Department.of((Integer) result.get("out_id"), (String) result.get("out_name")));
        }


//        return (Optional<Department>) jdbcTemplate.execute("call sproc_find_dept_name(?,?,?)",
//                new CallableStatementCallback() {
//                    public Optional<Department> doInCallableStatement(CallableStatement callableStatement) throws SQLException,
//                            DataAccessException {
//                        callableStatement.setInt(1, deptId);
//                        var rs = callableStatement.executeQuery();
//                        if (rs.next()) {
//                            Department d = new Department();
//                            d.setId(rs.getInt(1));
//                            d.setName(rs.getString(2));
//                            return Optional.of(d);
//                        } else {
//                            return Optional.empty();
//                        }
//                    }
//                }
//        );
    }

    public List<Department> findAll() {
        return jdbcTemplate.query(
                "select * from departments",
                new DepartmentRowMapper());
    }

    public long count() {
        return jdbcTemplate
                .queryForObject("select count(*) from departments", Long.class);
    }

//    public void  updateDepartmentName(int id, String newName) {
//        jdbcTemplate.update("call sproc_update_dept_name(?,?)", id, newName);
//
//    }

    public void deleteById(Integer id) {
        this.jdbcTemplate.update(
                "delete from departments where id = ?",
                id);
    }

    private final  RowMapper<EmployShortInfoDto> employShortInfoDtoRowMapper  = (rs,row) -> {
        EmployShortInfoDto repLine = new EmployShortInfoDto();
        repLine.setId(rs.getString("id"));
        repLine.setFirstName(rs.getString("first_name"));
        repLine.setLastName(rs.getString("last_name"));
        repLine.setHireDate(rs.getDate("hire_date").toLocalDate());
        return repLine;
    };


    public List<EmployShortInfoDto> findDepartmentEmployess(int deptId){
        return findDepartmentEmployessCallable(deptId);
    }


    // Does not work with HSQLDB
    public List<EmployShortInfoDto> findDepartmentEmployessJdbcCall(int deptId){
        return jdbcTemplate
                .query("call sproc_dept_emp_rep1(?)",
                        employShortInfoDtoRowMapper,
                        deptId);
    }


    //   Start Solution 2 extends StoredProcedure
    private static class DeptEmployessReportStoredProcedure extends StoredProcedure {
        public DeptEmployessReportStoredProcedure(JdbcTemplate jdbcTemplate) {
            super(jdbcTemplate, "sproc_dept_emp_rep1");
            setFunction(false);
            declareParameter(new SqlParameter("IN_DEPT_ID", Types.INTEGER));
            // HSQLDB this causes error
            //declareParameter(new SqlOutParameter("RESULT", Types.REF_CURSOR, new EmployShortInfoDtotRowMapper()));
            compile();
        }

        public List<EmployShortInfoDto>  execute(int dept_id) {
            Map<String, Object> inputs = new HashMap<>();
            inputs.put("IN_DEPT_ID", dept_id);

            Map<String, Object> outputs = super.execute(inputs);
            Optional<String> resultSetKey = outputs.entrySet().stream()
                    .filter(w->w.getKey().contains("result-set"))
                    .map(Map.Entry::getKey).findFirst();
            if (resultSetKey.isEmpty()) {
                return new ArrayList<EmployShortInfoDto>();
            } else {
                return (List<EmployShortInfoDto>)outputs.get(resultSetKey.get());
            }
        }
    }

    public List<EmployShortInfoDto> findDepartmentEmployessStoredProcedureAbstraction(int deptId) {
        DeptEmployessReportStoredProcedure sproc = new DeptEmployessReportStoredProcedure(jdbcTemplate);
        return (List<EmployShortInfoDto>) sproc.execute(deptId);
    }
    //   End Solution 2 extends StoredProcedure


    // Solution 3 callable statement
    public List<EmployShortInfoDto> findDepartmentEmployessCallable(int deptId) {
        return (List<EmployShortInfoDto>) jdbcTemplate
                .execute("call sproc_dept_emp_rep1(?)",
                        new CallableStatementCallback() {
                            public List<EmployShortInfoDto> doInCallableStatement(CallableStatement callableStatement) throws SQLException,
                                    DataAccessException {
                                callableStatement.setInt(1, deptId);
                                var rs = callableStatement.executeQuery();
                                List<EmployShortInfoDto> lst = new ArrayList<>();
                                while (rs.next()) {
                                    EmployShortInfoDto repLine = new EmployShortInfoDto();
                                    repLine.setId(rs.getString("id"));
                                    repLine.setFirstName(rs.getString("first_name"));
                                    repLine.setLastName(rs.getString("last_name"));
                                    repLine.setHireDate(rs.getDate("hire_date").toLocalDate());
                                    lst.add(repLine);
                                }
                                return lst;
                            }
                        }
                );
    }

    public static class OptionalDepartmentRowMapper implements RowMapper<Optional<Department>> {
        @Override
        public Optional<Department> mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Optional.of(Department.of(rs.getInt(1), rs.getString(2)));
        }
    }

    public static class DepartmentRowMapper implements RowMapper<Department> {
        @Override
        public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Department.of(rs.getInt(1), rs.getString(2));
        }
    }


}

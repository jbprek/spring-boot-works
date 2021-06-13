package data.jpa.relationships.m2ou.persistence;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;

@Repository
@AllArgsConstructor
public class DepartmentRepository {

    @Bean
    private JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        return jdbcTemplate;
    }

    @Autowired
    JdbcTemplate jdbcTemplate;


    private final RowMapper<EmployeeShortInfoDto> employShortInfoDtoRowMapper = (rs, row) -> {
        EmployeeShortInfoDto repLine = new EmployeeShortInfoDto();
        repLine.setId(rs.getString("id"));
        repLine.setFirstName(rs.getString("first_name"));
        repLine.setLastName(rs.getString("last_name"));
        repLine.setHireDate(rs.getDate("hire_date").toLocalDate());
        return repLine;
    };


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
        return findByIdSimple(deptId);
    }


    public Optional<Department> findByIdSimple(Integer deptId) {
        JdbcTemplate jdbc = new JdbcTemplate(jdbcTemplate.getDataSource());
        jdbc.setResultsMapCaseInsensitive(true);
        SimpleJdbcCall sproc = new SimpleJdbcCall(jdbc)
                .withProcedureName("SPROC_FIND_DEPT")
                .withoutProcedureColumnMetaDataAccess()
                .useInParameterNames("IN_DEPT_ID")
                .declareParameters(
                        new SqlParameter("IN_DEPT_ID", Types.INTEGER),
                        new SqlOutParameter("OUT_ID", Types.INTEGER),
                        new SqlOutParameter("OUT_NAME", Types.VARCHAR)
                )
                ;
        Map out = sproc.execute(deptId);
        Department d = Department.of((Integer) out.get("out_id"), (String) out.get("out_name"));
        return Optional.ofNullable(d);
    }


    public Optional<Department> findByIdTemplate(Integer deptId) {

        return (Optional<Department>)
        jdbcTemplate.execute(
//                (connection) -> {
//                    CallableStatement cs = connection.prepareCall("call sproc_find_dept(?,?,?)");
//                    cs.registerOutParameter(2, Types.INTEGER);
//                    cs.registerOutParameter(3, Types.VARCHAR);
//                    return cs;
//                }
//                new CallableStatementCreator() {
//                    @Override
//                    public CallableStatement createCallableStatement(Connection connection)
//                            throws SQLException {
//                        CallableStatement cs = connection.prepareCall("CALL SPROC_FIND_DEPT(?,?,?)");
//                        cs.registerOutParameter(2, Types.INTEGER);
//                        cs.registerOutParameter(3, Types.VARCHAR);
//                        return cs;
//                    }
//                },
                "CALL SPROC_FIND_DEPT(?,?,?)",
                new CallableStatementCallback() {
                    public Optional<Department> doInCallableStatement(CallableStatement cs) throws SQLException,
                            DataAccessException {
                        cs.registerOutParameter(2, Types.INTEGER);
                        cs.registerOutParameter(3, Types.VARCHAR);
                        cs.setInt(1, deptId);
                                var rs = cs.executeQuery();
                        if (rs.next()) {
                            Department d = new Department();
                            d.setId(rs.getInt(1));
                            d.setName(rs.getString(2));
                            return Optional.of(d);
                        } else {
                            return Optional.empty();
                        }
                    }
                }
        );
//
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


    public List<EmployeeShortInfoDto> findDepartmentEmployess(int deptId) {
        return findDepartmentEmployeesSimpleJdbc(deptId);
    }


    //  Solution 1 Does not work with HSQLDB
    public List<EmployeeShortInfoDto> findDepartmentEmployessJdbcCall(int deptId) {
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

        public List<EmployeeShortInfoDto> execute(int dept_id) {
            Map<String, Object> inputs = new HashMap<>();
            inputs.put("IN_DEPT_ID", dept_id);

            Map<String, Object> outputs = super.execute(inputs);
            Optional<String> resultSetKey = outputs.entrySet().stream()
                    .filter(w -> w.getKey().contains("result-set"))
                    .map(Map.Entry::getKey).findFirst();
            if (resultSetKey.isEmpty()) {
                return new ArrayList<>();
            } else {
                return (List<EmployeeShortInfoDto>) outputs.get(resultSetKey.get());
            }
        }
    }

    public List<EmployeeShortInfoDto> findDepartmentEmployessStoredProcedureAbstraction(int deptId) {
        DeptEmployessReportStoredProcedure sproc = new DeptEmployessReportStoredProcedure(jdbcTemplate);
        return (List<EmployeeShortInfoDto>) sproc.execute(deptId);
    }
    //   End Solution 2 extends StoredProcedure


    // Solution 3 callable statement
    public List<EmployeeShortInfoDto> findDepartmentEmployessCallable(int deptId) {
        return (List<EmployeeShortInfoDto>) jdbcTemplate
                .execute("call sproc_dept_emp_rep1(?)",
                        new CallableStatementCallback() {
                            public List<EmployeeShortInfoDto> doInCallableStatement(CallableStatement callableStatement) throws SQLException,
                                    DataAccessException {
                                callableStatement.setInt(1, deptId);
                                var rs = callableStatement.executeQuery();
                                List<EmployeeShortInfoDto> lst = new ArrayList<>();
                                while (rs.next()) {
                                    EmployeeShortInfoDto repLine = new EmployeeShortInfoDto();
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

    // Solution 4 SimpleJdbc - Look Best
    public List<EmployeeShortInfoDto> findDepartmentEmployeesSimpleJdbc(int deptId) {
        SimpleJdbcCall sproc = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sproc_dept_emp_rep1")
                .withoutProcedureColumnMetaDataAccess()
                .useInParameterNames("in_id")
                .declareParameters(
                        new SqlParameter("in_id", Types.INTEGER))
                .returningResultSet("result_set", employShortInfoDtoRowMapper);
        List<EmployeeShortInfoDto> out = (List<EmployeeShortInfoDto>) sproc.execute(deptId).get("result_set");
        return out;
    }


    public static class DepartmentRowMapper implements RowMapper<Department> {
        @Override
        public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Department.of(rs.getInt(1), rs.getString(2));
        }
    }


}

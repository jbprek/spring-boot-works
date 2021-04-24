package com.foo.jdbc.empdept.persistence;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Optional<Department> findById(Integer id) {
        return jdbcTemplate.queryForObject(
                "select * from departments where id = ?",
                new OptionalDepartmentRowMapper(), id);
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

    public <S extends Department> S update(S s) {
        jdbcTemplate.update(
                "update departments set name=? where id=?",
                s.getName(), s.getId());
        return s;
    }

    public void deleteById(Integer id) {
        this.jdbcTemplate.update(
                "delete from departments where id = ?",
                id);
    }


// Does not work with HSQLDB
//    public List<Employee> findDepartmentEmployess(int deptId){
//        return jdbcTemplate
//                .query("call department_employees(?)",
//                        (resultSet, rowNum) -> {
//                            Employee newActor = new Employee();
//                            newActor.setId(resultSet.getString("id"));
//                            newActor.setFirstName(resultSet.getString("first_name"));
//                            newActor.setLastName(resultSet.getString("last_name"));
//                            newActor.setHireDate(resultSet.getDate("hire_date").toLocalDate());
//                            return newActor;},
//                        deptId);
//    }

    public List<EmployShortInfoDto> findDepartmentEmployess(int deptId) {
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

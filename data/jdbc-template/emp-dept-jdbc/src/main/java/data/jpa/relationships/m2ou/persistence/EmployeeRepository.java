package data.jpa.relationships.m2ou.persistence;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
// TODO
@Repository
public class  EmployeeRepository {

    public <S extends Employee> S save(S s) {
        return null;
    }

    public Optional<Employee> findById(Integer integer) {
        return Optional.empty();
    }


    public List<Employee> findAll() {
        return null;
    }


    public long count() {
        return 0;
    }


    public void deleteById(Integer integer) {

    }
}

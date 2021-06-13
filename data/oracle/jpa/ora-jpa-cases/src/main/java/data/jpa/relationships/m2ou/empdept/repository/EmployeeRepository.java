package data.jpa.relationships.m2ou.empdept.repository;

import data.jpa.relationships.m2ou.empdept.entity.M2OUEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<M2OUEmployee, String> {
}

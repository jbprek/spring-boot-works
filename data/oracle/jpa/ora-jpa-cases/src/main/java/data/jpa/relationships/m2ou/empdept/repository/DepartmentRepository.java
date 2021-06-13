package data.jpa.relationships.m2ou.empdept.repository;

import data.jpa.relationships.m2ou.empdept.entity.M2OUDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public interface DepartmentRepository extends JpaRepository<M2OUDepartment, Integer> {

    @Transactional(readOnly = true,propagation = Propagation.REQUIRES_NEW)
    @Override
    Optional<M2OUDepartment> findById(Integer integer);

    @Transactional(readOnly = true,propagation = Propagation.REQUIRES_NEW)
    @Override
    List<M2OUDepartment> findAll();
}

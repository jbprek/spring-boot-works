package data.jpa.o2mb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public interface O2MBDepartmentRepository extends JpaRepository<Department, Integer> {

    @Transactional(readOnly = true,propagation = Propagation.REQUIRES_NEW)
    @Override
    Optional<Department> findById(Integer integer);

    @Transactional(readOnly = true,propagation = Propagation.REQUIRES_NEW)
    @Override
    List<Department> findAll();
}

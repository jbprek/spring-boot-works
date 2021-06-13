package data.jpa.o2mb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface O2MBEmployeeRepository extends JpaRepository<Employee, String> {
}

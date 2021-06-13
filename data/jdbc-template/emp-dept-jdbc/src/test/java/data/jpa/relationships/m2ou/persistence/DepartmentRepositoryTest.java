package data.jpa.relationships.m2ou.persistence;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Sql(scripts = {"/sql/schema.sql"}, config = @SqlConfig(separator = "^;") )
@Sql(scripts = {"/sql/data.sql"}, config = @SqlConfig(separator = ";"))
@Sql(scripts = {"/sql/cleanup.sql"}, config = @SqlConfig(separator = ";"), executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DepartmentRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private DepartmentRepository repository;

    @BeforeEach
    public void beforeEach() {
        repository = new DepartmentRepository(jdbcTemplate);
    }

    
    @Order(1)
    @Test
    public void testCount() {
        assertThat(repository.count()).isEqualTo(3);
    }



    @Order(2)
    @Test
    public void findEmployeeById() {
        Optional<Department> optDpt = repository.findById(1);
        assertThat(optDpt).isNotEmpty();
        assertThat(optDpt.get().getId()).isEqualTo(1);
        assertThat(optDpt.get().getName()).isEqualTo("IT");
    }

//    @Order(3)
//    @Test
//    public void findEmployeeByIdNotExists() {
//        Optional<Department> optDpt = repository.findById(9999);
//        assertThat(optDpt).isEmpty();
//    }

    

    @Order(4)
    @Test
    public void findAll() {
        List<Department> dptList = repository.findAll();
        assertThat(dptList).isNotEmpty().hasSize(3);
    }

    

    @Order(5)
    @Test
    void findDepartmentEmployess() {
        List<EmployeeShortInfoDto> dptList = repository.findDepartmentEmployess(1);
        assertThat(dptList).isNotEmpty().hasSize(2);
    }
//
//    @Order(6)
//    @Test
//    public void findSavedEmployeeById() {
//        var department = Department.of(4, "IT");
//        repository.create(department);
//        assertThat(repository.findById(department.getId())).hasValue(department);
//    }


//    @Order(6)
//    @Test
//    public void testUpdateDepartmentName() {
//        repository.updateDepartmentName(1, "Test New Name");
//        assertThat(repository.findById(1).get().getName()).isEqualTo("Test New Name");
//    }


//    @Test
//    public void findUpdateEmployeeById() {
//        var department = Department.of(1, "IT");
//        repository.save(department);
//        department.setName("IT Division+");
//        repository.save(department);
//        assertThat(repository.findById(department.getId())).hasValue(department);
//    }
//
//    @Test
//    public void checkDeletedEmployeeById() {
//        var department = Department.of(1, "IT");
//        repository.save(department);
//        repository.deleteById(1);
//        assertThat(repository.findById(1)).isEmpty();
//    }

}
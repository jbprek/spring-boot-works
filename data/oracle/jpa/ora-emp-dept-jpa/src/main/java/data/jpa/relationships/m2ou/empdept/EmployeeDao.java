package data.jpa.relationships.m2ou.empdeptdao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EmployeeDao {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private MapStructMapper mapStructMapper;

    public List<DepartmentDto> getAllDepartments() {
        return mapStructMapper.fromDepartmentEntityList(departmentRepository.findAll());
    }

    public Optional<DepartmentDto> findDepartment(Integer id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (optionalDepartment.isEmpty()){
            return Optional.empty();
        }
        return Optional.ofNullable(mapStructMapper.fromEntity(optionalDepartment.get()));
    }

}

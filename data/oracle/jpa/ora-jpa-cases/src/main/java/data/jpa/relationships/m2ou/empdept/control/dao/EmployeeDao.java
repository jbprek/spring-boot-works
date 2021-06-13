package data.jpa.relationships.m2ou.empdept.control.dao;

import data.jpa.relationships.m2ou.empdept.entity.M2OUDepartment;
import data.jpa.relationships.m2ou.empdept.repository.DepartmentRepository;
import data.jpa.relationships.m2ou.empdept.repository.EmployeeRepository;
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
        return mapStructMapper.departmentEntityList2DtoList(departmentRepository.findAll());
    }

    public Optional<DepartmentDto> findDepartment(Integer id) {
        Optional<M2OUDepartment> optionalDepartment = departmentRepository.findById(id);
        if (optionalDepartment.isEmpty()){
            return Optional.empty();
        }
        return Optional.ofNullable(mapStructMapper.departmentEntity2Dto(optionalDepartment.get()));
    }

}

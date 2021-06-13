package data.jpa.relationships.m2ou.empdeptdao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DepartmentDao {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private MapStructMapper mapStructMapper;

    public List<DepartmentDto> getAllDepartments() {
        List<Department> entityList = departmentRepository.findAll();
        List<DepartmentDto> dtoList =  mapStructMapper.fromDepartmentEntityList(entityList);
        return dtoList;
    }

    public Optional<DepartmentDto> findDepartment(Integer id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (optionalDepartment.isEmpty()){
            return Optional.empty();
        }
        return Optional.ofNullable(mapStructMapper.fromEntity(optionalDepartment.get()));
    }

}

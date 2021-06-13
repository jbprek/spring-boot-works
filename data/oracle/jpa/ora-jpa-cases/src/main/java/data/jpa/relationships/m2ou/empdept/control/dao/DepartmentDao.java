package data.jpa.relationships.m2ou.empdept.control.dao;

import data.jpa.relationships.m2ou.empdept.entity.M2OUDepartment;
import data.jpa.relationships.m2ou.empdept.repository.DepartmentRepository;
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
        List<M2OUDepartment> entityList = departmentRepository.findAll();
        List<DepartmentDto> dtoList =  mapStructMapper.departmentEntityList2DtoList(entityList);
        return dtoList;
    }

    public Optional<DepartmentDto> findDepartment(Integer id) {
        Optional<M2OUDepartment> optionalDepartment = departmentRepository.findById(id);
        if (optionalDepartment.isEmpty()){
            return Optional.empty();
        }
        return Optional.ofNullable(mapStructMapper.departmentEntity2Dto(optionalDepartment.get()));
    }

}

package data.jpa.relationships.m2ou.empdept.control.dao;

import data.jpa.relationships.m2ou.empdept.entity.M2OUDepartment;
import data.jpa.relationships.m2ou.empdept.entity.M2OUEmployee;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MapStructMapper {
    DepartmentDto departmentEntity2Dto(M2OUDepartment source);
    List<DepartmentDto> departmentEntityList2DtoList(List<M2OUDepartment> source);
    EmployeeDto employeeEntity2Dto(M2OUEmployee source);
    List<EmployeeDto> employeeEntityList2DtoList(List<EmployeeDto> source);

}

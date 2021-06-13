package data.jpa.relationships.m2ou.empdeptdao;

import data.jpa.relationships.m2ou.empdeptdao.DepartmentDto;
import data.jpa.relationships.m2ou.empdeptdao.EmployeeDto;
import data.jpa.relationships.m2ou.empdeptdao.Department;
import data.jpa.relationships.m2ou.empdeptdao.Employee;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MapStructMapper {
    DepartmentDto fromEntity(Department source);
    List<DepartmentDto> fromDepartmentEntityList(List<Department> source);
    EmployeeDto fromEntity(Employee source);
    List<EmployeeDto> fromEmployeeEntityList(List<EmployeeDto> source);

}

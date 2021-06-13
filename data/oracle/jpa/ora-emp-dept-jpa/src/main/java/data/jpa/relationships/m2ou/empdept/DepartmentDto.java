package data.jpa.relationships.m2ou.empdeptdao;

import lombok.Data;

//TODO @Value com.fasterxml.jackson.databind.exc.InvalidDefinitionException: No serializer found for class
// @Builder
@Data
public class DepartmentDto {
    private Integer id;
    private String name;

}

package Mapstruct.mapper;

import Mapstruct.Dto.PersonDefaultDTO;
import Mapstruct.Entities.Person;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {
    PersonMapper INSTACE = Mappers.getMapper(PersonMapper.class);

    PersonDefaultDTO personToPersonDefaultDTO(Person person);
}
package Mapstruct;

import Mapstruct.Dto.PersonDefaultDTO;
import Mapstruct.Entities.Person;
import Mapstruct.mapper.PersonMapper;


public class Main {
    public static void main(String[] args) {
        Person person = new Person(1L,"Carlos","Ramirez","carlos@correo.com",(byte) 32,'M');
        System.out.println(person);

        PersonDefaultDTO personDefaultDTO = PersonMapper.INSTACE.personToPersonDefaultDTO(person);
        System.out.println(personDefaultDTO);
    }
}

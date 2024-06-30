package OrikaMapper;

import OrikaMapper.Dto.PersonCustomDTO;
import OrikaMapper.Entities.Person;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class main {
    //* Orika Mapper - Custom *//
    public static void main(String[] args) {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(Person.class, PersonCustomDTO.class)
                .field("id", "idDTO")
                .field("name", "nameDTO")
                .field("lasname", "lasNameDTO")
                .field("email","emailDTO")
                .field("age","ageDTO")
                .field("gender","genderDTO")
                .byDefault()
                .register();

        MapperFacade mapperFacade = mapperFactory.getMapperFacade();

        Person person = new Person(1L,"Carlos","Ramirez","carlos@correo.com",(byte) 32,'M');
        System.out.println(person);

        PersonCustomDTO personCustomDTO = mapperFacade.map(person, PersonCustomDTO.class);
        System.out.println(personCustomDTO);
    }
}

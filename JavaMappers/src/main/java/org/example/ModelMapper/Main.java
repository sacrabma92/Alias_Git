package org.example.ModelMapper;


import org.example.ModelMapper.Dto.PersonCustomDTO;
import org.example.ModelMapper.Dto.PersonDefaultDTO;
import org.example.ModelMapper.Entities.Person;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

public class Main {

   /* Model Mapper- Custom */
   public static void main(String[] args) {
      ModelMapper modelMapper = new ModelMapper();

      TypeMap<Person, PersonCustomDTO> propertyMapper = modelMapper.createTypeMap(Person.class, PersonCustomDTO.class);
      propertyMapper.addMapping(Person::getId, PersonCustomDTO::setIdDTO);
      propertyMapper.addMapping(Person::getName, PersonCustomDTO::setNameDTO);
      propertyMapper.addMapping(Person::getLasName, PersonCustomDTO::setLasNameDTO);
      propertyMapper.addMapping(Person::getEmail, PersonCustomDTO::setEmailDTO);
      propertyMapper.addMapping(Person::getGender, PersonCustomDTO::setGenderDTO);

      Person person = new Person(1L,"Carlos","Ramirez","carlos@correo.com",(byte) 32,'M');
      System.out.println(person);

      PersonCustomDTO personCustomDTO = propertyMapper.map(person);
      System.out.println(personCustomDTO);
   }
}
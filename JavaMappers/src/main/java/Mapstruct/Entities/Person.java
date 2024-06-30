package Mapstruct.Entities;

public class Person {
   private Long id;
   private String name;
   private String lasName;
   private String email;
   private Byte age;
   private Character gender;

   public Person() {
   }

   @Override
   public String toString() {
      return "Person{" +
              "id=" + id +
              ", name='" + name + '\'' +
              ", lasName='" + lasName + '\'' +
              ", email='" + email + '\'' +
              ", age=" + age +
              ", gender=" + gender +
              '}';
   }

   public Person(Long id, String name, String lasName, String email, Byte age, Character gender) {
      this.id = id;
      this.name = name;
      this.lasName = lasName;
      this.email = email;
      this.age = age;
      this.gender = gender;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getLasName() {
      return lasName;
   }

   public void setLasName(String lasName) {
      this.lasName = lasName;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public Byte getAge() {
      return age;
   }

   public void setAge(Byte age) {
      this.age = age;
   }

   public Character getGender() {
      return gender;
   }

   public void setGender(Character gender) {
      this.gender = gender;
   }
}

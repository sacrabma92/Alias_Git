package Mapstruct.Dto;

public class PersonCustomDTO {
   private Long idDTO;
   private String nameDTO;
   private String lasNameDTO;
   private String emailDTO;
   private Byte ageDTO;
   private Character genderDTO;

   @Override
   public String toString() {
      return "PersonCustomDTO{" +
              "idDTO=" + idDTO +
              ", nameDTO='" + nameDTO + '\'' +
              ", lasNameDTO='" + lasNameDTO + '\'' +
              ", emailDTO='" + emailDTO + '\'' +
              ", ageDTO=" + ageDTO +
              ", genderDTO=" + genderDTO +
              '}';
   }

   public PersonCustomDTO() {

   }
   public PersonCustomDTO(Long idDTO, String nameDTO, String lasNameDTO, String emailDTO, Byte ageDTO, Character genderDTO) {
      this.idDTO = idDTO;
      this.nameDTO = nameDTO;
      this.lasNameDTO = lasNameDTO;
      this.emailDTO = emailDTO;
      this.ageDTO = ageDTO;
      this.genderDTO = genderDTO;
   }

   public Long getIdDTO() {
      return idDTO;
   }

   public void setIdDTO(Long idDTO) {
      this.idDTO = idDTO;
   }

   public String getNameDTO() {
      return nameDTO;
   }

   public void setNameDTO(String nameDTO) {
      this.nameDTO = nameDTO;
   }

   public String getLasNameDTO() {
      return lasNameDTO;
   }

   public void setLasNameDTO(String lasNameDTO) {
      this.lasNameDTO = lasNameDTO;
   }

   public String getEmailDTO() {
      return emailDTO;
   }

   public void setEmailDTO(String emailDTO) {
      this.emailDTO = emailDTO;
   }

   public Byte getAgeDTO() {
      return ageDTO;
   }

   public void setAgeDTO(Byte ageDTO) {
      this.ageDTO = ageDTO;
   }

   public Character getGenderDTO() {
      return genderDTO;
   }

   public void setGenderDTO(Character genderDTO) {
      this.genderDTO = genderDTO;
   }
}

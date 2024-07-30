package com.cursos.api.springsecuritycourse.persistence.entity;

import com.cursos.api.springsecuritycourse.persistence.entity.security.GrantedPermission;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Role {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String name;

   @OneToMany(mappedBy = "role")
   private List<GrantedPermission> permission;

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

   public List<GrantedPermission> getPermissions() {
      return permission;
   }

   public void setPermissions(List<GrantedPermission> permissions) {
      this.permission = permissions;
   }
}

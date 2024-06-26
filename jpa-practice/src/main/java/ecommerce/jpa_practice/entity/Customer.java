
package ecommerce.jpa_practice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    private Long id;
    
    private String firstName;
    
    private String lastName;
}

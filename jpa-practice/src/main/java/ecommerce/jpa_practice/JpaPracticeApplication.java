package ecommerce.jpa_practice;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaPracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaPracticeApplication.class, args);
                //EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-practice");
                //EntityManager em = factory.createEntityManager();
	}

}

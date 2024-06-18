package com.api.hateoas;

import com.api.hateoas.model.Cuenta;
import com.api.hateoas.repository.CuentaRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@Rollback(value =  true)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CuentaRepositoryTests {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Test
    void testAgregarCuenta(){
        Cuenta cuenta = new Cuenta(12,"10");
        Cuenta cuentaGuardada = cuentaRepository.save(cuenta);

        Assertions.assertThat(cuentaGuardada).isNotNull(); // Comprobamos que la cuenta no sea null
        Assertions.assertThat(cuentaGuardada.getId()).isGreaterThan(0); // Comprabamos que el id sea mayor que 0
    }
}

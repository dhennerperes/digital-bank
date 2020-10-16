package com.zup.dhennerperes.digitalbank.repository;

import com.zup.dhennerperes.digitalbank.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

    @Transactional(readOnly = true)
    Optional<Persona> findByCpf(String cpf);

}

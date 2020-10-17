package com.zup.dhennerperes.digitalbank.repository;

import com.zup.dhennerperes.digitalbank.model.PersonaAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface PersonaAddressRepository extends JpaRepository<PersonaAddress, Long> {

    @Transactional(readOnly = true)
    Optional<PersonaAddress> findByPersonaId(Long personaId);

}

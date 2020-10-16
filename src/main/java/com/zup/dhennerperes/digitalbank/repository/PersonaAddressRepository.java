package com.zup.dhennerperes.digitalbank.repository;

import com.zup.dhennerperes.digitalbank.model.PersonaAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaAddressRepository extends JpaRepository<PersonaAddress, Long> {

}

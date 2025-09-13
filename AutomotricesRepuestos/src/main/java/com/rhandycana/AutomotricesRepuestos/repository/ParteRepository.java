package com.rhandycana.AutomotricesRepuestos.repository;

import com.rhandycana.AutomotricesRepuestos.model.Parte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParteRepository extends JpaRepository<Parte, Long> {
}

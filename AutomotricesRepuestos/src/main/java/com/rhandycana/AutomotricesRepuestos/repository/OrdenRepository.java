package com.rhandycana.AutomotricesRepuestos.repository;

import com.rhandycana.AutomotricesRepuestos.model.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Long> {
}

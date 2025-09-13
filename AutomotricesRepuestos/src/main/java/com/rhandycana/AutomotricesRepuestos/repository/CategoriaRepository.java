package com.rhandycana.AutomotricesRepuestos.repository;

import com.rhandycana.AutomotricesRepuestos.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}

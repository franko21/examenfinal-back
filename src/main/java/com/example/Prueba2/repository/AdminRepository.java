package com.example.Prueba2.repository;

import com.example.Prueba2.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Administrador,Long> {
    Optional<Administrador> findByUsuario(String usuario);

}

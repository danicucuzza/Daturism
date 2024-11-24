package com.daturism.taller3.Repository;

import com.daturism.taller3.Model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAdministradorRepository extends JpaRepository<Administrador, Long> {
}

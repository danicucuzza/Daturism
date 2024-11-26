package com.daturism.taller3.Repository;

import com.daturism.taller3.Model.Paquete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPaqueteRepository extends JpaRepository<Paquete, Long> {

}

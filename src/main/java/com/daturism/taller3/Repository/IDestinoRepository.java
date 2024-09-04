package com.daturism.taller3.Repository;

import com.daturism.taller3.Model.Destino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDestinoRepository extends JpaRepository<Destino, Long> {

}

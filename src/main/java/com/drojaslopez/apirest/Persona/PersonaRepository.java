package com.drojaslopez.apirest.Persona;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, String> {

    @Query(value="SELECT rut, nombre,apellido,edad FROM Persona p WHERE p.nombre = :nombre",nativeQuery = true)
    Persona findByNombre(@Param ("nombre") String nombre);


}

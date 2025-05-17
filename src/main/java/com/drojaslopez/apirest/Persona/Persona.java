package com.drojaslopez.apirest.Persona;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor


@Entity
public class Persona {

    @Id
    private String rut;

    private String nombre;
    private String apellido;
    private int edad;

   

}

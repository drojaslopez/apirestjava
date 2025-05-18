package com.drojaslopez.apirest.Persona;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/persona")
@RequiredArgsConstructor
public class PersonaController {

    private final PersonaService personaService;

    @PostMapping("/crear")
    public void crearPersona(@RequestBody Persona persona) {
        try {
            personaService.savePersona(persona);
            System.out.println("Persona creada: " + persona);
        } catch (Exception e) {
            System.out.println("Error al crear persona: " + e.getMessage());
        }







        
    }
    
    @GetMapping("/listar")
    public Iterable<Persona> listarPersonas() {
        try {           
            return personaService.getAllPersonas();
        } catch (Exception e) {
            System.out.println("Error al listar personas: " + e.getMessage());}
            return null;
            
        }

    @GetMapping("/{rut}")
    public ResponseEntity<Persona> obtenerPersonaPorRut(@PathVariable String rut) {
        try {
            Persona persona = personaService.getPersonaById(rut);
            if (persona == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(persona);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    

}

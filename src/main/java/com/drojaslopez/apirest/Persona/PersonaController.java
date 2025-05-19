package com.drojaslopez.apirest.Persona;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/persona")
@RequiredArgsConstructor
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping("/")
    public String welcome() {
        return "Bienvenido a la API de Personas";
    }

    @PostMapping("/crear")
    public ResponseEntity<Persona> crearPersona(@RequestBody Persona persona) {

        // se puede limpiar los datos para evitar inyecciones o errores
        if (personaService.getPersonaById(persona.getRut()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        try {
            personaService.savePersona(persona);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<Iterable<Persona>> listarPersonas() {
        try {
            Iterable<Persona> personas = personaService.getAllPersonas();
            return ResponseEntity.ok(personas);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{rut}")
    public ResponseEntity<Persona> obtenerPersonaPorRut(@PathVariable String rut) {
        // se puede limpiar los datos para evitar inyecciones o errores
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
    @DeleteMapping("/{rut}")
    public ResponseEntity<Void> eliminarPersona(@PathVariable String rut) {
        try {
            if (personaService.getPersonaById(rut) == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            personaService.deletePersona(rut);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

   

    @PutMapping("/actualizar")
    public ResponseEntity<Persona> actualizarPersona(@RequestBody Persona persona) {
        try {
            if (personaService.getPersonaById(persona.getRut()) == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            personaService.updatePersona(persona.getRut(), persona);
            return ResponseEntity.ok(persona);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

     @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Persona> obtenerPersonaPorNombre(@PathVariable String nombre) {
        try {
            Persona persona = personaService.findByNombre(nombre);            
            return ResponseEntity.ok(persona);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}

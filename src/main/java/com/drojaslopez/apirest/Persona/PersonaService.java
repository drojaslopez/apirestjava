package com.drojaslopez.apirest.Persona;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonaService {
    private final PersonaRepository personaRepository;

    

    public Iterable<Persona> getAllPersonas() {
        return personaRepository.findAll();
    }

    public Persona getPersonaById(String rut) {
        return personaRepository.findById(rut).orElse(null);
    }

    public Persona savePersona(Persona persona) {
        return personaRepository.save(persona);
    }

    public void deletePersona(String rut) {
        personaRepository.deleteById(rut);
    }

    public Persona updatePersona(String rut, Persona persona) {
        if (personaRepository.existsById(rut)) {
            persona.setNombre(persona.getNombre());
            persona.setApellido(persona.getApellido());
            persona.setEdad(persona.getEdad());
            return personaRepository.save(persona);
        }
        return null;
    }

    public Persona findByNombre(String nombre) {
        return personaRepository.findByNombre(nombre);
    }


}

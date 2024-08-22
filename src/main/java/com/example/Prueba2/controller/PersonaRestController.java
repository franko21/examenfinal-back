package com.example.Prueba2.controller;

import com.example.Prueba2.model.Persona;
import com.example.Prueba2.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:4200","http://137.184.65.180:9096","http://10.0.2.2:8080"})
@RestController
@RequestMapping("/api")
public class PersonaRestController {
    @Autowired
    private PersonaRepository personaRepository;

    @GetMapping("/persona")
    public List<Persona> Listarpersonas(){
        return personaRepository.findAll();
    }

    @GetMapping("/persona/{id}")
    public ResponseEntity<Persona> buscar(@PathVariable Long id) {
        Optional<Persona> persona = personaRepository.findById(id);
        if (persona.isPresent()) {
            return ResponseEntity.ok(persona.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/persona")
    public ResponseEntity<Persona> crear(@RequestBody Persona body) {
        Persona nuevoPersona= personaRepository.save(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPersona);
    }

    @PutMapping("/persona/{id}")
    public ResponseEntity<Persona> actualizar(@RequestBody Persona body, @PathVariable Long id) {
        Optional<Persona> persona = personaRepository.findById(id);
        if (persona.isPresent()) {
            Persona cambios = persona.get();
            cambios.setNombre(body.getNombre());
            cambios.setApellido(body.getApellido());
            cambios.setCedula(body.getCedula());
            cambios.setUbicacion(body.getUbicacion());
            return ResponseEntity.ok(personaRepository.save(cambios));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/persona/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (personaRepository.existsById(id)) {
            personaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

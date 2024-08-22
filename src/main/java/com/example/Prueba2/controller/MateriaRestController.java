package com.example.Prueba2.controller;

import com.example.Prueba2.model.Materia;
import com.example.Prueba2.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:4200","http://137.184.65.180:9096","http://10.0.2.2:8080"})
@RestController
@RequestMapping("/api")
public class MateriaRestController {
    @Autowired
    private MateriaRepository materiaRepository;

    @GetMapping("/materia")
    public List<Materia> Listarmateria(){
        return materiaRepository.findAll();
    }

    @GetMapping("/materia/{id}")
    public ResponseEntity<Materia> buscar(@PathVariable Long id) {
        Optional<Materia> materia = materiaRepository.findById(id);
        if (materia.isPresent()) {
            return ResponseEntity.ok(materia.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/materia")
    public ResponseEntity<Materia> crear(@RequestBody Materia body) {
        Materia nuevoMateria= materiaRepository.save(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoMateria);
    }

    @PutMapping("/materia/{id}")
    public ResponseEntity<Materia> actualizar(@RequestBody Materia body, @PathVariable Long id) {
        Optional<Materia> materia = materiaRepository.findById(id);
        if (materia.isPresent()) {
            Materia cambios = materia.get();
            cambios.setNombre(body.getNombre());
            return ResponseEntity.ok(materiaRepository.save(cambios));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/materia/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (materiaRepository.existsById(id)) {
            materiaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

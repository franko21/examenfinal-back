package com.example.Prueba2.controller;

import com.example.Prueba2.model.Empleado;
import com.example.Prueba2.model.Marca;
import com.example.Prueba2.repository.EmpleadoRepository;
import com.example.Prueba2.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class MarcaRestController {
    @Autowired
    private MarcaRepository marcaRepository;

    @GetMapping("/marca")
    public List<Marca> Listarmarcas(){
        return marcaRepository.findAll();
    }

    @GetMapping("/marca/{id}")
    public ResponseEntity<Marca> buscar(@PathVariable Long id) {
        Optional<Marca> marca = marcaRepository.findById(id);
        if (marca.isPresent()) {
            return ResponseEntity.ok(marca.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/marca")
    public ResponseEntity<Marca> crear(@RequestBody Marca body) {
        Marca nuevoMarca = marcaRepository.save(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoMarca);
    }

    @PutMapping("/marca/{id}")
    public ResponseEntity<Marca> actualizar(@RequestBody Marca body, @PathVariable Long id) {
        Optional<Marca> marca = marcaRepository.findById(id);
        if (marca.isPresent()) {
            Marca cambios = marca.get();
            cambios.setNombre(body.getNombre());
            return ResponseEntity.ok(marcaRepository.save(cambios));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/marca/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (marcaRepository.existsById(id)) {
            marcaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

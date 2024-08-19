package com.example.Prueba2.controller;

import com.example.Prueba2.model.Marca;
import com.example.Prueba2.model.Vehiculo;
import com.example.Prueba2.repository.MarcaRepository;
import com.example.Prueba2.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class VehiculoRestController {
    @Autowired
    private VehiculoRepository vehiculoRepository;

    @GetMapping("/vehiculo")
    public List<Vehiculo> Listarvehiculo(){
        return vehiculoRepository.findAll();
    }

    @GetMapping("/vehiculo/{id}")
    public ResponseEntity<Vehiculo> buscar(@PathVariable Long id) {
        Optional<Vehiculo> vehiculo = vehiculoRepository.findById(id);
        if (vehiculo.isPresent()) {
            return ResponseEntity.ok(vehiculo.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/vehiculo")
    public ResponseEntity<Vehiculo> crear(@RequestBody Vehiculo body) {
        Vehiculo nuevoVehiculo = vehiculoRepository.save(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoVehiculo);
    }

    @PutMapping("/vehiculo/{id}")
    public ResponseEntity<Vehiculo> actualizar(@RequestBody Vehiculo body, @PathVariable Long id) {
        Optional<Vehiculo> vehiculo = vehiculoRepository.findById(id);
        if (vehiculo.isPresent()) {
            Vehiculo cambios = vehiculo.get();
            cambios.setModelo(body.getModelo());
            cambios.setAnio(body.getAnio());
            cambios.setDescripcion(body.getDescripcion());
            cambios.setPrecio(body.getPrecio());
            cambios.setEstado(body.getEstado());
            return ResponseEntity.ok(vehiculoRepository.save(cambios));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/vehiculo/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (vehiculoRepository.existsById(id)) {
            vehiculoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

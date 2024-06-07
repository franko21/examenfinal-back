package com.example.Prueba2.controller;

import com.example.Prueba2.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.Prueba2.model.Empleado;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EmpleadoRestController {
    @Autowired
    private EmpleadoRepository empleadoRepository;

    @GetMapping("/empleado")
    public List<Empleado> Listarempleados(){
        return empleadoRepository.findAll();
    }

    @GetMapping("/empleado/{id}")
    public ResponseEntity<Empleado> buscar(@PathVariable Long id) {
        Optional<Empleado> empleado = empleadoRepository.findById(id);
        if (empleado.isPresent()) {
            return ResponseEntity.ok(empleado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/empleado")
    public ResponseEntity<Empleado> crear(@RequestBody Empleado body) {
        body.calcularSueldo();
        Empleado nuevoEmpleado = empleadoRepository.save(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEmpleado);
    }

    @PutMapping("/empleado/{id}")
    public ResponseEntity<Empleado> actualizar(@RequestBody Empleado body, @PathVariable Long id) {
        Optional<Empleado> empleado = empleadoRepository.findById(id);
        if (empleado.isPresent()) {
            Empleado cambios = empleado.get();
            cambios.setNombre(body.getNombre());
            cambios.setApellido(body.getApellido());
            cambios.setDireccion(body.getDireccion());
            cambios.setObservacion(body.getObservacion());
            cambios.setFecha_nacimiento(body.getFecha_nacimiento());
            cambios.setDias_trabajo(body.getDias_trabajo());
            cambios.setTelefono(body.getTelefono());
            cambios.calcularSueldo();
            return ResponseEntity.ok(empleadoRepository.save(cambios));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/empleado/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (empleadoRepository.existsById(id)) {
            empleadoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

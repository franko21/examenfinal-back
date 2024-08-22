package com.example.Prueba2.controller;

import com.example.Prueba2.model.Administrador;
import com.example.Prueba2.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:4200","http://137.184.65.180:9096","http://10.0.2.2:8080"})
@RestController
@RequestMapping("/api")
public class AdminRestController {
    @Autowired
    private AdminRepository adminRepository;

    @GetMapping("/admin")
    public List<Administrador> Listaradmin(){
        return adminRepository.findAll();
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<Administrador> buscar(@PathVariable Long id) {
        Optional<Administrador> admin = adminRepository.findById(id);
        if (admin.isPresent()) {
            return ResponseEntity.ok(admin.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/admin/{usuario}/{clave}")
    public boolean buscar(@PathVariable String usuario,@PathVariable String clave) {
        Optional<Administrador> admin = adminRepository.findByUsuario(usuario);
        if (admin.isPresent()) {
            if (admin.get().getClave().equals(clave)){
                return true;
            }else{
                return false;
            }
        } else {
            return false;
        }
    }
    @PostMapping("/admin")
    public ResponseEntity<Administrador> crear(@RequestBody Administrador body) {
        Administrador nuevoAdmin = adminRepository.save(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoAdmin);
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<Administrador> actualizar(@RequestBody Administrador body, @PathVariable Long id) {
        Optional<Administrador> admin = adminRepository.findById(id);
        if (admin.isPresent()) {
            Administrador cambios = admin.get();
            cambios.setUsuario(body.getUsuario());
            cambios.setClave(body.getClave());
            return ResponseEntity.ok(adminRepository.save(cambios));
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}

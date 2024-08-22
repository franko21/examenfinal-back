package com.example.Prueba2.data;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.example.Prueba2.repository.AdminRepository;
import com.example.Prueba2.model.Administrador;




@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private AdminRepository adminRepository;


    @Override
    public void run(String... args) throws Exception {

        //CREAR USUARIO

        String username = "admin";
        String password = "123";

        Administrador usuario = adminRepository.findByUsuario(username).orElse(null);
        if (usuario == null) {
                // Crear y guardar la nueva Persona
                    Administrador admin=new Administrador();
                    admin.setUsuario(username);
                    admin.setClave(password);
                    adminRepository.save(admin);

        } else {
            System.out.println("El usuario con el nombre de usuario '" + username + "' ya existe.");
        }

    }
}

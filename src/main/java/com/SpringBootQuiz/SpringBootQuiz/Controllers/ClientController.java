package com.SpringBootQuiz.SpringBootQuiz.Controllers;

import com.SpringBootQuiz.SpringBootQuiz.Models.Client;
import com.SpringBootQuiz.SpringBootQuiz.Repositories.ClientRepository;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientController {
    private final ClientRepository repository;

    ClientController(ClientRepository repository) {
        this.repository = repository;
    }

    // return all clients in the system
    @GetMapping("/clients")
    Object all() {
        try {
            return repository.findAll();
        }catch (Exception e ){
            return e.getMessage() ;
        }
    }

    @PostMapping("/clients")
    Object newClient(@RequestBody Client newClient) {
        try {
            return repository.save(newClient);
        }catch (Exception e ){
            return e.getMessage() ;
        }
    }

    // Single item
    @GetMapping("/clients/{id}")
    Object getClientByID(@PathVariable Long id) {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Could not find client : " + id .toString()));
        }catch (Exception e ){
            return e.getMessage() ;
        }
    }

    @PutMapping("/clients/{id}")
    Object replaceClient(@RequestBody Client newClient, @PathVariable Long id) {
        try {
            return repository.findById(id)
                    .map(client -> {
                        client.setName(newClient.getName());
                        client.setLastName(newClient.getLastName());
                        client.setMobile(newClient.getMobile());
                        return repository.save(client);
                    })
                    .orElseThrow(() -> new RuntimeException("Could not find client : " + id .toString()));
        }catch (Exception e ){
            return e.getMessage() ;
        }
    }

    @DeleteMapping("/clients/{id}")
    void deleteClient(@PathVariable Long id) {
        repository.deleteById(id);
    }
}

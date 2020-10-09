package com.SpringBootQuiz.SpringBootQuiz.Clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientController {
    @Autowired
    private ClientService ClientService;

    // return all clients in the system
    @GetMapping("/Clients")
    Object all() {
        try {
            return ClientService.all();
        }catch (Exception e ){
            return e.getMessage() ;
        }
    }

    @PostMapping("/Clients")
    Object newClient(@RequestBody Client newClient) {
        try {
            return ClientService.newClient(newClient);
        }catch (Exception e ){
            return e.getMessage() ;
        }
    }

    // Single item
    @GetMapping("/Clients/{id}")
    Object getClientByID(@PathVariable Long id) {
        try {
            return ClientService.getClientByID(id);
        }catch (Exception e ){
            return e.getMessage() ;
        }
    }

    @PutMapping("/Clients/{id}")
    Object updateClient(@RequestBody Client newClient, @PathVariable Long id) {
        try {
            return ClientService.updateClient(newClient,id);
        }catch (Exception e ){
            return e.getMessage() ;
        }
    }

    @DeleteMapping("/Clients/{id}")
    Object deleteClient(@PathVariable Long id) {
        try {
            return ClientService.deleteClient(id);
        }catch (Exception e ){
            return e.getMessage() ;
        }
    }
}

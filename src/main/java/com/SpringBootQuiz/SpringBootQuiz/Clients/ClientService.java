package com.SpringBootQuiz.SpringBootQuiz.Clients;

import org.springframework.stereotype.Service;

@Service
public class ClientService {
    private final ClientRepository repository;

    ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    // return all clients in the system
    public Object all() {
        try {
            return repository.findAll();
        }catch (Exception e ){
            return e.getMessage() ;
        }
    }

    public Object newClient(Client newClient) {
        try {
            return repository.save(newClient);
        }catch (Exception e ){
            return e.getMessage() ;
        }
    }

    public Object getClientByID(Long id) {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Could not find client : " + id .toString()));
        }catch (Exception e ){
            return e.getMessage() ;
        }
    }

    public Object updateClient(Client newClient,Long id) {
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

    public Object deleteClient(Long id) {
        try {
            repository.deleteById(id);
            return true ;
        }catch (Exception e){
            return e.getMessage() ;
        }
    }
}

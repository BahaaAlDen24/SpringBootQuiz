package com.SpringBootQuiz.SpringBootQuiz.Repositories;

import com.SpringBootQuiz.SpringBootQuiz.Models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository  extends JpaRepository<Client , Long> {
}

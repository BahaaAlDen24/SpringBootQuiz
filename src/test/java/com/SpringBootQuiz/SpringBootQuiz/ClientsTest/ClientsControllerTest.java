package com.SpringBootQuiz.SpringBootQuiz.ClientsTest;

import com.SpringBootQuiz.SpringBootQuiz.Clients.Client;
import com.SpringBootQuiz.SpringBootQuiz.Clients.ClientController;
import com.SpringBootQuiz.SpringBootQuiz.Clients.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = ClientController.class)
@ActiveProfiles("test")
public class ClientsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    private List<Client> clientList;

    @BeforeEach
    void setUp() {
        this.clientList = new ArrayList<>();
        this.clientList.add(new Client(1L, "Client 1", "Client 1", "0935067850"));
        this.clientList.add(new Client(2L, "Client 1", "Client 1", "0935067850"));
        this.clientList.add(new Client(3L, "Client 1", "Client 1", "0935067850"));
    }

    @Test
    void testGetAllClients() throws Exception{
        given(clientService.all()).willReturn(clientList);
        this.mockMvc.perform(get("/Clients"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.size()",is(clientList.size())));
    }

    @Test
    void testGetClientsByID() throws Exception{
        final Long ClientID = 1L ;
        final Client client = new Client(1L, "Client 1", "Client 1", "0935067850");

        given(clientService.getClientByID(ClientID)).willReturn(Optional.of(client));

        this.mockMvc.perform(get("/Clients/{id}",ClientID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is(client.getName())))
                .andExpect(jsonPath("$.lastName",is(client.getLastName())))
                .andExpect(jsonPath("$.mobile",is(client.getMobile())));
    }
}
package ua.com.alevel.Finance.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.com.alevel.Finance.persistence.entity.Client;
import ua.com.alevel.Finance.persistence.repository.ClientRepository;

import java.util.Collection;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClientServiceTest {

    private static final String FULL_NAME = "FULL NAME FOR TEST";
    private static final String EMAIL = "test@email.com";

    private static final Long ID1 = 1L;
    private static Client client1;

    @Autowired
    private ClientRepository clientRepository;

    @BeforeAll
    public static void setUp() {
        client1 = new Client();
        client1.setId(ID1);
        client1.setFullName(FULL_NAME);
        client1.setEmail(EMAIL);
    }

    @Test
    @Order(1)
    void shouldBeCreateClient() {
        clientRepository.save(client1);
        Assertions.assertNotNull(client1.getId());
    }

    @Test
    @Order(2)
    void shouldBeUpdateClient() {
        clientRepository.save(client1);
        Assertions.assertEquals(client1.getId(), clientRepository.findById(ID1).get().getId());
    }

    @Test
    @Order(3)
    void shouldFindClientById() {
        clientRepository.save(client1);
        Optional<Client> foundClient = clientRepository.findById(ID1);
        Client expectedClient = foundClient.orElse(null);
        Assertions.assertNotNull(expectedClient);
    }

    @Test
    @Order(4)
    void shouldFindAllClients() {
        clientRepository.save(client1);
        Collection<Client> foundClients = clientRepository.findAll();
        Assertions.assertNotNull(foundClients);
    }

    @Test
    @Order(5)
    void shouldBeDeleteClient() {
        clientRepository.save(client1);
        clientRepository.deleteById(ID1);
        Assertions.assertFalse(clientRepository.findById(ID1).isPresent());
    }
}

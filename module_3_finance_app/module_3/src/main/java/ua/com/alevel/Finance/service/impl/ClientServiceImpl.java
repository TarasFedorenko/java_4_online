package ua.com.alevel.Finance.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.Finance.persistence.entity.Client;
import ua.com.alevel.Finance.persistence.repository.ClientRepository;
import ua.com.alevel.Finance.service.ClientService;

import java.util.Collection;
import java.util.Optional;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public Client findById(Long id) {
        Optional<Client> foundClient = clientRepository.findById(id);
        return foundClient.orElse(null);
    }

    @Override
    public Collection<Client> findAll() {
        return clientRepository.findAll();
    }

    @Transactional
    @Override
    public void create(Client client) {
        clientRepository.save(client);
    }

    @Transactional
    @Override
    public void update(Long id, Client updatedClient) {
        updatedClient.setId(id);
        clientRepository.save(updatedClient);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }


}

package pl.sda.carrental.service;

import com.mysql.cj.xdevapi.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.carrental.ObjectNotFoundInRepositoryException;
import pl.sda.carrental.model.BranchModel;
import pl.sda.carrental.model.ClientModel;
import pl.sda.carrental.repository.BranchRepository;
import pl.sda.carrental.repository.ClientRepository;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final BranchRepository branchRepository;

    public ClientModel findById(Long id) {
            return clientRepository.findById(id)
                    .orElseThrow(() -> new ObjectNotFoundInRepositoryException("Client not found"));
    }

    public List<ClientModel> getAllClients() {
        return clientRepository.findAll();
    }

    public void addClient(ClientModel client) {
        clientRepository.save(client);
    }

    public ClientModel editClient(Long id, ClientModel client) {
        ClientModel found = clientRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundInRepositoryException("No client under that ID!"));

        found.setName(client.getName());
        found.setSurname(client.getSurname());
        found.setEmail(client.getEmail());
        found.setAddress(client.getAddress());

        clientRepository.deleteById(id);

        return clientRepository.save(found);

    }

    public void removeClient(Long id) {
        clientRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundInRepositoryException("No client under that ID!"));
        clientRepository.deleteById(id);
    }

    public void assignClientToBranch(Long clientId, Long branchId) {
        ClientModel foundClient = clientRepository.findById(clientId)
                .orElseThrow(() -> new ObjectNotFoundInRepositoryException("No client under ID #" + clientId));
        if(foundClient.getBranch() != null) {
            throw new RuntimeException("This client is already assigned to existing branch!");
        }
        BranchModel foundBranch = branchRepository.findById(branchId)
                .orElseThrow(() -> new ObjectNotFoundInRepositoryException("No branch under ID #" + clientId));

        foundBranch.getClients().add(foundClient);
        foundClient.setBranch(foundBranch);

        branchRepository.save(foundBranch);
        clientRepository.save(foundClient);
    }

    public void removeClientFromBranch(Long clientId, Long branchId) {
        BranchModel foundBranch = branchRepository.findById(branchId)
                .orElseThrow(() -> new ObjectNotFoundInRepositoryException("No branch under ID #" + branchId));
        ClientModel foundClient = foundBranch.getClients().stream()
                .filter(client -> Objects.equals(client.getClient_id(), clientId))
                .findFirst()
                .orElseThrow(() ->
                        new ObjectNotFoundInRepositoryException("No client under ID #"
                        + clientId + " is assigned to branch under ID #" + branchId));

        foundBranch.getClients().remove(foundClient);
        foundClient.setBranch(null);

        branchRepository.save(foundBranch);
        clientRepository.save(foundClient);
    }
}

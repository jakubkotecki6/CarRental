package pl.sda.carrental.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.carrental.exceptionHandling.ObjectAlreadyAssignedToBranchException;
import pl.sda.carrental.exceptionHandling.ObjectNotFoundInRepositoryException;
import pl.sda.carrental.model.Branch;
import pl.sda.carrental.model.Client;
import pl.sda.carrental.repository.BranchRepository;
import pl.sda.carrental.repository.ClientRepository;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final BranchRepository branchRepository;

    /**
     * Retrieves a client by their unique ID.
     *
     * @param id The unique identifier of the client to retrieve.
     * @return The Client object corresponding to the provided ID.
     * @throws ObjectNotFoundInRepositoryException if no client is found with the specified ID.
     */
    public Client findById(Long id) {
            return clientRepository.findById(id)
                    .orElseThrow(() -> new ObjectNotFoundInRepositoryException("Client not found"));
    }

    /**
     * Retrieves a list containing all clients available in the repository.
     *
     * @return A list containing all Client objects available in the repository.
     */
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    /**
     * Adds a new client to the repository.
     *
     * @param client The Client object representing the new client to be added.
     */
    public void addClient(Client client) {
        clientRepository.save(client);
    }

    /**
     * Updates the details of a client identified by the provided ID.
     * Retrieves the client with the given ID from the repository or throws an exception if not found.
     * Updates client details with the provided information.
     * Deletes the previous version of the client and saves the updated client in the repository.
     *
     * @param id     The ID of the client to be updated.
     * @param client The Client object containing the updated information for the client.
     * @return The updated Client object.
     * @throws ObjectNotFoundInRepositoryException if no client is found under the provided ID.
     */
    public Client editClient(Long id, Client client) {
        Client found = clientRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundInRepositoryException("No client under that ID!"));

        found.setName(client.getName());
        found.setSurname(client.getSurname());
        found.setEmail(client.getEmail());
        found.setAddress(client.getAddress());

        clientRepository.deleteById(id);

        return clientRepository.save(found);

    }

    /**
     * Checks if a client with the given ID exists in the repository, or throws an exception if not found.
     * Removes a client identified by the provided ID from the repository.
     *
     * @param id The ID of the client to be removed.
     * @throws ObjectNotFoundInRepositoryException if no client is found under the provided ID.
     */
    public void removeClient(Long id) {
        clientRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundInRepositoryException("No client under that ID!"));
        clientRepository.deleteById(id);
    }

    /**
     * Assigns a client to a branch based on their respective IDs.
     * Retrieves the client with the given ID from the repository or throws an exception if not found.
     * Checks if the client is already assigned to a branch, throws an exception if already assigned.
     * Retrieves the branch with the given ID from the repository or throws an exception if not found.
     * Assigns the client to the branch and updates their association in the repositories.
     *
     * @param clientId  The ID of the client to be assigned to a branch.
     * @param branchId  The ID of the branch to which the client will be assigned.
     * @throws ObjectNotFoundInRepositoryException        if no client or branch is found under the provided IDs.
     * @throws ObjectAlreadyAssignedToBranchException    if the client is already assigned to an existing branch.
     */
    public void assignClientToBranch(Long clientId, Long branchId) {
        Client foundClient = clientRepository.findById(clientId)
                .orElseThrow(() -> new ObjectNotFoundInRepositoryException("No client under ID #" + clientId));
        if(foundClient.getBranch() != null) {
            throw new ObjectAlreadyAssignedToBranchException("This client is already assigned to existing branch!");
        }
        Branch foundBranch = branchRepository.findById(branchId)
                .orElseThrow(() -> new ObjectNotFoundInRepositoryException("No branch under ID #" + clientId));

        foundBranch.getClients().add(foundClient);
        foundClient.setBranch(foundBranch);

        branchRepository.save(foundBranch);
        clientRepository.save(foundClient);
    }

    /**
     * Removes a specific client from a branch based on their respective IDs.
     * The client is dissociated from the branch, and the branch-client association is updated in the repositories.
     *
     * @param clientId  The ID of the client to be removed from the branch.
     * @param branchId  The ID of the branch from which the client will be removed.
     * @throws ObjectNotFoundInRepositoryException if no branch or client is found under the provided IDs.
     */
    public void removeClientFromBranch(Long clientId, Long branchId) {
        Branch foundBranch = branchRepository.findById(branchId)
                .orElseThrow(() -> new ObjectNotFoundInRepositoryException("No branch under ID #" + branchId));
        Client foundClient = foundBranch.getClients().stream()
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

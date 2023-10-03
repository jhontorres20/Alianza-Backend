package com.alianza.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alianza.entity.Client;
import com.alianza.exception.ResourceNotFoundException;
import com.alianza.repository.ClientRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Transactional
public class ClientImpl implements IClient {

	@Autowired
	ClientRepository clientRepository;

	ObjectMapper objMapper = new ObjectMapper();

	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	@Override
	public List<Client> consultClients() {
		return clientRepository.findAll();
	}

	@Override
	public Client saveClient(Client client) {
		return clientRepository.save(client);
	}

	@Override
	public Client loadClientById(Long idClient) {
		return clientRepository.findById(idClient)
				.orElseThrow(() -> new ResourceNotFoundException("No existe el client con el ID : " + idClient));
	}

	@Override
	public Client updateClientById(Long idClient, Client detailClient) {
		Client client = clientRepository.findById(idClient)
			.orElseThrow(() -> new ResourceNotFoundException("No existe el client con el ID : " + idClient));
			
		client.setSharedKey(detailClient.getSharedKey());
		client.setBusinessId(detailClient.getBusinessId());
		client.setEmail(detailClient.getEmail());
		client.setPhone(detailClient.getPhone());		

		return clientRepository.save(client);		
	}

	@Override
	public Map<String, Boolean> deleteClientById(Long idClient) {
		Client client = clientRepository.findById(idClient)
				.orElseThrow(() -> new ResourceNotFoundException("No existe el client con el ID : " + idClient));
		clientRepository.delete(client);
		Map<String, Boolean> response = new HashMap<>();
		response.put("ELIMINATE", Boolean.TRUE);
		return response;
	}

}

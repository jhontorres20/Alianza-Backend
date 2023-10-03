package com.alianza.business;

import java.util.List;
import java.util.Map;

import com.alianza.entity.Client;

public interface IClient {
	
	List<Client> consultClients();
	
	Client saveClient(Client client);
	
	Client loadClientById(Long idClient);
	
	Client updateClientById(Long idClient, Client detailClient);

	Map<String, Boolean> deleteClientById(Long idClient);
}

package com.alianza.business;

import java.util.List;
import java.util.Map;

import com.alianza.dto.RequestClientDTO;
import com.alianza.dto.ResponseClientDTO;
import com.alianza.entity.Client;

public interface IClient {
	
	List<ResponseClientDTO> consultClients();
	
	Client saveClient(RequestClientDTO client);
	
	Client loadClientById(Long idClient);
	
	Client updateClientById(Long idClient, Client detailClient);

	Map<String, Boolean> deleteClientById(Long idClient);
	
	List<ResponseClientDTO> advancedSearchClient(String searchFields);
}
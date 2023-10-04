package com.alianza.business;

import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alianza.dto.RequestClientDTO;
import com.alianza.dto.ResponseClientDTO;
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
	ModelMapper modelMapper = new ModelMapper();

	private static final String DATE_FORMAT = "yyyy-MM-dd";
	
	private final static Logger logger = LoggerFactory.getLogger(ClientImpl.class);

	@Override
	public List<ResponseClientDTO> consultClients() {
		logger.info("Consultando listado de clientes");
		List<Client> listClients = clientRepository.findAll();
		List<ResponseClientDTO> listResponseClient = new ArrayList<>();
		
		for(Client tempClient : listClients) {
			ResponseClientDTO responseClientDTO = new ResponseClientDTO();
			responseClientDTO.setId(tempClient.getId());
			responseClientDTO.setBusinessId(tempClient.getBusinessId());
			responseClientDTO.setDataAdded(tempClient.getDataAdded());
			responseClientDTO.setEmail(tempClient.getEmail());
			responseClientDTO.setPhone(tempClient.getPhone());
			responseClientDTO.setSharedKey(tempClient.getSharedKey());
			listResponseClient.add(responseClientDTO);
		}
		logger.info("Termina la consulta de clientes");
		return listResponseClient;
	}

	@Override
	public Client saveClient(RequestClientDTO client) {
		logger.info("Guardando cliente");
		Date dateAddedNew = null;
		try {
			dateAddedNew = new SimpleDateFormat(DATE_FORMAT).parse(client.getDataAdded());
		} catch (ParseException e) {
			logger.error("Error en el formato de fechas {} ", e.getMessage());
			e.printStackTrace();
		} 
		
		Client newClient = new Client();
		newClient.setSharedKey(client.getSharedKey());
		newClient.setBusinessId(client.getBusinessId());
		newClient.setEmail(client.getEmail());
		newClient.setPhone(client.getPhone());	
		newClient.setDataAdded(dateAddedNew);
		
		Client clientNew = null;
		try {
			clientNew = clientRepository.save(newClient);
			logger.info("Cliente guardado correctamente");
		} catch (Exception e) {
			logger.error("Error al guardar un nuevo cliente {} ",e.getMessage());
		}		
		return clientNew;
	}

	@Override
	public Client loadClientById(Long idClient) {
		return clientRepository.findById(idClient)
				.orElseThrow(() -> new ResourceNotFoundException("No existe el client con el ID : " + idClient));
	}

	@Override
	public Client updateClientById(Long idClient, Client detailClient) {
		logger.info("Inicia la actualizacion del cliente id -> {}",detailClient.getId());
		Client client = null;
		try {
			client = clientRepository.findById(idClient)
					.orElseThrow(() -> new ResourceNotFoundException("No existe el client con el ID : " + idClient));
					
				client.setSharedKey(detailClient.getSharedKey());
				client.setBusinessId(detailClient.getBusinessId());
				client.setEmail(detailClient.getEmail());
				client.setPhone(detailClient.getPhone());
				client = clientRepository.save(client);
		} catch (Exception e) {
			logger.error("Error en la actualización del cliente {} ", e.getMessage());
		}		
		return client;	
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

	@Override
	public List<ResponseClientDTO> advancedSearchClient(String searchFields) {		
		//List<Client> listClient = clientRepository.searchAdvanceByClients(sharedKey, sharedKey, email, phone, dataAdded);
		logger.info("Preparando busqueda avanzada ");
		List<Client> listClient = clientRepository.searchAdvanceByClients(searchFields);
		List<ResponseClientDTO> responseSearchClient = null;
		
		if(!listClient.isEmpty()) {			
			responseSearchClient = listClient
					  .stream()
					  .map(searchClient -> modelMapper.map(searchClient, ResponseClientDTO.class))
					  .collect(Collectors.toList());			
			logger.info("Termina busqueda avanzada ");
		}else {
			logger.info("No se encontraron datos ");
		}
		return responseSearchClient;
	}

}

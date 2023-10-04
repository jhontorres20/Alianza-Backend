package com.alianza.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alianza.business.IClient;
import com.alianza.dto.RequestClientDTO;
import com.alianza.dto.ResponseClientDTO;
import com.alianza.entity.Client;

/**
 * Clase que contiene los microservicios para la gestion clientes
 * @author JhonCTorres
 */
@RestController
@RequestMapping("/api/alianza/v1/")
@CrossOrigin(origins = "http://localhost:4200")
public class ClientController {
	
	@Autowired
	private IClient iClient;	

	// este servicio sirve para listar todos los clients
	@GetMapping("/client/listClients")
	public List<ResponseClientDTO> listClients() {
		List<ResponseClientDTO> response = iClient.consultClients();
		return response;
	}

	// este servicio sirve para guardar el client
	@PostMapping("/client/saveClient")
	public Client clientSave(@RequestBody RequestClientDTO client) {
		return iClient.saveClient(client);
	}

	// este servicio sirve para buscar un client
	@GetMapping("/client/{id}")
	public ResponseEntity<Client> consultClientById (@PathVariable Long id) {
		Client client = iClient.loadClientById(id);
		return ResponseEntity.ok(client);
	}

	// este servicio sirve para actualizar client
	@PutMapping("/client/{id}")
	public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client detailClient) {
		Client clientUpdate = iClient.updateClientById(id, detailClient);		
		return ResponseEntity.ok(clientUpdate);
	}

	// este servicio sirve para eliminar un client
	@DeleteMapping("/client/{id}")
	public ResponseEntity<Map<String, Boolean>> eliminarclient(@PathVariable Long id) {
		Map<String, Boolean> response = iClient.deleteClientById(id);
		return ResponseEntity.ok(response);
	}
	
	// este servicio sirve para realizar una busqueda avanzada de un cliente
	/*@GetMapping("client/searchAdvancedClient")
	public ResponseEntity<List<ResponseClientDTO>> searchClient (@RequestParam(value = "sharedKey", required = false) String sharedKey, 
			@RequestParam(value = "businessId", required = false) String businessId, 
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "phone", required = false) String phone, 
			@RequestParam(value = "dataAdded", required = false) String dataAdded) {
		List<ResponseClientDTO> responseClientDTO = iClient.advancedSearchClient(sharedKey, email, phone, dataAdded);
		return ResponseEntity.ok(responseClientDTO);
	}*/
	
	// este servicio sirve para realizar una busqueda avanzada de un cliente
		@GetMapping("client/searchAdvancedClient")
		public ResponseEntity<List<ResponseClientDTO>> searchClient (@RequestParam(value = "searchFields", required = false) String searchFields) {
			List<ResponseClientDTO> responseClientDTO = iClient.advancedSearchClient(searchFields);
			return ResponseEntity.ok(responseClientDTO);
		}
	
}

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
import org.springframework.web.bind.annotation.RestController;

import com.alianza.business.IClient;
import com.alianza.entity.Client;

@RestController
@RequestMapping("/api/alianza/v1/")
@CrossOrigin(origins = "http://localhost:4200")
public class ClientController {
	
	@Autowired
	private IClient iClient;	

	// este metodo sirve para listar todos los clients
	@GetMapping("client/listClients")
	public List<Client> listClients() {
		return iClient.consultClients();
	}

	// este metodo sirve para guardar el client
	@PostMapping("client/saveClient")
	public Client clientSave(@RequestBody Client client) {
		return iClient.saveClient(client);
	}

	// este metodo sirve para buscar un client
	@GetMapping("/client/{id}")
	public ResponseEntity<Client> consultClientById (@PathVariable Long id) {
		Client client = iClient.loadClientById(id);
		return ResponseEntity.ok(client);
	}

	// este metodo sirve para actualizar client
	@PutMapping("/client/{id}")
	public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client detailClient) {
		Client clientUpdate = iClient.updateClientById(id, detailClient);		
		return ResponseEntity.ok(clientUpdate);
	}

	// este metodo sirve para eliminar un client
	@DeleteMapping("/client/{id}")
	public ResponseEntity<Map<String, Boolean>> eliminarclient(@PathVariable Long id) {
		Map<String, Boolean> response = iClient.deleteClientById(id);
		return ResponseEntity.ok(response);
	}
}

package com.alianza.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alianza.business.IClientExport;

import jakarta.servlet.http.HttpServletResponse;

/**
 * Clase que contiene los microservicios para exportar listado de clientes
 * @author JhonCTorres
 */
@RestController
@RequestMapping("/api/alianza/v1/")
@CrossOrigin(origins = "http://localhost:4200")
public class ClientExportController {
	
	@Autowired
	private IClientExport iClientExport;	

	// este servicio sirve para exportar listado de clientes
	@GetMapping("/client/exportListClients")
	public void listClients(HttpServletResponse servletResponse) throws IOException {		
		servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"Listado_Clientes.csv\"");
        iClientExport.exportClients(servletResponse.getWriter());
	}
	
}

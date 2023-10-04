package com.alianza.business;

import java.io.IOException;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
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
public class ClientExportImpl implements IClientExport {

	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	ClientImpl clientImpl;

	ObjectMapper objMapper = new ObjectMapper();
	
	private final static Logger logger = LoggerFactory.getLogger(ClientExportImpl.class);

	@Override
	public void exportClients(Writer writer) {
		
		List<ResponseClientDTO> listClients = clientImpl.consultClients();
		
		logger.info("Exportando listado de clientes a CSV");
		try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("ID", "BUSINESS_ID" ,"SHARED_KEY", "E-MAIL","PHONE","DATA_ADDED");
            for (ResponseClientDTO tempListClients : listClients) {
                csvPrinter.printRecord(tempListClients.getId(), tempListClients.getSharedKey(), 
                		tempListClients.getBusinessId(), tempListClients.getEmail(), tempListClients.getPhone(),
                		tempListClients.getDataAdded());
            }
        } catch (IOException e) {
            logger.error("Error generando CSV {} ", e);
        }
		logger.info("Termina la exportación de clientes a CSV");
	}
}

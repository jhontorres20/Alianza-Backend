package com.alianza;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.alianza.business.ClientImpl;
import com.alianza.dto.RequestClientDTO;
import com.alianza.entity.Client;
import com.alianza.repository.ClientRepository;

@SpringBootTest
class AlianzaFiduciariaValoresApplicationTests {
	
	
	@Mock
	private ClientRepository clientRepository;
	
	@InjectMocks
	private ClientImpl clientImpl;
	
	private Client mockClient;
	
	private RequestClientDTO mockRequestClientDTO;
	
	@BeforeEach
	void setUp() {		
		String date = "03-10-2023";  
	    Date newDate = null;
		try {
			newDate = new SimpleDateFormat("YYYY-MM-DD").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}  	
		mockClient = new Client();
		mockClient.setId(1L);
		mockClient.setSharedKey("jtorres");
		mockClient.setBusinessId("Jhon Torres");
		mockClient.setEmail("jhonc1991@hotmail.com");
		mockClient.setPhone("312547891");
		mockClient.setDataAdded(newDate);
		
		mockRequestClientDTO = new RequestClientDTO();
		mockRequestClientDTO.setSharedKey("atorres");
		mockRequestClientDTO.setBusinessId("Amaia Torres");
		mockRequestClientDTO.setEmail("amaia@hotmail.com");
		mockRequestClientDTO.setPhone("40125874");		
		mockRequestClientDTO.setDataAdded(date);
	}	

	/**
	 * Test method for {@link com.alianza.business.ClientImpl#consultClients()}.
	 */
	@Test
	public void testConsultClients() {
		when(clientRepository.findAll()).thenReturn(Arrays.asList(mockClient));		
		assertNotNull(clientImpl.consultClients());
	}

	/**
	 * Test method for {@link com.alianza.business.ClientImpl#saveClient(com.alianza.dto.RequestClientDTO)}.
	 */
	@Test
	public void testSaveClient() {
		//when(clientRepository.save(any())).thenReturn(Arrays.asList(mockClient));
		when(clientRepository.save(any(Client.class))).thenReturn(mockClient);
		assertNotNull(clientImpl.saveClient(mockRequestClientDTO));
	}
}

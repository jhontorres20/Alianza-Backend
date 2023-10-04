package com.alianza.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.alianza.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

	/*@Query(value = "select C.* from clientes C "
			+ "where "
			+ "(?1 IS NULL OR C.shared_key = ?1) AND "
			+ "(?2 IS NULL OR C.business_id = ?2) AND "
			+ "(?3 IS NULL OR C.email = ?3) AND "
			+ "(?4 IS NULL OR C.phone = ?4) AND "
			+ "(?5 IS NULL OR C.data_added = ?5) ", nativeQuery = true)
	List<Client> searchAdvanceByClients(String sharedKey, String businessId, String email,
			String phone, String dataAdded);*/
	
	@Query(value = "select c from Client c "
			+ "WHERE "
			+ "CONCAT(c.sharedKey, c.businessId, c.email, c.phone, c.dataAdded) "			
			+ "LIKE %?1% ")
	List<Client> searchAdvanceByClients(String searchWord);
}

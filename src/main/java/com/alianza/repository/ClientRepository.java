package com.alianza.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alianza.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

}

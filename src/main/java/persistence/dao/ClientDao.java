package persistence.dao;

import java.util.List;

import persistence.entities.Client;

public interface ClientDao extends GlobalDao<Client> {
	
	List<Client> findByNom(String nomclient);
	List<Client> findByPrenom(String prenomclient);
	
}

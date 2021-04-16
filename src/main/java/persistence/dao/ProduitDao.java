package persistence.dao;

import java.util.List;

import persistence.entities.Produit;

public interface ProduitDao extends GlobalDao<Produit> {
	
	List<Produit> findByDesignation(String designation);
	List<Produit> findByMarque(String marque);
}

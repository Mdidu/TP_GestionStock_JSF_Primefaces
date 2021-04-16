package view.managedBean;

import java.math.BigDecimal;
import java.util.ArrayList;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import persistence.dao.ProduitDao;
import persistence.dao.ProduitDaoImpl;
import persistence.entities.Produit;

@ManagedBean
@SessionScoped
public class ProduitMBean {
	
	private Produit produit = new Produit();
	private Produit selectedProduit = new Produit();
	ProduitDao produitdao = new ProduitDaoImpl();
	private List<Produit> listProduit = new ArrayList<Produit>();
	private String valeurRecherche;
	private String critereRecherche;
	

	public ProduitMBean() {
		listProduit = produitdao.findAll();
	}
	
	public String getValeurRecherche() {
		return valeurRecherche;
	}


	public void setValeurRecherche(String valeurRecherche) {
		this.valeurRecherche = valeurRecherche;
	}


	public String getCritereRecherche() {
		return critereRecherche;
	}


	public void setCritereRecherche(String critereRecherche) {
		this.critereRecherche = critereRecherche;
	}


	public Produit getProduit() {
		return produit;
	}


	public void setProduit(Produit produit) {
		this.produit = produit;
	}


	public Produit getSelectedProduit() {
		return selectedProduit;
	}


	public void setSelectedProduit(Produit selectedProduit) {
		this.selectedProduit = selectedProduit;
	}


	public List<Produit> getListProduit() {
		return listProduit;
	}


	public void setListProduit(List<Produit> listProduit) {
		this.listProduit = listProduit;
	}


	public void addProduit(ActionEvent e) {
		produitdao.add(produit);
		produit = new Produit();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ajout effectué avec succés"));
	}
	public void deleteProduit(ActionEvent e) {
		if(selectedProduit==null  || selectedProduit.getIdproduit()== new BigDecimal(0))
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Attention", "Aucun produit sélectionné"));
		else {
			produitdao.delete(selectedProduit);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Suppression effectué avec succés"));
		}
	}
	
	public String editProduit() {
		if(selectedProduit==null  || selectedProduit.getIdproduit()== new BigDecimal(0)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Attention", "Aucun produit sélectionné"));
			return "showProduit.xhtml";
		} else 
		return "editProduit.xhtml";
	}
	public void updateProduit(ActionEvent e) {
		produitdao.update(selectedProduit);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Modification effectué avec succés"));
	}
	
	public void renvoi(ActionEvent e) {
    	if (critereRecherche.equalsIgnoreCase("0")||critereRecherche==null)
    		this.listProduit = produitdao.findAll();
    	else if (critereRecherche.equalsIgnoreCase("1")) 
    		this.listProduit = produitdao.findByDesignation(valeurRecherche);
    	else if (critereRecherche.equalsIgnoreCase("2")) 
    		this.listProduit = produitdao.findByMarque(valeurRecherche);
    	critereRecherche=null;
    	valeurRecherche=null;
    }
}

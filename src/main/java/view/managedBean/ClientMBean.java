package view.managedBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import persistence.dao.ClientDao;
import persistence.dao.ClientDaoImpl;
import persistence.entities.Client;
import persistence.entities.Role;

@ManagedBean
@SessionScoped
public class ClientMBean {

	private Client client = new Client();
	private Client selectedClient = new Client();
	ClientDao clientDao = new ClientDaoImpl();
	private List<Client> listClient = new ArrayList<Client>();
	private String valeurRecherche;
	private String critereRecherche;

	public ClientMBean() {
		this.listClient = clientDao.findAll();
	}

	public String getCritereRecherche() {
		return critereRecherche;
	}

	public void setCritereRecherche(String text2) {
		this.critereRecherche = text2;
	}

	public void renvoi(ActionEvent e) {
		if (critereRecherche.equalsIgnoreCase("none") || critereRecherche == null)
			this.listClient = clientDao.findAll();
		
		else if (critereRecherche.equalsIgnoreCase("nom"))
			this.listClient = clientDao.findByNom(valeurRecherche);
		
		else if (critereRecherche.equalsIgnoreCase("prenom"))
			this.listClient = clientDao.findByPrenom(valeurRecherche);
		
		critereRecherche = null;
		valeurRecherche = null;
	}

	public String getValeurRecherche() {
		return valeurRecherche;
	}

	public void setValeurRecherche(String valeurRecherche) {
		this.valeurRecherche = valeurRecherche;
	}

	public Client getSelectedClient() {
		return selectedClient;
	}

	public void setSelectedClient(Client selectedClient) {
		this.selectedClient = selectedClient;
	}

	public List<Client> getListClient() {
		return listClient;
	}

	public void setListClient(List<Client> listClient) {
		this.listClient = listClient;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void addClient(ActionEvent e) {
		Role role = new Role();
		role.setIdrole(new BigDecimal(2));
		client.getStockuser().setRole(role);
		clientDao.add(client);
		client = new Client();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ajout effectué avec succès"));
	}

	public void deleteClient(ActionEvent e) {
		if (selectedClient == null || selectedClient.getIdclient() == new BigDecimal(0))
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Attention", "Aucun  client sélectionné"));
		else {
			clientDao.delete(selectedClient);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Suppression effectué avec succès"));
		}
	}

	public String editClient() {
		return "editClient.xhtml";
	}

	public void updateClient(ActionEvent e) {
		clientDao.update(selectedClient);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Modification effectué avec succès"));
	}
}

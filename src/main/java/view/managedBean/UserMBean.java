package view.managedBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import persistence.dao.UserDao;
import persistence.dao.UserDaoImpl;
import persistence.entities.Role;
import persistence.entities.Stockuser;

@ManagedBean
@SessionScoped
public class UserMBean {

	private Stockuser user = new Stockuser();
	private Stockuser selectedUser = new Stockuser();
	UserDao userDao = new UserDaoImpl();
	private List<Stockuser> listUsers = new ArrayList<Stockuser>();

	public Stockuser getUser() {
		return user;
	}

	public void setUser(Stockuser user) {
		this.user = user;
	}

	public Stockuser getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(Stockuser selectedUser) {
		this.selectedUser = selectedUser;
	}

	public List<Stockuser> getListUsers() {
		listUsers = userDao.findAll();
		return listUsers;
	}

	public void setListUsers(List<Stockuser> listUsers) {
		this.listUsers = listUsers;
	}
	
	public void addUser(ActionEvent e) {
		Role role = new Role();
		role.setIdrole(new BigDecimal(1));
		user.setRole(role);
		userDao.add(user);
		user = new Stockuser();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ajout effectué avec succés"));
	}
	
	public void deleteUser(ActionEvent e) {
		if(selectedUser == null || selectedUser.getIduser() == new BigDecimal(0)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Attention" ,"Aucun utilisateur n'a Ã©tÃ© sÃ©lectionnÃ© !"));
		} else {
			userDao.delete(selectedUser);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Suppresion effectuÃ© avec succés"));
		}
	}
	
	public String editUser() {
		return "editUser.xhtml";
	}
	
	public void updateUser(ActionEvent e) {
		userDao.update(selectedUser);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Modification effectuÃ© avec succés"));
	}

	public String login() {
		if(userDao.findUserByLoginAndPassword(user.getLogin(),user.getPassword()) != null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Connexion effectué avec succés"));
			return "accueil.xhtml";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Attention", "Utilisateur inexistant"));
			return "login.xhtml";
		}
	}
}

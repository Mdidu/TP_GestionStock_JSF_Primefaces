package persistence.dao;

import persistence.entities.Stockuser;

public interface UserDao extends GlobalDao<Stockuser> {

	/**
	 * Vérification de la connexion
	 * @param login
	 * @param password
	 * @return
	 */
	Stockuser findUserByLoginAndPassword(String login, String password);
}

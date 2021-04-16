package persistence.dao;

import persistence.entities.Stockuser;

public interface UserDao extends GlobalDao<Stockuser> {

	Stockuser findUserByLoginAndPassword(String login, String password);
}

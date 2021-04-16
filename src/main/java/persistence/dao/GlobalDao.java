package persistence.dao;

import java.io.Serializable;
import java.util.List;

public interface GlobalDao<T> {
	void add(T type);
	void delete(T type);
	void update(T type);
	List<T> findAll();
	T findById(Serializable o);
}

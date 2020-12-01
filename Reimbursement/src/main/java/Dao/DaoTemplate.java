package Dao;

import java.util.List;

public interface DaoTemplate<T> {
	
	public List<T> findAll();
	public boolean insert(T objectToInsert);
	public boolean update(T objectToUpdate);
	public T findById(int id);
	public T findByName(String name);
	
	
}

package com.revature.faculty.dao;

import java.util.List;

import com.revature.faculty.Exception.DBException;
import com.revature.faculty.model.Roles;

public interface RolesDao {
	List<Roles>  get() throws DBException;
	
	Roles get(Long id) throws DBException;
	
	void save(Roles role) throws DBException;
	
	void delete(Long id) throws DBException;
	
	

}

package com.revature.faculty.service;

import java.util.List;

import com.revature.faculty.Exception.DBException;
import com.revature.faculty.Exception.ServiceException;
import com.revature.faculty.model.Roles;

public interface RolesService {
	List<Roles> get() throws ServiceException;

	Roles get(Long id) throws ServiceException;

	void save(Roles role) throws DBException;

	void delete(Long id) throws ServiceException;

}

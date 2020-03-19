package com.revature.faculty.service;

import java.util.List;

import com.revature.faculty.Exception.DBException;
import com.revature.faculty.Exception.ServiceException;
import com.revature.faculty.dto.InsertFacultyDto;
import com.revature.faculty.model.Faculty;

public interface FacultyService {
	List<Faculty> get() throws ServiceException;
	
	Faculty get(Long id) throws ServiceException;
	
	void delete(Long id) throws ServiceException;
	
	List<Faculty> getByInstitution(Long id) throws ServiceException;

	void save(InsertFacultyDto dto) throws DBException;
	
}

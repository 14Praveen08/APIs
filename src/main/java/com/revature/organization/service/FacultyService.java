package com.revature.organization.service;

import java.util.List;

import com.revature.organization.dto.InsertFacultyDto;
import com.revature.organization.exception.BadResponse;
import com.revature.organization.exception.NotFound;
import com.revature.organization.model.Faculty;

public interface FacultyService {
	List<Faculty> get() throws NotFound;

	Faculty get(Long id) throws NotFound;

	void delete(Long id) throws NotFound;

	List<Faculty> getByInstitution(Long id) throws NotFound;

	void save(InsertFacultyDto dto) throws BadResponse;
}

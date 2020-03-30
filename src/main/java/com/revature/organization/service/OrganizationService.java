package com.revature.organization.service;

import java.util.List;

import com.revature.organization.exception.BadResponse;
import com.revature.organization.exception.NotFound;
import com.revature.organization.exception.ServiceException;
import com.revature.organization.model.Organization;

public interface OrganizationService {
	List<Organization> get() throws NotFound;

	List<Organization> getActive() throws ServiceException;

	Organization get(Long id) throws NotFound;

	void save(Organization org) throws BadResponse;

	void delete(Long id) throws NotFound;

	void changeStatus(Long id);
}

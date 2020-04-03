package com.revature.organization.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.organization.dao.OrganizationDAO;
import com.revature.organization.exception.DBException;
import com.revature.organization.exception.ServiceException;
import com.revature.organization.model.Organization;

@Service
public class OrganizationServiceImpl implements OrganizationService {
	@Autowired
	private OrganizationDAO organizationDAO;

	@Transactional
	@Override
	public List<Organization> get() throws ServiceException {
		List<Organization> org = new ArrayList<Organization>();
		try {
			org = organizationDAO.get();
			if (org.isEmpty()) {
				throw new ServiceException("Unable to get records!!DB Empty");
			}
		} catch (DBException e) {
			System.out.println(e.getMessage());
		}
		return org;
	}

	@Transactional
	@Override
	public List<Organization> getActive() throws ServiceException {
		List<Organization> org = new ArrayList<Organization>();
		try {
			org = organizationDAO.getActive();
			if (org.isEmpty()) {
				throw new ServiceException("Unable to get records!!DB Empty");
			}
		} catch (DBException e) {
			System.out.println(e.getMessage());
		}
		return org;
	}

	@Transactional
	@Override
	public Organization get(Long id) throws ServiceException {
		Organization org = new Organization();
		try {
			org = organizationDAO.get(id);
			if (org == null) {
				throw new ServiceException("The Record with " + id + " is Displayed Below");
			}
		} catch (DBException e) {
			System.out.println(e.getMessage());
		}
		return org;
	}

	@Transactional
	@Override
	public void save(Organization org) throws ServiceException {
		try {
			LocalDateTime ts = LocalDateTime.now();
			if (org.getId() == null) {
				org.setIsActive(true);
				org.setCreatedon(ts);
			} else {
				org.setModifiedon(ts);
			}
			String name = org.getName();
			String Aname = org.getAlias();
			String University = org.getUniversity();
			if (name == null || Aname == null || University == null) {
				throw new ServiceException(" One or More Fields Missing");
			}
			organizationDAO.save(org);
		} catch (DBException e) {
			System.out.println(e.getMessage());
		}

	}

	@Transactional
	@Override
	public void delete(Long id) throws ServiceException {
		Organization org = new Organization();
		try {
			org = organizationDAO.get(id);
			if (org != null) {
				organizationDAO.delete(id);
			} else {
				throw new ServiceException("Cannot Find Id");
			}
		} catch (DBException e) {
			System.out.println(e.getMessage());
		}

	}

	@Transactional
	@Override
	public void changeStatus(Long id) throws ServiceException {
		Organization org = new Organization();
		try {
			org = organizationDAO.get(id);
			if (org != null) {
				organizationDAO.changeStatus(id);
			} else {
				throw new ServiceException("Something Went Wrong!!Status Could Not Be Changed");
			}
		} catch (DBException e) {
			System.out.println(e.getMessage());
		}
	}
}

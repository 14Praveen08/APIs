package com.revature.organization.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.organization.dao.RolesDao;
import com.revature.organization.exception.DBException;
import com.revature.organization.exception.ServiceException;
import com.revature.organization.model.Roles;

@Service
public class RolesServiceImpl implements RolesService {
	@Autowired
	private RolesDao rolesDao;

	@Transactional
	@Override
	public List<Roles> get() throws ServiceException {
		List<Roles> list = new ArrayList<Roles>();
		try {
			list = rolesDao.get();
			if (list.isEmpty()) {
				throw new ServiceException("Unable to get records!!DB Empty");
			}
		} catch (DBException e) {
			System.out.println(e.getMessage());

		}
		return list;
	}

	@Transactional
	@Override
	public Roles get(Long id) throws ServiceException {
		Roles role = new Roles();
		try {
			role = rolesDao.get(id);
			if (role == null) {
				throw new ServiceException("Cannot Find Id");
			}
		} catch (DBException e) {
			System.out.println(e.getMessage());
		}

		return role;
	}

	@Transactional
	@Override
	public void save(Roles role) throws ServiceException, DBException {
		try {
			String name = role.getName();
			if (name == null) {
				throw new ServiceException(" One or More Fields Missing");
			}
			rolesDao.save(role);
		} catch (DBException e) {
			System.out.println(e.getMessage());
		}

	}

	@Transactional
	@Override
	public void delete(Long id) throws ServiceException {
		Roles role = new Roles();
		try {
			role = rolesDao.get(id);
			if (role != null) {
				rolesDao.delete(id);
			} else {
				throw new ServiceException("Cannot Find Id");
			}
		} catch (DBException e) {
			System.out.println(e.getMessage());
		}

	}
}

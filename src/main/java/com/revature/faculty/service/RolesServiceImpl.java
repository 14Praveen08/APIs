package com.revature.faculty.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.faculty.Exception.DBException;
import com.revature.faculty.Exception.ServiceException;
import com.revature.faculty.dao.RolesDao;
import com.revature.faculty.model.Roles;
import com.revature.faculty.util.Message;

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
				throw new ServiceException(Message.NO_RECORD);
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
				throw new ServiceException(Message.UNABLE_TO_FIND_ROLE);
			}
		} catch (DBException e) {
			System.out.println(e.getMessage());
		}

		return role;
	}

	@Transactional
	@Override
	public void save(Roles role) throws DBException {
		try {
			String name = role.getName();
			if (name == null) {
				throw new DBException(Message.UNABLE_TO_INSERT);
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
				throw new ServiceException(Message.UNABLE_TO_DELETE_ROLE);
			}
		} catch (DBException e) {
			System.out.println(e.getMessage());
		}

	}

}

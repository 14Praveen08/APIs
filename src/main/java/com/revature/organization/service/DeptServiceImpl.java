package com.revature.organization.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.organization.dao.DeptDao;
import com.revature.organization.exception.DBException;
import com.revature.organization.exception.ServiceException;
import com.revature.organization.model.Department;

@Service
public class DeptServiceImpl implements DeptService {

	@Autowired
	private DeptDao deptDao;

	@Transactional
	@Override
	public List<Department> get() throws ServiceException {
		List<Department> list = new ArrayList<Department>();
		try {
			list = deptDao.get();
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
	public Department get(Long id) throws ServiceException {
		Department dept = new Department();
		try {
			dept = deptDao.get(id);
			if (dept == null) {
				throw new ServiceException("Cannot Find Id");
			}
		} catch (DBException e) {
			System.out.println(e.getMessage());
		}

		return dept;
	}

}

package com.revature.organization.service;

import java.util.List;

import com.revature.organization.exception.ServiceException;
import com.revature.organization.model.Department;

public interface DeptService {

	List<Department> get() throws ServiceException;

	Department get(Long id) throws ServiceException;
}

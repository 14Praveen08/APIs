package com.revature.organization.dao;

import java.util.List;

import com.revature.organization.exception.DBException;
import com.revature.organization.model.Department;

public interface DeptDao {
	List<Department> get() throws DBException;

	Department get(Long id) throws DBException;
}

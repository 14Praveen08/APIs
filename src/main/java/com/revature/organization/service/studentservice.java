package com.revature.organization.service;

import java.util.List;

import com.revature.organization.dto.InsertDTO;
import com.revature.organization.exception.ServiceException;
import com.revature.organization.model.student;

public interface studentservice {
	public List<student> get() throws ServiceException;

	public void delete(int id) throws ServiceException;

	public List<student> getstudbyInst(int institutionid) throws ServiceException;

	public List<student> getstudbyInstYear(int institutionid, int year) throws ServiceException;

	public List<student> getstudbyInstYearDept(int institutionid, int year, Long dept) throws ServiceException;

	public void save(InsertDTO idto) throws ServiceException;

	public student get(int id) throws ServiceException;

	public List<student> getstudbyYear(int year) throws ServiceException;

	public List<student> getstudbyDepartment(Long deptid) throws ServiceException;

}

package com.revature.student.service;

import java.util.List;

import com.revature.student.DTO.InsertDTO;
import com.revature.student.Exception.DBException;
import com.revature.student.Exception.ServiceException;
import com.revature.student.model.student;

public interface studentservice {

	public List<student> get() throws ServiceException;
	public void delete(int id) throws ServiceException;
	public List<student> getstudbyInst(int institutionid) throws ServiceException;
	public List<student> getstudbyInstYear(int institutionid, int year) throws ServiceException;
	public void save(InsertDTO idto) throws DBException;
	public student get(int id) throws ServiceException;
	public List<student> getstudbyYear(int year) throws ServiceException;

	


}

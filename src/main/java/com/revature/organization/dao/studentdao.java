package com.revature.organization.dao;

import java.util.List;

import com.revature.organization.exception.DBException;
import com.revature.organization.model.student;

public interface studentdao {
	public List<student> get() throws DBException;

	public void insert(student stud) throws DBException;

	public void delete(int id) throws DBException;

	public List<student> getstudbyInst(int institutionid) throws DBException;

	public List<student> getstudbyInstYear(int institutionid, int year) throws DBException;

	public List<student> getstudbyInstYearDept(int institutionid, int year, Long dept) throws DBException;

	public student get(int id) throws DBException;

	public List<student> getstudbyYear(int year) throws DBException;

	public List<student> getstudbyDepartment(Long deptid) throws DBException;

	public List<student> getstudbyDeptYear(Long dept, int year) throws DBException;

	public List<student> getstudbyInstDept(int institutionid, Long dept) throws DBException;
}

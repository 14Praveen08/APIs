package com.revature.organization.service;

import java.util.List;

import com.revature.organization.dto.InsertDTO;
import com.revature.organization.exception.BadResponse;
import com.revature.organization.exception.NotFound;
import com.revature.organization.model.student;

public interface studentservice {
	public List<student> get() throws NotFound;

	public void delete(int id) throws NotFound;

	public List<student> getstudbyInst(int institutionid) throws NotFound;

	public List<student> getstudbyInstYear(int institutionid, int year) throws NotFound;

	public void save(InsertDTO idto) throws BadResponse;

	public student get(int id) throws NotFound;

	public List<student> getstudbyYear(int year) throws NotFound;
}

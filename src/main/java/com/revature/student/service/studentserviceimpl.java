package com.revature.student.service;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.student.DAO.studentdao;
import com.revature.student.DTO.InsertDTO;
import com.revature.student.Exception.DBException;
import com.revature.student.Exception.ServiceException;
import com.revature.student.model.Organization;
import com.revature.student.model.student;
import com.revature.student.util.Message;

@Service
public class studentserviceimpl implements studentservice {

	@Autowired
	private studentdao studdao;
 
	
	@Transactional
	@Override
	public List<student> get() throws ServiceException {
		List<student> list = new ArrayList<student>();
		try {
			list= studdao.get();
			if(list.isEmpty()) {
				throw new ServiceException(Message.NO_RECORD);
			}
		}catch(DBException e) {
			System.out.println(e.getMessage());
		}
		return list;
	}

	@Transactional
	@Override
	public void save(InsertDTO idto) throws DBException {
		student stud = new student();
		Organization org = new Organization();
		try {
			if(idto.getId() == null) {
				stud.setCreatedon(idto.getCreatedon());
			}
			else {
				stud=studdao.get(idto.getId());
				stud.setModifiedon(idto.getModifiedon());
				stud.setId(idto.getId());
			}
			org.setId(idto.getInstitutionid());
			stud.setOrg(org);
			stud.setRedgno(idto.getRedgno());
			stud.setFname(idto.getFname());
			stud.setLname(idto.getLname());
			stud.setDob(idto.getDob());
			stud.setYear(idto.getYear());
			stud.setMobileno(idto.getMobileno());
			stud.setEmail(idto.getEmail());
			Long redg = idto.getRedgno();
			String fname = idto.getFname();
			String lname = idto.getLname();
			Date dob = idto.getDob();
			Integer year = idto.getYear();
			Long mobilenumber = idto.getMobileno();
			String email = idto.getEmail();
			if(redg==null || fname==null || lname==null || dob==null || year==null || email==null || mobilenumber==null) {
				throw new DBException(Message.UNABLE_TO_INSERT);
			}
			studdao.insert(stud);
			
		}catch(DBException e) {
			System.out.println(e.getMessage());			
		}
		}
		

	@Transactional
	@Override
	public void delete(int id) throws ServiceException {
		student stud = new student();
		try {
			stud = studdao.get(id);
			if(stud!=null) {
				studdao.delete(id);
			}
			else {
				throw new ServiceException(Message.UNABLE_TO_DELETE); 
			}
		}catch(DBException e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public List<student> getstudbyInst(int institutionid) throws ServiceException {
		List<student> stud = new ArrayList<student>();
		try {
			stud = studdao.getstudbyInst(institutionid);
			if(stud.isEmpty()) {
				throw new ServiceException(Message.NO_STUDENTS_AVAILABLE);
			}
		}catch(DBException e) {
			System.out.println(e.getMessage());
		}
		return stud;
	}

	@Override
	public List<student> getstudbyInstYear(int institutionid, int year) throws ServiceException{
		List<student> stud = new ArrayList<student>();
		try {
			stud = studdao.getstudbyInstYear(institutionid,year);
			if(stud.isEmpty()) {
				throw new ServiceException(Message.NO_STUDENT_YEAR_AVAILABLE);
			}
		}catch(DBException e) {
			System.out.println(e.getMessage());
		}
		return stud;
	}


	@Override
	public List<student> getstudbyYear(int year) throws ServiceException {
		List<student> stud = new ArrayList<student>();
			try {
			stud=studdao.getstudbyYear(year);
			if(stud.isEmpty()) {
				throw new ServiceException(Message.NO_STUDENT_YEAR_AVAILABLE);
			}
		}catch(DBException e) {
			System.out.println(e.getMessage());
		}
		return stud;
	}

	/*
	 * @Override public void update(UpdateDTO udto) { student stud = new student();
	 * stud.setId(udto.getId()); stud.setInstitutionid(udto.getInstitutionid());
	 * stud.setRedgno(udto.getRedgno()); stud.setFname(udto.getFname());
	 * stud.setLname(udto.getLname()); stud.setDob(udto.getDob());
	 * stud.setYear(udto.getYear()); stud.setMobileno(udto.getMobileno());
	 * stud.setEmail(udto.getEmail()); System.out.println("Before");
	 * studdao.update(stud); System.out.println("After"); }
	 */
	
	@Override
	public student get(int id) throws ServiceException {
		student stud = new student();
		try {
			stud = studdao.get(id);
			if(stud == null) {
				throw new ServiceException(Message.UNABLE_TO_FIND);
			}
		}catch(DBException e) {
			System.out.println(e.getMessage());
		}
		return stud;
	}



}

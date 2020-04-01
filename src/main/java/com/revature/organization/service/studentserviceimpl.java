package com.revature.organization.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.revature.organization.dao.studentdao;
import com.revature.organization.dto.InsertDTO;
import com.revature.organization.exception.BadResponse;
import com.revature.organization.exception.DBException;
import com.revature.organization.exception.NotFound;
import com.revature.organization.model.Organization;
import com.revature.organization.model.student;

@Service
public class studentserviceimpl implements studentservice {
	@Autowired
	private studentdao studdao;

	@Transactional
	@Override
	public List<student> get() throws NotFound {
		List<student> list = new ArrayList<student>();
		try {
			list = studdao.get();
			if (list.isEmpty()) {
				throw new NotFound(HttpStatus.NOT_FOUND.value(), "Unable to get records!!DB Empty");
			}
		} catch (DBException e) {
			System.out.println(e.getMessage());
		}
		return list;
	}

	@Transactional
	@Override
	public void save(InsertDTO idto) throws BadResponse {
		student stud = new student();
		Organization org = new Organization();
		try {
			if (idto.getId() == null) {
				stud.setCreatedon(idto.getCreatedon());
			} else {
				stud = studdao.get(idto.getId());
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
			if (redg == null || fname == null || lname == null || dob == null || year == null || email == null
					|| mobilenumber == null) {
				throw new BadResponse(HttpStatus.BAD_REQUEST.value(), " One or More Fields Missing");
			}
			studdao.insert(stud);

		} catch (DBException e) {
			System.out.println(e.getMessage());
		}
	}

	@Transactional
	@Override
	public void delete(int id) throws NotFound {
		student stud = new student();
		try {
			stud = studdao.get(id);
			if (stud != null) {
				studdao.delete(id);
			} else {
				throw new NotFound(HttpStatus.NOT_FOUND.value(), "Cannot Find Id");
			}
		} catch (DBException e) {
			System.out.println(e.getMessage());
		}

	}

	@Override
	public List<student> getstudbyInst(int institutionid) throws NotFound {
		List<student> stud = new ArrayList<student>();
		try {
			stud = studdao.getstudbyInst(institutionid);
			if (stud.isEmpty()) {
				throw new NotFound(HttpStatus.NOT_FOUND.value(),
						"The Record with " + institutionid + " is Displayed Below");
			}
		} catch (DBException e) {
			System.out.println(e.getMessage());
		}
		return stud;
	}

	@Override
	public List<student> getstudbyInstYear(int institutionid, int year) throws NotFound {
		List<student> stud = new ArrayList<student>();
		try {
			stud = studdao.getstudbyInstYear(institutionid, year);
			if (stud.isEmpty()) {
				throw new NotFound(HttpStatus.NOT_FOUND.value(), "The Record with Institution Id " + institutionid
						+ " and year " + year + " is Displayed Below");
			}
		} catch (DBException e) {
			System.out.println(e.getMessage());
		}
		return stud;
	}

	@Override
	public List<student> getstudbyYear(int year) throws NotFound {
		List<student> stud = new ArrayList<student>();
		try {
			stud = studdao.getstudbyYear(year);
			if (stud.isEmpty()) {
				throw new NotFound(HttpStatus.NOT_FOUND.value(), "The Record with " + year + " is Displayed Below");
			}
		} catch (DBException e) {
			System.out.println(e.getMessage());
		}
		return stud;
	}

	@Override
	public student get(int id) throws NotFound {
		student stud = new student();
		try {
			stud = studdao.get(id);
			if (stud == null) {
				throw new NotFound(HttpStatus.NOT_FOUND.value(), "The Record with " + id + " is Displayed Below");
			}
		} catch (DBException e) {
			System.out.println(e.getMessage());
		}
		return stud;
	}
}

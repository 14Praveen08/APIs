package com.revature.organization.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.revature.organization.dao.FacultyDao;
import com.revature.organization.dto.InsertFacultyDto;
import com.revature.organization.exception.BadResponse;
import com.revature.organization.exception.DBException;
import com.revature.organization.exception.NotFound;
import com.revature.organization.model.Faculty;
import com.revature.organization.model.Organization;
import com.revature.organization.model.Roles;

@Service
public class FacultyServiceImpl implements FacultyService {
	@Autowired
	private FacultyDao facultyDao;

	@Transactional
	@Override
	public List<Faculty> get() throws NotFound {
		List<Faculty> list = new ArrayList<Faculty>();
		try {
			list = facultyDao.get();
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
	public Faculty get(Long id) throws NotFound {
		Faculty fac = new Faculty();
		try {
			fac = facultyDao.get(id);
			if (fac == null) {
				throw new NotFound(HttpStatus.NOT_FOUND.value(), "The Record with " + id + " is Displayed Below");
			}
		} catch (DBException e) {
			System.out.println(e.getMessage());

		}
		return fac;
	}

	@Transactional
	@Override
	public void save(InsertFacultyDto dto) throws BadResponse {
		Faculty fac = new Faculty();
		Organization org = new Organization();
		Roles role = new Roles();
		try {
			if (dto.getId() == null) {
				fac.setCreatedon(dto.getCreatedon());
			} else {
				fac = facultyDao.get(dto.getId());
				fac.setModifiedon(dto.getModifiedon());
				System.out.println("Object Exists");
				fac.setId(dto.getId());
			}

			fac.setEmployee_id(dto.getEmployee_id());
			org.setId(dto.getInstitution_id());
			fac.setOrg(org);
			fac.setFirst_name(dto.getFirst_name());
			fac.setLast_name(dto.getLast_name());
			fac.setDob(dto.getDob());
			fac.setEmail(dto.getEmail());
			fac.setMobile_no(dto.getMobile_no());
			role.setId(dto.getRole_id());
			fac.setRoles(role);
			Integer id = dto.getEmployee_id();
			String fname = dto.getFirst_name();
			String lname = dto.getLast_name();
			Date dob = dto.getDob();
			String email = dto.getEmail();
			Long mobilenumber = dto.getMobile_no();

			if (fname == null || lname == null || dob == null || email == null || mobilenumber == null) {

				throw new BadResponse(HttpStatus.BAD_REQUEST.value(), " One or More Fields Missing");
			}
			facultyDao.save(fac);

		} catch (DBException e) {
			System.out.println(e.getMessage());
		}
	}

	@Transactional
	@Override
	public void delete(Long id) throws NotFound {
		Faculty fac = new Faculty();
		try {
			fac = facultyDao.get(id);
			if (fac != null) {
				facultyDao.delete(id);
			} else {
				throw new NotFound(HttpStatus.NOT_FOUND.value(), "Cannot Find Id");
			}
		} catch (DBException e) {
			System.out.println(e.getMessage());
		}

	}

	@Override
	public List<Faculty> getByInstitution(Long id) throws NotFound {
		List<Faculty> list = new ArrayList<Faculty>();
		try {
			list = facultyDao.getByInstitution(id);
			if (list.isEmpty()) {
				throw new NotFound(HttpStatus.NOT_FOUND.value(), "The Record with " + id + " is Displayed Below");
			}
		} catch (DBException e) {
			System.out.println(e.getMessage());
		}
		return list;

	}
}

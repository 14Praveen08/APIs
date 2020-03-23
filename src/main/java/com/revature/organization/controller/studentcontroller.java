package com.revature.organization.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.organization.dto.InsertDTO;
import com.revature.organization.exception.DBException;
import com.revature.organization.exception.ServiceException;
import com.revature.organization.model.student;
import com.revature.organization.service.studentservice;

@RestController
@RequestMapping("/core")
@CrossOrigin(origins = "*")
public class studentcontroller {
	@Autowired
	private studentservice studservice;

	@GetMapping("/getstud")
	public List<student> get() throws ServiceException {
		return studservice.get();
	}

	@GetMapping("/getstud/{id}")
	public student get(@PathVariable int id) throws ServiceException {
		return studservice.get(id);
	}

	@GetMapping("/getstud/institution/{institutionid}")
	public List<student> getstudbyInst(@PathVariable int institutionid) throws ServiceException {

		return studservice.getstudbyInst(institutionid);
	}

	@GetMapping("getstud/instyearwise/{institutionid}/{year}")
	public List<student> getstudbyInstYear(@PathVariable int institutionid, @PathVariable int year)
			throws ServiceException {
		return studservice.getstudbyInstYear(institutionid, year);
	}

	@GetMapping("getstud/year/{year}")
	public List<student> getstudbyYear(@PathVariable int year) throws ServiceException {
		return studservice.getstudbyYear(year);
	}

	@PostMapping("/insert")
	public void save(@RequestBody InsertDTO idto) throws DBException {
		studservice.save(idto);
	}

	@PutMapping("/update")
	public void update(@RequestBody InsertDTO idto) throws DBException {
		studservice.save(idto);
	}

	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable int id) throws ServiceException {
		studservice.delete(id);
		return "Student of " + id + " deleted";

	}
}

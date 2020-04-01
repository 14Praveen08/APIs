package com.revature.organization.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.revature.organization.exception.BadResponse;
import com.revature.organization.exception.DBException;
import com.revature.organization.exception.NotFound;
import com.revature.organization.exception.ResponseEntity;
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
	public ResponseEntity get() {
		try {
			List<student> student = studservice.get();
			return new ResponseEntity(HttpStatus.OK.value(), "The Records in the Table are Displayed Below", student);
		} catch (NotFound e) {
			return new ResponseEntity(HttpStatus.NOT_FOUND.value(), "Unable to get records!!DB Empty", null);
		}
	}

	@GetMapping("/getstud/{id}")
	public ResponseEntity get(@PathVariable int id) {
		try {
			student student = studservice.get(id);
			return new ResponseEntity(HttpStatus.OK.value(), "The Record with " + id + " is Displayed Below", student);
		} catch (NotFound e) {
			return new ResponseEntity(HttpStatus.NOT_FOUND.value(), "Cannot Find Data with Id " + id, id);
		}
	}

	@GetMapping("/getstud/institution/{institutionid}")
	public ResponseEntity getstudbyInst(@PathVariable int institutionid) {
		try {
			List<student> student = studservice.getstudbyInst(institutionid);
			return new ResponseEntity(HttpStatus.OK.value(), "The Record with " + institutionid + " is Displayed Below",
					student);
		} catch (NotFound e) {
			return new ResponseEntity(HttpStatus.NOT_FOUND.value(), "Cannot Find Data with Id " + institutionid,
					institutionid);
		}

	}

	@GetMapping("getstud/instyearwise/{institutionid}/{year}")
	public ResponseEntity getstudbyInstYear(@PathVariable int institutionid, @PathVariable int year)
			throws ServiceException {
		try {
			List<student> student = studservice.getstudbyInstYear(institutionid, year);
			return new ResponseEntity(HttpStatus.OK.value(),
					"The Record with Institution id  " + institutionid + " and year " + year + " is Displayed Below",
					student);
		} catch (NotFound e) {
			return new ResponseEntity(HttpStatus.NOT_FOUND.value(),
					"Cannot find data with institution id " + institutionid + " and year " + year, null);
		}
	}

	@GetMapping("getstud/year/{year}")
	public ResponseEntity getstudbyYear(@PathVariable int year) {
		try {
			List<student> student = studservice.getstudbyYear(year);
			return new ResponseEntity(HttpStatus.OK.value(), "Student with " + year + " is Displayed Below", student);
		} catch (NotFound e) {
			return new ResponseEntity(HttpStatus.NOT_FOUND.value(), "Cannot Find Data with Year " + year, year);
		}

	}

	@PostMapping("/insert")
	public ResponseEntity save(@RequestBody InsertDTO idto) {
		try {
			studservice.save(idto);
			return new ResponseEntity(HttpStatus.CREATED.value(), "Data Insertion Done Successfully", idto);
		} catch (BadResponse e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST.value(), "One or More Fields Missing", null);
		}
	}

	@PutMapping("/update")
	public ResponseEntity update(@RequestBody InsertDTO idto) throws DBException {
		try {
			studservice.save(idto);
			return new ResponseEntity(HttpStatus.CREATED.value(), "Data Updation Done Successfully", idto);
		} catch (BadResponse e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST.value(), "One or More Fields Missing", null);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity delete(@PathVariable int id) {
		try {
			studservice.delete(id);
			return new ResponseEntity(HttpStatus.OK.value(), "Record " + id + " Deleted Successfully", id);
		} catch (NotFound e) {
			return new ResponseEntity(HttpStatus.NOT_FOUND.value(), "Cannot Find Id", id);
		}

	}
}

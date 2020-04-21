package com.revature.organization.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.revature.organization.exception.HTTPStatusResponse;
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
	public ResponseEntity<HTTPStatusResponse> get() {
		try {
			List<student> student = studservice.get();
			return new ResponseEntity<>(new HTTPStatusResponse(HttpStatus.OK.value(),
					"The Records in the Table are Displayed Below", student), HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(
					new HTTPStatusResponse(HttpStatus.NOT_FOUND.value(), "Unable to get records!!DB Empty", null),
					HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getstud/{id}")
	public ResponseEntity<HTTPStatusResponse> get(@PathVariable int id) {
		try {
			student student = studservice.get(id);
			return new ResponseEntity<>(new HTTPStatusResponse(HttpStatus.OK.value(),
					"The Record with " + id + " is Displayed Below", student), HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(
					new HTTPStatusResponse(HttpStatus.NOT_FOUND.value(), "Cannot Find Data with Id " + id, id),
					HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getstud/institution/{institutionid}")
	public ResponseEntity<HTTPStatusResponse> getstudbyInst(@PathVariable int institutionid) {
		try {
			List<student> student = studservice.getstudbyInst(institutionid);
			return new ResponseEntity<>(new HTTPStatusResponse(HttpStatus.OK.value(),
					"The Record with " + institutionid + " is Displayed Below", student), HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(new HTTPStatusResponse(HttpStatus.NOT_FOUND.value(),
					"Cannot Find Data with Id " + institutionid, institutionid), HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/getstud/department/{deptid}")
	public ResponseEntity<HTTPStatusResponse> getstudbyDepartment(@PathVariable Long deptid) {
		try {
			List<student> student = studservice.getstudbyDepartment(deptid);
			return new ResponseEntity<>(new HTTPStatusResponse(HttpStatus.OK.value(),
					"Student with " + deptid + " is Displayed Below", student), HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(new HTTPStatusResponse(HttpStatus.NOT_FOUND.value(),
					"Cannot Find Data with Year " + deptid, deptid), HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/getstud/instyearwise/{institutionid}/{year}")
	public ResponseEntity<HTTPStatusResponse> getstudbyInstYear(@PathVariable int institutionid, @PathVariable int year)
			throws ServiceException {
		try {
			List<student> student = studservice.getstudbyInstYear(institutionid, year);
			return new ResponseEntity<>(new HTTPStatusResponse(HttpStatus.OK.value(),
					"The Record with Institution id  " + institutionid + " and year " + year + " is Displayed Below",
					student), HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(
					new HTTPStatusResponse(HttpStatus.NOT_FOUND.value(),
							"Cannot find data with institution id " + institutionid + " and year " + year, null),
					HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getstud/instdeptwise/{institutionid}/{dept}")
	public ResponseEntity<HTTPStatusResponse> getstudbyInstDept(@PathVariable int institutionid,
			@PathVariable Long dept) throws ServiceException {
		try {
			List<student> student = studservice.getstudbyInstDept(institutionid, dept);
			return new ResponseEntity<>(new HTTPStatusResponse(HttpStatus.OK.value(),
					"The Record with Institution id  " + institutionid + " and Dept id " + dept + " is Displayed Below",
					student), HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(
					new HTTPStatusResponse(HttpStatus.NOT_FOUND.value(),
							"Cannot find data with institution id " + institutionid + " and Dept id " + dept, null),
					HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getstud/deptyearwise/{dept}/{year}")
	public ResponseEntity<HTTPStatusResponse> getstudbyDeptYear(@PathVariable Long dept, @PathVariable int year)
			throws ServiceException {
		try {
			List<student> student = studservice.getstudbyDeptYear(dept, year);
			return new ResponseEntity<>(new HTTPStatusResponse(HttpStatus.OK.value(),
					"The Record with Department id  " + dept + " and year " + year + " is Displayed Below", student),
					HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(
					new HTTPStatusResponse(HttpStatus.NOT_FOUND.value(),
							"Cannot find data with Department id " + dept + " and year " + year, null),
					HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getstud/instyeardeptwise/{institutionid}/{year}/{dept}")
	public ResponseEntity<HTTPStatusResponse> getstudbyInstYearDept(@PathVariable int institutionid,
			@PathVariable int year, @PathVariable Long dept) throws ServiceException {
		try {
			List<student> student = studservice.getstudbyInstYearDept(institutionid, year, dept);
			return new ResponseEntity<>(
					new HTTPStatusResponse(HttpStatus.OK.value(), "The Record with Institution id  " + institutionid
							+ " and year " + year + " and department" + dept + " is Displayed Below", student),
					HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(new HTTPStatusResponse(HttpStatus.NOT_FOUND.value(),
					"Cannot find data with institution id " + institutionid + " year " + year + "or department" + dept,
					null), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getstud/year/{year}")
	public ResponseEntity<HTTPStatusResponse> getstudbyYear(@PathVariable int year) {
		try {
			List<student> student = studservice.getstudbyYear(year);
			return new ResponseEntity<>(new HTTPStatusResponse(HttpStatus.OK.value(),
					"Student with " + year + " is Displayed Below", student), HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(
					new HTTPStatusResponse(HttpStatus.NOT_FOUND.value(), "Cannot Find Data with Year " + year, year),
					HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping("/insert")
	public ResponseEntity<HTTPStatusResponse> save(@RequestBody InsertDTO idto) {
		try {
			studservice.save(idto);
			return new ResponseEntity<>(
					new HTTPStatusResponse(HttpStatus.CREATED.value(), "Data Insertion Done Successfully", idto),
					HttpStatus.CREATED);
		} catch (ServiceException e) {
			return new ResponseEntity<>(
					new HTTPStatusResponse(HttpStatus.BAD_REQUEST.value(), "One or More Fields Missing", null),
					HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/update")
	public ResponseEntity<HTTPStatusResponse> update(@RequestBody InsertDTO idto) throws DBException {
		try {
			studservice.save(idto);
			return new ResponseEntity<>(
					new HTTPStatusResponse(HttpStatus.OK.value(), "Data Updation Done Successfully", idto),
					HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(
					new HTTPStatusResponse(HttpStatus.BAD_REQUEST.value(), "One or More Fields Missing", null),
					HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HTTPStatusResponse> delete(@PathVariable int id) {
		try {
			studservice.delete(id);
			return new ResponseEntity<>(
					new HTTPStatusResponse(HttpStatus.OK.value(), "Record " + id + " Deleted Successfully", id),
					HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(new HTTPStatusResponse(HttpStatus.NOT_FOUND.value(), "Cannot Find Id", id),
					HttpStatus.NOT_FOUND);
		}

	}
}

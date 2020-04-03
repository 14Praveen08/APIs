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

import com.revature.organization.dto.InsertFacultyDto;
import com.revature.organization.exception.HTTPStatusResponse;
import com.revature.organization.exception.ServiceException;
import com.revature.organization.model.Faculty;
import com.revature.organization.service.FacultyService;

@RestController
@RequestMapping("/core")
@CrossOrigin(origins = "*")
public class FacultyController {
	@Autowired
	private FacultyService facultyService;

	@GetMapping("/faculty")
	public ResponseEntity<HTTPStatusResponse> get() {
		try {
			List<Faculty> faculty = facultyService.get();
			return new ResponseEntity<>(new HTTPStatusResponse(HttpStatus.OK.value(),
					"The Records in the Table are Displayed Below", faculty), HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(
					new HTTPStatusResponse(HttpStatus.NOT_FOUND.value(), "Unable to get records!!DB Empty", null),
					HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/faculty/institution/{inst_id}")
	public ResponseEntity<HTTPStatusResponse> getbyInst(@PathVariable Long inst_id) {
		try {
			List<Faculty> faculty = facultyService.getByInstitution(inst_id);
			return new ResponseEntity<>(new HTTPStatusResponse(HttpStatus.OK.value(),
					"The Record with " + inst_id + " is Displayed Below", faculty), HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(new HTTPStatusResponse(HttpStatus.NOT_FOUND.value(),
					"Cannot Find Data with Id " + inst_id, inst_id), HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/faculty/{id}")
	public ResponseEntity<HTTPStatusResponse> get(@PathVariable Long id) {
		try {
			Faculty facObj = facultyService.get(id);
			return new ResponseEntity<>(new HTTPStatusResponse(HttpStatus.OK.value(),
					"The Record with " + id + " is Displayed Below", facObj), HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(
					new HTTPStatusResponse(HttpStatus.NOT_FOUND.value(), "Cannot Find Data with Id " + id, id),
					HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/faculty")
	public ResponseEntity<HTTPStatusResponse> save(@RequestBody InsertFacultyDto fac) {
		try {
			facultyService.save(fac);
			return new ResponseEntity<>(
					new HTTPStatusResponse(HttpStatus.CREATED.value(), "Data Insertion Done Successfully", fac),
					HttpStatus.CREATED);
		} catch (ServiceException e) {
			return new ResponseEntity<>(
					new HTTPStatusResponse(HttpStatus.BAD_REQUEST.value(), "One or More Fields Missing", null),
					HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/faculty")
	public ResponseEntity<HTTPStatusResponse> update(@RequestBody InsertFacultyDto fac) {
		try {
			facultyService.save(fac);
			return new ResponseEntity<>(
					new HTTPStatusResponse(HttpStatus.OK.value(), "Data Updation Done Successfully", fac),
					HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(
					new HTTPStatusResponse(HttpStatus.BAD_REQUEST.value(), "One or More Fields Missing", null),
					HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/faculty/{id}")
	public ResponseEntity<HTTPStatusResponse> delete(@PathVariable Long id) {
		try {
			facultyService.delete(id);
			return new ResponseEntity<>(
					new HTTPStatusResponse(HttpStatus.OK.value(), "Record " + id + " Deleted Successfully", id),
					HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(new HTTPStatusResponse(HttpStatus.NOT_FOUND.value(), "Cannot Find Id", id),
					HttpStatus.NOT_FOUND);
		}

	}

}

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

import com.revature.organization.dto.InsertFacultyDto;
import com.revature.organization.exception.BadResponse;
import com.revature.organization.exception.NotFound;
import com.revature.organization.exception.ResponseEntity;
import com.revature.organization.model.Faculty;
import com.revature.organization.service.FacultyService;

@RestController
@RequestMapping("/core")
@CrossOrigin(origins = "*")
public class FacultyController {
	@Autowired
	private FacultyService facultyService;

	@GetMapping("/faculty")
	public ResponseEntity get() {
		try {
			List<Faculty> faculty = facultyService.get();
			return new ResponseEntity(HttpStatus.OK.value(), "The Records in the Table are Displayed Below", faculty);
		} catch (NotFound e) {
			return new ResponseEntity(HttpStatus.NOT_FOUND.value(), "Unable to get records!!DB Empty", null);
		}

	}

	@GetMapping("/faculty/institution/{inst_id}")
	public ResponseEntity getbyInst(@PathVariable Long inst_id) {
		try {
			List<Faculty> faculty = facultyService.getByInstitution(inst_id);
			return new ResponseEntity(HttpStatus.OK.value(), "The Record with " + inst_id + " is Displayed Below",
					faculty);
		} catch (NotFound e) {
			return new ResponseEntity(HttpStatus.NOT_FOUND.value(), "Cannot Find Data with Id " + inst_id, inst_id);
		}

	}

	@GetMapping("/faculty/{id}")
	public ResponseEntity get(@PathVariable Long id) {
		try {
			Faculty facObj = facultyService.get(id);
			return new ResponseEntity(HttpStatus.OK.value(), "The Record with " + id + " is Displayed Below", facObj);
		} catch (NotFound e) {
			return new ResponseEntity(HttpStatus.NOT_FOUND.value(), "Cannot Find Data with Id " + id, id);
		}
	}

	@PostMapping("/faculty")
	public ResponseEntity save(@RequestBody InsertFacultyDto fac) {
		try {
			facultyService.save(fac);
			return new ResponseEntity(HttpStatus.CREATED.value(), "Data Insertion Done Successfully", fac);
		} catch (BadResponse e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST.value(), "One or More Fields Missing", null);
		}
	}

	@PutMapping("/faculty")
	public ResponseEntity update(@RequestBody InsertFacultyDto fac) {
		try {
			facultyService.save(fac);
			return new ResponseEntity(HttpStatus.CREATED.value(), "Data Updation Done Successfully", fac);
		} catch (BadResponse e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST.value(), "One or More Fields Missing", null);
		}
	}

	@DeleteMapping("/faculty/{id}")
	public ResponseEntity delete(@PathVariable Long id) {
		try {
			facultyService.delete(id);
			return new ResponseEntity(HttpStatus.OK.value(), "Record " + id + " Deleted Successfully", id);
		} catch (NotFound e) {
			return new ResponseEntity(HttpStatus.NOT_FOUND.value(), "Cannot Find Id", id);
		}

	}

}

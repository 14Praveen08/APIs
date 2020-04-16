package com.revature.organization.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.organization.exception.HTTPStatusResponse;
import com.revature.organization.exception.ServiceException;
import com.revature.organization.model.Department;
import com.revature.organization.service.DeptService;

@RestController
@RequestMapping("/core")
@CrossOrigin(origins = "*")
public class DepartmentController {

	@Autowired
	private DeptService deptService;

	@GetMapping("/department")
	public ResponseEntity<HTTPStatusResponse> getDepartment() throws Exception {
		try {
			List<Department> dept = deptService.get();
			return new ResponseEntity<>(
					new HTTPStatusResponse(HttpStatus.OK.value(), "The Records in the Table are Displayed Below", dept),
					HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(
					new HTTPStatusResponse(HttpStatus.NOT_FOUND.value(), "Unable to get records!!DB Empty", null),
					HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/department/{id}")
	public ResponseEntity<HTTPStatusResponse> getRolesById(@PathVariable Long id) throws Exception {
		try {
			Department dept = deptService.get(id);
			return new ResponseEntity<>(new HTTPStatusResponse(HttpStatus.OK.value(),
					"The Record with " + id + " is Displayed Below", dept), HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(
					new HTTPStatusResponse(HttpStatus.NOT_FOUND.value(), "Cannot Find Data with Id " + id, id),
					HttpStatus.NOT_FOUND);
		}

	}

}

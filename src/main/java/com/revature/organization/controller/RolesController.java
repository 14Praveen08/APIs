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

import com.revature.organization.exception.DBException;
import com.revature.organization.exception.HTTPStatusResponse;
import com.revature.organization.exception.ServiceException;
import com.revature.organization.model.Roles;
import com.revature.organization.service.RolesService;

@RestController
@RequestMapping("/core")
@CrossOrigin(origins = "*")
public class RolesController {
	@Autowired
	private RolesService rolesService;

	@GetMapping("/role")
	public ResponseEntity<HTTPStatusResponse> getRoles() throws Exception {
		try {
			List<Roles> role = rolesService.get();
			return new ResponseEntity<>(
					new HTTPStatusResponse(HttpStatus.OK.value(), "The Records in the Table are Displayed Below", role),
					HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(
					new HTTPStatusResponse(HttpStatus.NOT_FOUND.value(), "Unable to get records!!DB Empty", null),
					HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/role/{id}")
	public ResponseEntity<HTTPStatusResponse> getRolesById(@PathVariable Long id) throws Exception {
		try {
			Roles role = rolesService.get(id);
			return new ResponseEntity<>(new HTTPStatusResponse(HttpStatus.OK.value(),
					"The Record with " + id + " is Displayed Below", role), HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(
					new HTTPStatusResponse(HttpStatus.NOT_FOUND.value(), "Cannot Find Data with Id " + id, id),
					HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping("/role")
	public ResponseEntity<HTTPStatusResponse> save(@RequestBody Roles role) throws Exception {
		try {
			rolesService.save(role);
			return new ResponseEntity<>(
					new HTTPStatusResponse(HttpStatus.CREATED.value(), "Data Insertion Done Successfully", role),
					HttpStatus.CREATED);
		} catch (ServiceException e) {
			return new ResponseEntity<>(
					new HTTPStatusResponse(HttpStatus.BAD_REQUEST.value(), "One or More Fields Missing", null),
					HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/role")
	public ResponseEntity<HTTPStatusResponse> update(@RequestBody Roles role) throws DBException {
		try {
			rolesService.save(role);
			return new ResponseEntity<>(
					new HTTPStatusResponse(HttpStatus.OK.value(), "Data Updation Done Successfully", role),
					HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(new HTTPStatusResponse(HttpStatus.BAD_REQUEST.value(),
					"Something Went Wrong!!Could not Update", null), HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping("/role/{id}")
	public ResponseEntity<HTTPStatusResponse> deleteRole(@PathVariable Long id) throws ServiceException {
		try {
			rolesService.delete(id);
			return new ResponseEntity<>(
					new HTTPStatusResponse(HttpStatus.OK.value(), "Record " + id + " Deleted Successfully", id),
					HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(new HTTPStatusResponse(HttpStatus.NOT_FOUND.value(), "Cannot Find Id", id),
					HttpStatus.NOT_FOUND);
		}

	}
}

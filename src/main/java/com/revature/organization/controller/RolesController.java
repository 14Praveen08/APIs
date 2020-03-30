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

import com.revature.organization.exception.BadResponse;
import com.revature.organization.exception.DBException;
import com.revature.organization.exception.NotFound;
import com.revature.organization.exception.ResponseEntity;
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
	public ResponseEntity getRoles() throws ServiceException {
		try {
			List<Roles> role = rolesService.get();
			return new ResponseEntity(HttpStatus.OK.value(), "The Records in the Table are Displayed Below", role);
		} catch (NotFound e) {
			return new ResponseEntity(HttpStatus.NOT_FOUND.value(), "Unable to get records!!DB Empty", null);
		}
	}

	@GetMapping("/role/{id}")
	public ResponseEntity getRolesById(@PathVariable Long id) throws NotFound {
		try {
			Roles role = rolesService.get(id);
			return new ResponseEntity(HttpStatus.OK.value(), "The Record with " + id + " is Displayed Below", role);
		} catch (NotFound e) {
			return new ResponseEntity(HttpStatus.NOT_FOUND.value(), "Cannot Find Data with Id " + id, id);
		}

	}

	@PostMapping("/role")
	public ResponseEntity save(@RequestBody Roles role) throws DBException, BadResponse {
		try {
			rolesService.save(role);
			return new ResponseEntity(HttpStatus.CREATED.value(), "Data Insertion Done Successfully", role);
		} catch (BadResponse e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST.value(), "One or More Fields Missing", null);
		}
	}

	@PutMapping("/role")
	public ResponseEntity update(@RequestBody Roles role) throws DBException, BadResponse {
		try {
			rolesService.save(role);
			return new ResponseEntity(HttpStatus.OK.value(), "Data Updation Done Successfully", role);
		} catch (BadResponse e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST.value(), "Something Went Wrong!!Could not Update", null);
		}

	}

	@DeleteMapping("/role/{id}")
	public ResponseEntity deleteRole(@PathVariable Long id) throws ServiceException {
		try {
			rolesService.delete(id);
			return new ResponseEntity(HttpStatus.OK.value(), "Record " + id + " Deleted Successfully", id);
		} catch (NotFound e) {
			return new ResponseEntity(HttpStatus.NOT_FOUND.value(), "Cannot Find Id", id);
		}

	}
}

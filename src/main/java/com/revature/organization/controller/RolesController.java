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

import com.revature.organization.dto.ResponseEntity;
import com.revature.organization.exception.BadResponse;
import com.revature.organization.exception.DBException;
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
	public List<Roles> getRoles() throws ServiceException {
		return rolesService.get();
	}

	@GetMapping("/role/{id}")
	public Roles getRolesById(@PathVariable Long id) throws ServiceException {
		return rolesService.get(id);

	}

	@PostMapping("/role")
	public ResponseEntity save(@RequestBody Roles role) throws DBException, BadResponse {
		try {
			rolesService.save(role);
			return new ResponseEntity(HttpStatus.OK.value(), "Data Insert Success", role);
		} catch (BadResponse e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST.value(), " One or More Fields Missing", null);
		}
	}

	@PutMapping("/role")
	public Roles update(@RequestBody Roles role) throws DBException, BadResponse {
		rolesService.save(role);
		return role;

	}

	@DeleteMapping("/role/{id}")
	public String deleteRole(@PathVariable Long id) throws ServiceException {
		rolesService.delete(id);
		return "Role Deleted with id:" + id;
	}
}

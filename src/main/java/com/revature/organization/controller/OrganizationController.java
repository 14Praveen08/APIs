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
import com.revature.organization.model.Organization;
import com.revature.organization.service.OrganizationService;

@RestController
@RequestMapping("/core")
@CrossOrigin(origins = "*")
public class OrganizationController {
	@Autowired
	private OrganizationService organizationService;

	@GetMapping("/organization")
	public ResponseEntity get() throws ServiceException {
		try {
			List<Organization> organization = organizationService.get();
			return new ResponseEntity(HttpStatus.OK.value(), "The Records in the Table are Displayed Below",
					organization);
		} catch (NotFound e) {
			return new ResponseEntity(HttpStatus.NOT_FOUND.value(), "Unable to get records!!DB Empty", null);
		}

	}

	@GetMapping("/organization/active")
	public List<Organization> getActive() throws ServiceException {
		return organizationService.getActive();

	}

	@PostMapping("/organization")
	public ResponseEntity save(@RequestBody Organization organizationObj) throws DBException {
		try {
			organizationService.save(organizationObj);
			return new ResponseEntity(HttpStatus.CREATED.value(), "Data Insertion Done Successfully", organizationObj);
		} catch (BadResponse e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST.value(), "One or More Fields Missing", null);
		}

	}

	@GetMapping("/organization/{id}")
	public ResponseEntity get(@PathVariable Long id) throws ServiceException, NotFound {
		try {
			Organization organizationObj = organizationService.get(id);
			return new ResponseEntity(HttpStatus.OK.value(), "The Record with " + id + " is Displayed Below",
					organizationObj);
		} catch (NotFound e) {
			return new ResponseEntity(HttpStatus.NOT_FOUND.value(), "Cannot Find Data with Id " + id, id);
		}
	}

	@DeleteMapping("/organization/{id}")
	public ResponseEntity delete(@PathVariable Long id) throws NotFound {
		try {
			organizationService.delete(id);
			return new ResponseEntity(HttpStatus.OK.value(), "Record " + id + " Deleted Successfully", id);
		} catch (NotFound e) {
			return new ResponseEntity(HttpStatus.NOT_FOUND.value(), "Cannot Find Id", id);
		}
	}

	@PutMapping("/organization")
	public ResponseEntity update(@RequestBody Organization organizationObj) throws BadResponse {
		try {
			organizationService.save(organizationObj);
			return new ResponseEntity(HttpStatus.OK.value(), "Data Updation Done Successfully", organizationObj);
		} catch (BadResponse e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST.value(), "Something Went Wrong!!Could not Update", null);
		}
	}

	@PutMapping("/organization/status/{id}")
	public void changeStatus(@PathVariable Long id) {
		organizationService.changeStatus(id);

	}

}

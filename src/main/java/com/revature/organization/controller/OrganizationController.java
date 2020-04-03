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

import com.revature.organization.exception.HTTPStatusResponse;
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
	public ResponseEntity<HTTPStatusResponse> get() {
		try {
			List<Organization> organization = organizationService.get();
			return new ResponseEntity<>(new HTTPStatusResponse(HttpStatus.OK.value(),
					"The Records in the Table are Displayed Below", organization), HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(
					new HTTPStatusResponse(HttpStatus.NOT_FOUND.value(), "Unable to get records!!DB Empty", null),
					HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/organization/active")
	public ResponseEntity<HTTPStatusResponse> getActive() {
		try {
			List<Organization> organization = organizationService.getActive();
			return new ResponseEntity<>(new HTTPStatusResponse(HttpStatus.OK.value(),
					"The Records in the Table are Displayed Below", organization), HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(
					new HTTPStatusResponse(HttpStatus.NOT_FOUND.value(), "Unable to get records!!DB Empty", null),
					HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping("/organization")
	public ResponseEntity<HTTPStatusResponse> save(@RequestBody Organization organizationObj) {
		try {
			organizationService.save(organizationObj);
			return new ResponseEntity<>(new HTTPStatusResponse(HttpStatus.CREATED.value(),
					"Data Insertion Done Successfully", organizationObj), HttpStatus.CREATED);
		} catch (ServiceException e) {
			return new ResponseEntity<>(
					new HTTPStatusResponse(HttpStatus.BAD_REQUEST.value(), "One or More Fields Missing", null),
					HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/organization/{id}")
	public ResponseEntity<HTTPStatusResponse> get(@PathVariable Long id) {
		try {
			Organization organizationObj = organizationService.get(id);
			return new ResponseEntity<>(new HTTPStatusResponse(HttpStatus.OK.value(),
					"The Record with " + id + " is Displayed Below", organizationObj), HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(
					new HTTPStatusResponse(HttpStatus.NOT_FOUND.value(), "Cannot Find Data with Id " + id, id),
					HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/organization/{id}")
	public ResponseEntity<HTTPStatusResponse> delete(@PathVariable Long id) {
		try {
			organizationService.delete(id);
			return new ResponseEntity<>(
					new HTTPStatusResponse(HttpStatus.OK.value(), "Record " + id + " Deleted Successfully", id),
					HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(new HTTPStatusResponse(HttpStatus.NOT_FOUND.value(), "Cannot Find Id", id),
					HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/organization")
	public ResponseEntity<HTTPStatusResponse> update(@RequestBody Organization organizationObj) {
		try {
			organizationService.save(organizationObj);
			return new ResponseEntity<>(
					new HTTPStatusResponse(HttpStatus.OK.value(), "Data Updation Done Successfully", organizationObj),
					HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(new HTTPStatusResponse(HttpStatus.BAD_REQUEST.value(),
					"Something Went Wrong!!Could not Update", null), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/organization/status/{id}")
	public ResponseEntity<HTTPStatusResponse> changeStatus(@PathVariable Long id) {
		try {
			organizationService.changeStatus(id);
			return new ResponseEntity<>(new HTTPStatusResponse(HttpStatus.OK.value(), "Status Changed", null),
					HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(new HTTPStatusResponse(HttpStatus.BAD_REQUEST.value(),
					"Something Went Wrong!!Status Could Not Be Changed", null), HttpStatus.BAD_REQUEST);
		}

	}

}

package com.revature.organization.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.organization.dto.InsertFacultyDto;
import com.revature.organization.exception.ServiceException;
import com.revature.organization.model.Faculty;
import com.revature.organization.model.Organization;
import com.revature.organization.model.Roles;
import com.revature.organization.service.FacultyService;

class FacultyControllerTest {

	private MockMvc mockmvc;

	private ObjectMapper om = new ObjectMapper();

	@InjectMocks
	FacultyController facultycontroller;

	@Mock
	FacultyService facultyService;

	@Spy
	List<Faculty> facList = new ArrayList<Faculty>();

	private Organization org;

	private Roles roles;

	private long id;

	private Long role_id;

	private Long inst_id;

	@BeforeEach
	void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockmvc = MockMvcBuilders.standaloneSetup(facultycontroller).build();
		facList = getFacList();
	}

	private List<Faculty> getFacList() {
		Faculty faculty = new Faculty();

		faculty.setId((long) 1);
		faculty.setEmployee_id(1);
		faculty.setFirst_name("abc");
		faculty.setLast_name("def");
		faculty.setOrg(org);
		faculty.setRoles(roles);
		String str = "1985-03-31";
		Date date = Date.valueOf(str);
		faculty.setDob(date);
		faculty.setEmail("abc@gmail.com");
		faculty.setMobile_no((long) 998451233);

		facList.add(faculty);
		return facList;
	}

	@Test
	void testGet() throws Exception {
		when(facultyService.get()).thenReturn(facList);
		this.mockmvc.perform(get("/core/faculty")).andExpect(status().isOk());
	}

	@Test
	void testGetExpectFailure() throws Exception {
		doThrow(ServiceException.class).when(facultyService).get();
		this.mockmvc.perform(get("/core/faculty")).andExpect(status().isNotFound());
	}

	@Test
	void testGetbyInst() throws Exception {
		when(facultyService.getByInstitution(id)).thenReturn(facList);
		this.mockmvc.perform(get("/core/faculty/institution/{id}", 1)).andExpect(status().isOk());
	}

	@Test
	void testGetbyInstExpectFailure() throws Exception {
		id = (long) 1;
		doThrow(ServiceException.class).when(facultyService).getByInstitution(id);
		this.mockmvc.perform(get("/core/faculty/institution/{id}", 1)).andExpect(status().isNotFound());
	}

	@Test
	void testGetLong() throws Exception {
		Faculty faculty = new Faculty();
		faculty.setEmployee_id(1);
		faculty.setFirst_name("abc");
		faculty.setLast_name("def");
		faculty.setOrg(org);
		faculty.setRoles(roles);
		String str = "1985-03-31";
		Date date = Date.valueOf(str);
		faculty.setDob(date);
		faculty.setEmail("abc@gmail.com");
		faculty.setMobile_no((long) 998451233);
		when(facultyService.get(id)).thenReturn(faculty);
		this.mockmvc.perform(get("/core/faculty/{id}", 1)).andExpect(status().isOk());
	}

	@Test
	void testGetLongExpectFailure() throws Exception {
		id = (long) 1;
		doThrow(ServiceException.class).when(facultyService).get(id);
		this.mockmvc.perform(get("/core/faculty/{id}", 1)).andExpect(status().isNotFound());
	}

	@Test
	void testGetByRole() throws Exception {
		when(facultyService.getByRole(role_id)).thenReturn(facList);
		this.mockmvc.perform(get("/core/faculty/role/{role_id}", 1)).andExpect(status().isOk());
	}

	@Test
	void testGetByRoleExpectFailure() throws Exception {
		role_id = (long) 1;
		doThrow(ServiceException.class).when(facultyService).getByRole(role_id);
		this.mockmvc.perform(get("/core/faculty/role/{role_id}", 1)).andExpect(status().isNotFound());
	}

	@Test
	void testGetByInstRole() throws Exception {
		when(facultyService.getByInstRole(inst_id, role_id)).thenReturn(facList);
		this.mockmvc.perform(get("/core/faculty/instrole/{inst_id}/{role_id}", 1, 1)).andExpect(status().isOk());
	}

	@Test
	void testGetByInstRoleExpectFailure() throws Exception {
		role_id = (long) 1;
		inst_id = (long) 1;
		doThrow(ServiceException.class).when(facultyService).getByInstRole(inst_id, role_id);
		this.mockmvc.perform(get("/core/faculty/instrole/{inst_id}/{role_id}", 1, 1)).andExpect(status().isNotFound());
	}

	@Test
	void testSave() throws Exception {
		InsertFacultyDto faculty = new InsertFacultyDto();
		faculty.setId((long) 1);
		faculty.setEmployee_id(1);
		faculty.setFirst_name("abc");
		faculty.setLast_name("def");
		faculty.setInstitution_id((long) 1);
		faculty.setRole_id((long) 3);
		String str = "1985-03-31";
		Date date = Date.valueOf(str);
		faculty.setDob(date);
		faculty.setEmail("abc@gmail.com");
		faculty.setMobile_no((long) 998451233);
		doNothing().when(facultyService).save(faculty);
		String orgJson = om.writeValueAsString(faculty);
		MvcResult result = this.mockmvc
				.perform(post("/core/faculty").contentType(MediaType.APPLICATION_JSON_VALUE).content(orgJson))
				.andExpect(status().isCreated()).andReturn();

	}

	@Test
	void testSaveExpectFailure() throws Exception {
		InsertFacultyDto faculty = new InsertFacultyDto();
		doThrow(ServiceException.class).when(facultyService).save(faculty);
		this.mockmvc.perform(post("/core/faculty")).andExpect(status().isBadRequest());
	}

	@Test
	void testUpdate() throws Exception {
		InsertFacultyDto faculty = new InsertFacultyDto();
		faculty.setId((long) 1);
		faculty.setEmployee_id(1);
		faculty.setFirst_name("abc");
		faculty.setLast_name("def");
		faculty.setInstitution_id((long) 1);
		faculty.setRole_id((long) 3);
		String str = "1985-03-31";
		Date date = Date.valueOf(str);
		faculty.setDob(date);
		faculty.setEmail("abc@gmail.com");
		faculty.setMobile_no((long) 998451233);
		doNothing().when(facultyService).save(faculty);
		String orgJson = om.writeValueAsString(faculty);
		MvcResult result = this.mockmvc
				.perform(put("/core/faculty").contentType(MediaType.APPLICATION_JSON_VALUE).content(orgJson))
				.andExpect(status().isOk()).andReturn();

	}

	@Test
	void testUpdateExpectFailure() throws Exception {
		InsertFacultyDto faculty = new InsertFacultyDto();
		doThrow(ServiceException.class).when(facultyService).save(faculty);
		this.mockmvc.perform(post("/core/faculty")).andExpect(status().isBadRequest());
	}

	@Test
	void testDelete() throws Exception {
		Faculty faculty = new Faculty();
		id = (long) 1;
		when(facultyService.get(id)).thenReturn(faculty);
		this.mockmvc.perform(delete("/core/faculty/{id}", 1)).andExpect(status().isOk());
	}

	@Test
	void testDeleteExpectFailure() throws Exception {
		id = (long) 1;
		doThrow(ServiceException.class).when(facultyService).delete(id);
		this.mockmvc.perform(delete("/core/faculty/{id}", 1)).andExpect(status().isNotFound());
	}

}

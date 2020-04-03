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
import com.revature.organization.dto.InsertDTO;
import com.revature.organization.exception.ServiceException;
import com.revature.organization.model.Organization;
import com.revature.organization.model.Roles;
import com.revature.organization.model.student;
import com.revature.organization.service.studentservice;

class studentcontrollerTest {
	private MockMvc mockmvc;

	private ObjectMapper om = new ObjectMapper();

	@InjectMocks
	studentcontroller studentController;

	@Mock
	studentservice studentService;

	@Spy
	List<student> studList = new ArrayList<student>();

	private Organization org;

	private Roles roles;

	private int id;

	private int instid;

	private int year;

	@BeforeEach
	void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockmvc = MockMvcBuilders.standaloneSetup(studentController).build();
		studList = getStudList();
	}

	private List<student> getStudList() {
		student stud = new student();

		stud.setId(1);
		stud.setOrg(org);
		stud.setRedgno((long) 310816);
		stud.setFname("ABC");
		stud.setLname("DEF");
		String str = "1998-03-31";
		Date date = Date.valueOf(str);
		stud.setDob(date);
		stud.setYear(4);
		stud.setMobileno((long) 994016369);
		stud.setEmail("abc@gmail.com");

		studList.add(stud);
		return studList;
	}

	@Test
	void testGet() throws Exception {
		when(studentService.get()).thenReturn(studList);
		this.mockmvc.perform(get("/core/getstud")).andExpect(status().isOk());
	}

	@Test
	void testGetExpectFailure() throws Exception {
		doThrow(ServiceException.class).when(studentService).get();
		this.mockmvc.perform(get("/core/getstud")).andExpect(status().isNotFound());
	}

	@Test
	void testGetstudbyInst() throws Exception {
		when(studentService.getstudbyInst(instid)).thenReturn(studList);
		this.mockmvc.perform(get("/core/getstud/institution/{institutionid}", 1)).andExpect(status().isOk());
	}

	@Test
	void testGetstudbyInstExpectFailure() throws Exception {
		instid = 1;
		doThrow(ServiceException.class).when(studentService).getstudbyInst(instid);
		this.mockmvc.perform(get("/core/getstud/institution/{institutionid}", 1)).andExpect(status().isNotFound());
	}

	@Test
	void testGetInt() throws Exception {
		student stud = new student();
		stud.setId(1);
		stud.setOrg(org);
		stud.setRedgno((long) 310816);
		stud.setFname("ABC");
		stud.setLname("DEF");
		String str = "1998-03-31";
		Date date = Date.valueOf(str);
		stud.setDob(date);
		stud.setYear(4);
		stud.setMobileno((long) 994016369);
		stud.setEmail("abc@gmail.com");
		when(studentService.get(id)).thenReturn(stud);
		this.mockmvc.perform(get("/core/getstud/{id}", 1)).andExpect(status().isOk());
	}

	@Test
	void testGetIntExpectFailure() throws Exception {
		id = 1;
		doThrow(ServiceException.class).when(studentService).get(id);
		this.mockmvc.perform(get("/core/getstud/{id}", 1)).andExpect(status().isNotFound());
	}

	@Test
	void testGetstudbyInstYear() throws Exception {
		when(studentService.getstudbyInstYear(instid, year)).thenReturn(studList);
		this.mockmvc.perform(get("/core/getstud/instyearwise/{institutionid}/{year}", 1, 1)).andExpect(status().isOk());
	}

	@Test
	void testGetstudbyInstYearExpectFailure() throws Exception {
		instid = 1;
		year = 1;
		doThrow(ServiceException.class).when(studentService).getstudbyInstYear(instid, year);
		this.mockmvc.perform(get("/core/getstud/instyearwise/{institutionid}/{year}", 1, 1))
				.andExpect(status().isNotFound());
	}

	@Test
	void testGetstudbyYear() throws Exception {
		when(studentService.getstudbyYear(year)).thenReturn(studList);
		this.mockmvc.perform(get("/core/getstud/year/{year}", 1)).andExpect(status().isOk());
	}

	@Test
	void testGetstudbyYearExpectFailure() throws Exception {
		year = 1;
		doThrow(ServiceException.class).when(studentService).getstudbyYear(year);
		this.mockmvc.perform(get("/core/getstud/year/{year}", 1)).andExpect(status().isNotFound());
	}

	@Test
	void testSave() throws Exception {
		InsertDTO stud = new InsertDTO();
		stud.setId(1);
		stud.setInstitutionid((long) 1);
		stud.setRedgno((long) 310816);
		stud.setFname("ABC");
		stud.setLname("DEF");
		String str = "1998-03-31";
		Date date = Date.valueOf(str);
		stud.setDob(date);
		stud.setYear(4);
		stud.setMobileno((long) 994016369);
		stud.setEmail("abc@gmail.com");
		doNothing().when(studentService).save(stud);
		String orgJson = om.writeValueAsString(stud);
		MvcResult result = this.mockmvc
				.perform(post("/core/insert").contentType(MediaType.APPLICATION_JSON_VALUE).content(orgJson))
				.andExpect(status().isCreated()).andReturn();

	}

	@Test
	void testSaveExpectFailure() throws Exception {
		InsertDTO stud = new InsertDTO();
		doThrow(ServiceException.class).when(studentService).save(stud);
		this.mockmvc.perform(post("/core/insert")).andExpect(status().isBadRequest());
	}

	@Test
	void testUpdate() throws Exception {
		InsertDTO stud = new InsertDTO();
		stud.setId(1);
		stud.setInstitutionid((long) 1);
		stud.setRedgno((long) 310816);
		stud.setFname("ABC");
		stud.setLname("DEF");
		String str = "1998-03-31";
		Date date = Date.valueOf(str);
		stud.setDob(date);
		stud.setYear(4);
		stud.setMobileno((long) 994016369);
		stud.setEmail("abc@gmail.com");
		doNothing().when(studentService).save(stud);
		String orgJson = om.writeValueAsString(stud);
		MvcResult result = this.mockmvc
				.perform(put("/core/update").contentType(MediaType.APPLICATION_JSON_VALUE).content(orgJson))
				.andExpect(status().isOk()).andReturn();
	}

	@Test
	void testUpdateExpectFailure() throws Exception {
		InsertDTO stud = new InsertDTO();
		doThrow(ServiceException.class).when(studentService).save(stud);
		this.mockmvc.perform(put("/core/update")).andExpect(status().isBadRequest());
	}

	@Test
	void testDelete() throws Exception {
		student stud = new student();
		id = 1;
		when(studentService.get(id)).thenReturn(stud);
		this.mockmvc.perform(delete("/core/delete/{id}", 1)).andExpect(status().isOk());
	}

	@Test
	void testDeleteExpectFailure() throws Exception {
		id = 1;
		doThrow(ServiceException.class).when(studentService).delete(id);
		this.mockmvc.perform(delete("/core/delete/{id}", 1)).andExpect(status().isNotFound());
	}

}

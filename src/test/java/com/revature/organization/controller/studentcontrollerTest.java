package com.revature.organization.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.organization.dto.InsertDTO;
import com.revature.organization.exception.ServiceException;
import com.revature.organization.model.Organization;
import com.revature.organization.model.student;
import com.revature.organization.service.studentservice;

@ExtendWith({ RestDocumentationExtension.class, SpringExtension.class })
@WebAppConfiguration
@AutoConfigureRestDocs
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

	private int id;

	private int instid;

	private int year;

	private Long deptid;

	@BeforeEach
	void setup(RestDocumentationContextProvider restDocumentation) throws Exception {
		MockitoAnnotations.initMocks(this);
		mockmvc = MockMvcBuilders.standaloneSetup(studentController)
				.apply(documentationConfiguration(restDocumentation)).build();
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
	void testGetStudent() throws Exception {
		when(studentService.get()).thenReturn(studList);
		this.mockmvc.perform(get("/core/getstud")).andDo(print()).andExpect(status().isOk())
				.andDo(document("{methodName}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())));
	}

	@Test
	void testGetStudentExpectFailure() throws Exception {
		doThrow(ServiceException.class).when(studentService).get();
		this.mockmvc.perform(get("/core/getstud")).andExpect(status().isNotFound());
	}

	@Test
	void testGetStudentByInst() throws Exception {
		when(studentService.getstudbyInst(instid)).thenReturn(studList);
		this.mockmvc.perform(get("/core/getstud/institution/{institutionid}", 1)).andDo(print())
				.andExpect(status().isOk())
				.andDo(document("{methodName}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())));
	}

	@Test
	void testGetStudentByInstExpectFailure() throws Exception {
		instid = 1;
		doThrow(ServiceException.class).when(studentService).getstudbyInst(instid);
		this.mockmvc.perform(get("/core/getstud/institution/{institutionid}", 1)).andExpect(status().isNotFound());
	}

	@Test
	void testGetStudentByDepartment() throws Exception {
		when(studentService.getstudbyDepartment(deptid)).thenReturn(studList);
		this.mockmvc.perform(get("/core/getstud/department/{deptid}", 1)).andDo(print()).andExpect(status().isOk())
				.andDo(document("{methodName}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())));
	}

	@Test
	void testGetStudentByDepartmentExpectFailure() throws Exception {
		deptid = (long) 1;
		doThrow(ServiceException.class).when(studentService).getstudbyDepartment(deptid);
		this.mockmvc.perform(get("/core/getstud/department/{deptid}", 1)).andExpect(status().isNotFound());
	}

	@Test
	void testGetStudentInt() throws Exception {
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
		this.mockmvc.perform(get("/core/getstud/{id}", 1)).andDo(print()).andExpect(status().isOk())
				.andDo(document("{methodName}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())));
	}

	@Test
	void testGetStudentIntExpectFailure() throws Exception {
		id = 1;
		doThrow(ServiceException.class).when(studentService).get(id);
		this.mockmvc.perform(get("/core/getstud/{id}", 1)).andExpect(status().isNotFound());
	}

	@Test
	void testGetStudentByInstDept() throws Exception {
		when(studentService.getstudbyInstDept(instid, deptid)).thenReturn(studList);
		this.mockmvc.perform(get("/core/getstud/instdeptwise/{instid}/{deptid}", 1, 1)).andDo(print())
				.andExpect(status().isOk())
				.andDo(document("{methodName}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())));
	}

	@Test
	void testGetStudentByInstDeptExpectFailure() throws Exception {
		instid = 1;
		deptid = (long) 1;
		doThrow(ServiceException.class).when(studentService).getstudbyInstDept(instid, deptid);
		this.mockmvc.perform(get("/core/getstud/instdeptwise/{instid}/{deptid}", 1, 1))
				.andExpect(status().isNotFound());
	}

	@Test
	void testGetStudentByDeptYear() throws Exception {
		when(studentService.getstudbyDeptYear(deptid, year)).thenReturn(studList);
		this.mockmvc.perform(get("/core/getstud/deptyearwise/{deptid}/{year}", 1, 1)).andDo(print())
				.andExpect(status().isOk())
				.andDo(document("{methodName}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())));
	}

	@Test
	void testGetStudentByDeptYearExpectFailure() throws Exception {
		deptid = (long) 1;
		year = 1;
		doThrow(ServiceException.class).when(studentService).getstudbyDeptYear(deptid, year);
		this.mockmvc.perform(get("/core/getstud/deptyearwise/{deptid}/{year}", 1, 1)).andExpect(status().isNotFound());
	}

	@Test
	void testGetStudentByInstDeptYear() throws Exception {
		when(studentService.getstudbyInstYearDept(instid, year, deptid)).thenReturn(studList);
		this.mockmvc.perform(get("/core/getstud/instyeardeptwise/{instid}/{year}/{deptid}", 1, 1, 1)).andDo(print())
				.andExpect(status().isOk())
				.andDo(document("{methodName}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())));
	}

	@Test
	void testGetStudentByInstDeptYearExpectFailure() throws Exception {
		instid = 1;
		deptid = (long) 1;
		year = 1;
		doThrow(ServiceException.class).when(studentService).getstudbyInstYearDept(instid, year, deptid);
		this.mockmvc.perform(get("/core/getstud/instyeardeptwise/{instid}/{year}/{deptid}", 1, 1, 1))
				.andExpect(status().isNotFound());
	}

	@Test
	void testGetStudentByInstYear() throws Exception {
		when(studentService.getstudbyInstYear(instid, year)).thenReturn(studList);
		this.mockmvc.perform(get("/core/getstud/instyearwise/{institutionid}/{year}", 1, 1)).andDo(print())
				.andExpect(status().isOk())
				.andDo(document("{methodName}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())));
	}

	@Test
	void testGetStudentByInstYearExpectFailure() throws Exception {
		instid = 1;
		year = 1;
		doThrow(ServiceException.class).when(studentService).getstudbyInstYear(instid, year);
		this.mockmvc.perform(get("/core/getstud/instyearwise/{institutionid}/{year}", 1, 1))
				.andExpect(status().isNotFound());
	}

	@Test
	void testGetStudentByYear() throws Exception {
		when(studentService.getstudbyYear(year)).thenReturn(studList);
		this.mockmvc.perform(get("/core/getstud/year/{year}", 1)).andDo(print()).andExpect(status().isOk())
				.andDo(document("{methodName}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())));
	}

	@Test
	void testGetStudentByYearExpectFailure() throws Exception {
		year = 1;
		doThrow(ServiceException.class).when(studentService).getstudbyYear(year);
		this.mockmvc.perform(get("/core/getstud/year/{year}", 1)).andExpect(status().isNotFound());
	}

	@Test
	void testStudentSave() throws Exception {
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
		this.mockmvc.perform(post("/core/insert").contentType(MediaType.APPLICATION_JSON_VALUE).content(orgJson))
				.andDo(print()).andExpect(status().isCreated())
				.andDo(document("{methodName}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())));

	}

	@Test
	void testStudentSaveExpectFailure() throws Exception {
		InsertDTO stud = new InsertDTO();
		doThrow(ServiceException.class).when(studentService).save(stud);
		this.mockmvc.perform(post("/core/insert")).andExpect(status().isBadRequest());
	}

	@Test
	void testStudentUpdate() throws Exception {
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
		this.mockmvc.perform(put("/core/update").contentType(MediaType.APPLICATION_JSON_VALUE).content(orgJson))
				.andDo(print()).andExpect(status().isOk())
				.andDo(document("{methodName}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())));
	}

	@Test
	void testStudentUpdateExpectFailure() throws Exception {
		InsertDTO stud = new InsertDTO();
		doThrow(ServiceException.class).when(studentService).save(stud);
		this.mockmvc.perform(put("/core/update")).andExpect(status().isBadRequest());
	}

	@Test
	void testStudentDelete() throws Exception {
		student stud = new student();
		id = 1;
		when(studentService.get(id)).thenReturn(stud);
		this.mockmvc.perform(delete("/core/delete/{id}", 1)).andDo(print()).andExpect(status().isOk())
				.andDo(document("{methodName}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())));
	}

	@Test
	void testStudentDeleteExpectFailure() throws Exception {
		id = 1;
		doThrow(ServiceException.class).when(studentService).delete(id);
		this.mockmvc.perform(delete("/core/delete/{id}", 1)).andExpect(status().isNotFound());
	}

}

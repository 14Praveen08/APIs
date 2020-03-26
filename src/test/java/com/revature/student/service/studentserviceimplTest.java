package com.revature.student.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.revature.student.DAO.studentdao;
import com.revature.student.Exception.DBException;
import com.revature.student.Exception.ServiceException;
import com.revature.student.model.Organization;
import com.revature.student.model.student;

class studentserviceimplTest {

	@InjectMocks
	private studentserviceimpl studentservice;
	@Mock
	studentdao studentdao;

	@Spy
	List<student> studentList = new ArrayList<student>();

	private int institutionid;

	private int year;

	private Organization org;

	private int id;

	@BeforeEach
	void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
		studentList = getStudentList();
	}

	@Test
	void testGet() throws DBException, ServiceException {
		when(studentdao.get()).thenReturn(studentList);
		assertNotNull(studentList);
		assertEquals(studentservice.get(), studentList);
	}

	@Test
	void testSave() {
		fail("Not yet implemented");
	}

	@Test
	void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	void testGetstudbyInst() throws DBException, ServiceException {

		when(studentdao.getstudbyInst(institutionid)).thenReturn(studentList);
		assertNotNull(studentList);
		assertEquals(studentservice.getstudbyInst(institutionid), studentList);

	}

	@Test
	void testGetstudbyInstYear() throws DBException, ServiceException {
		when(studentdao.getstudbyInstYear(institutionid, year)).thenReturn(studentList);
		assertNotNull(studentList);
		assertEquals(studentservice.getstudbyInstYear(institutionid, year), studentList);
	}

	@Test
	void testGetstudbyYear() throws DBException, ServiceException {
		when(studentdao.getstudbyYear(year)).thenReturn(studentList);
		assertNotNull(studentList);
		assertEquals(studentservice.getstudbyYear(year), studentList);
	}

	@Test
	void testGetInt() throws DBException, ServiceException {
		student stud = new student();
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
		LocalDateTime cDateTime = LocalDateTime.of(2020, 03, 22, 01, 20, 00);
		stud.setCreatedon(cDateTime);
		LocalDateTime mDateTime = LocalDateTime.of(2020, 03, 22, 01, 30, 20);
		stud.setCreatedon(mDateTime);

		when(studentdao.get(id)).thenReturn(stud);
		assertNotNull(stud);
		assertEquals(studentservice.get(id), stud);

	}

	private List<student> getStudentList() {

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
		LocalDateTime cDateTime = LocalDateTime.of(2020, 03, 22, 01, 20, 00);
		stud.setCreatedon(cDateTime);
		LocalDateTime mDateTime = LocalDateTime.of(2020, 03, 22, 01, 30, 20);
		stud.setCreatedon(mDateTime);

		studentList.add(stud);

		return studentList;
	}

}

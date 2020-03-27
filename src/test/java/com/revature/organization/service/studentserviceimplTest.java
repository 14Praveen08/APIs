package com.revature.organization.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
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

import com.revature.organization.dao.studentdao;
import com.revature.organization.exception.DBException;
import com.revature.organization.exception.ServiceException;
import com.revature.organization.model.Organization;
import com.revature.organization.model.student;

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
		student stud = mock(student.class);
		when(stud.getFname()).thenReturn("ABC");
		when(stud.getLname()).thenReturn("DEF");
		when(stud.getRedgno()).thenReturn((long) 32145678);
		when(stud.getOrg()).thenReturn(org);
		String str = "1998-03-31";
		Date date = Date.valueOf(str);
		when(stud.getDob()).thenReturn(date);
		when(stud.getYear()).thenReturn(3);
		when(stud.getMobileno()).thenReturn((long) 994019744);
		when(stud.getEmail()).thenReturn("abc@gmail.com");
		LocalDateTime cDateTime = LocalDateTime.of(2020, 03, 22, 12, 00, 30);
		when(stud.getCreatedon()).thenReturn(cDateTime);
		LocalDateTime mDateTime = LocalDateTime.of(2020, 03, 22, 12, 05, 45);
		when(stud.getModifiedon()).thenReturn(mDateTime);

		assertEquals("ABC", stud.getFname());
		assertEquals("DEF", stud.getLname());
		assertEquals(32145678, stud.getRedgno());
		assertEquals(org, stud.getOrg());
		assertEquals(date, stud.getDob());
		assertEquals(3, stud.getYear());
		assertEquals(994019744, stud.getMobileno());
		assertEquals("abc@gmail.com", stud.getEmail());
		assertEquals(cDateTime, stud.getCreatedon());
		assertEquals(mDateTime, stud.getModifiedon());

	}

	@Test
	void testDelete() throws DBException {
		student student = new student();
		when(studentdao.get(id)).thenReturn(student);
		assertNotNull(student);
		studentdao.delete(id);
		verify(studentdao).delete(id);
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

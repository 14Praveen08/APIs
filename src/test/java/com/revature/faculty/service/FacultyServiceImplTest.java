package com.revature.faculty.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.revature.faculty.Exception.DBException;
import com.revature.faculty.Exception.ServiceException;
import com.revature.faculty.dao.FacultyDao;
import com.revature.faculty.model.Faculty;

class FacultyServiceImplTest {
	@InjectMocks
	private FacultyServiceImpl FacultyServiceImpl;

	@Mock
	FacultyDao FacultyDao;

	@Test
	void testGet() throws DBException, ServiceException {
		Faculty faculty = new Faculty();
		faculty.setId((long) 1);
		faculty.setEmployee_id(1);
		faculty.setFirst_name("abc");
		faculty.setLast_name("def");

		faculty.setEmail("abc@gmail.com");
		faculty.setMobile_no((long) 998451233);

		List<Faculty> facultyList = new ArrayList<Faculty>();
		facultyList.add(faculty);

		when(FacultyDao.get()).thenReturn(facultyList);
		assertNotNull(facultyList);
		assertEquals(FacultyServiceImpl.get(), facultyList);

	}

	@Test
	void testGetLong() {
		fail("Not yet implemented");
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
	void testGetByInstitution() {
		fail("Not yet implemented");
	}

}

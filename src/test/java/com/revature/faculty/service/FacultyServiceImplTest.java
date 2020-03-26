package com.revature.faculty.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.revature.faculty.Exception.DBException;
import com.revature.faculty.Exception.ServiceException;
import com.revature.faculty.dao.FacultyDao;
import com.revature.faculty.model.Faculty;
import com.revature.faculty.model.Organization;
import com.revature.faculty.model.Roles;

class FacultyServiceImplTest {
	@InjectMocks
	private FacultyServiceImpl FacultyServiceImpl;

	@Mock
	FacultyDao FacultyDao;

	@Spy
	List<Faculty> facultyList = new ArrayList<Faculty>();

	@Captor
	private ArgumentCaptor<Long> arg;

	private Long id;

	private Organization org;

	private Roles roles;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		facultyList = getFacultyList();

	}

	@Test
	void testGet() throws DBException, ServiceException {

		when(FacultyDao.get()).thenReturn(facultyList);
		assertNotNull(facultyList);
		assertEquals(FacultyServiceImpl.get(), facultyList);

	}

	@Test
	void testGetLong() throws DBException, ServiceException {
		Faculty faculty = new Faculty();
		faculty.setFirst_name("abc");
		faculty.setLast_name("def");
		faculty.setOrg(org);
		faculty.setRoles(roles);
		String str = "2015-03-31";
		Date date = Date.valueOf(str);
		faculty.setDob(date);
		faculty.setEmail("abc@gmail.com");
		faculty.setMobile_no((long) 998451233);

		when(FacultyDao.get(id)).thenReturn(faculty);
		assertNotNull(faculty);
		assertEquals(FacultyServiceImpl.get(id), faculty);

	}

	@Test
	void testSave() {
		Faculty faculty = mock(Faculty.class);

		when(faculty.getFirst_name()).thenReturn("ABC");
		when(faculty.getLast_name()).thenReturn("Kumaar");
		when(faculty.getEmployee_id()).thenReturn(1);
		when(faculty.getOrg()).thenReturn(org);
		assertEquals("ABC", faculty.getFirst_name());
		assertEquals("Kumaar", faculty.getLast_name());
		assertEquals(1, faculty.getEmployee_id());
		assertEquals(org, faculty.getOrg());
	}

	@Test
	void testDelete() throws ServiceException, DBException {
		Long id = (long) 2;
		FacultyServiceImpl.delete(id);
		verify(FacultyDao, times(1)).delete(arg.capture());
		assertNotNull(id);
		assertEquals(id, arg.getValue());
	}

	@Test
	void testGetByInstitution() throws DBException, ServiceException {

		when(FacultyDao.getByInstitution(id)).thenReturn(facultyList);
		assertNotNull(facultyList);
		assertEquals(FacultyServiceImpl.getByInstitution(id), facultyList);

	}

	public List<Faculty> getFacultyList() {

		Faculty faculty = new Faculty();

		faculty.setId((long) 1);
		faculty.setEmployee_id(1);
		faculty.setFirst_name("abc");
		faculty.setLast_name("def");
		faculty.setOrg(org);
		faculty.setRoles(roles);
		String str = "2015-03-31";
		Date date = Date.valueOf(str);
		faculty.setDob(date);
		faculty.setEmail("abc@gmail.com");
		faculty.setMobile_no((long) 998451233);

		List<Faculty> facultyList = new ArrayList<Faculty>();
		facultyList.add(faculty);

		return facultyList;

	}

}

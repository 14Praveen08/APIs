package com.revature.faculty.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import com.revature.faculty.dao.RolesDao;
import com.revature.faculty.model.Roles;

public class RolesServiceImplTest {

	@InjectMocks
	private RolesServiceImpl RolesServiceImpl;

	@Mock
	RolesDao rolesDao;

	@Spy
	List<Roles> roleList = new ArrayList<Roles>();

	@Captor
	private ArgumentCaptor<Long> arg;

	private Long id;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		roleList = getRoleList();

	}

	@Test
	void testGet() throws DBException, ServiceException {

		when(rolesDao.get()).thenReturn(roleList);
		assertNotNull(roleList);
		assertEquals(RolesServiceImpl.get(), roleList);
	}

	@Test
	void testGetLong() throws DBException, ServiceException {
		Roles role = new Roles();
		role.setName("HOD");

		when(rolesDao.get(id)).thenReturn(role);
		assertNotNull(role);
		assertEquals(RolesServiceImpl.get(id), role);

	}

	@Test
	void testSave() {
		Roles role = mock(Roles.class);

		when(role.getName()).thenReturn("HOD");
		assertEquals("HOD", role.getName());

	}

	@Test
	void testDelete() throws ServiceException, DBException {

		Roles role = new Roles();
		when(rolesDao.get(id)).thenReturn(role);
		assertNotNull(role);
		rolesDao.delete(id);
		verify(rolesDao).delete(id);

	}

	public List<Roles> getRoleList() {
		Roles role = new Roles();
		role.setId((long) 1);
		role.setName("HOD");
		Roles role2 = new Roles();
		role2.setId((long) 2);
		role2.setName("Asst.HOD");

		roleList.add(role);
		roleList.add(role2);

		return roleList;
	}

}

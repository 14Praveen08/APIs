package com.revature.organization.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revature.organization.dao.RolesDao;
import com.revature.organization.exception.DBException;
import com.revature.organization.exception.ServiceException;
import com.revature.organization.model.Roles;

class RolesServiceImplTest {

	@InjectMocks
	private RolesServiceImpl RolesServiceImpl;

	@Mock
	RolesDao rolesDao;

	private Long id;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testGet() throws DBException, ServiceException {
		Roles role = new Roles();
		role.setId((long) 1);
		role.setName("HOD");
		Roles role2 = new Roles();
		role2.setId((long) 2);
		role2.setName("Asst.HOD");

		List<Roles> roleList = new ArrayList<Roles>();
		roleList.add(role);
		roleList.add(role2);

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

		doThrow(IllegalArgumentException.class).when(role).setName(null);

		doAnswer((i) -> {
			assertTrue("HOD".equals(i.getArgument(0)));
			return null;
		}).when(role).setName("HOD");

		when(role.getName()).thenReturn("HOD");
		assertThrows(IllegalArgumentException.class, () -> role.setName(null));
		assertEquals("HOD", role.getName());
	}

	@Test
	void testDelete() throws DBException {
		Roles role = new Roles();
		when(rolesDao.get(id)).thenReturn(role);
		assertNotNull(role);
		rolesDao.delete(id);
		verify(rolesDao).delete(id);
	}

}

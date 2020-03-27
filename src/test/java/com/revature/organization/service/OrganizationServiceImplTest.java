package com.revature.organization.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.revature.organization.dao.OrganizationDAO;
import com.revature.organization.exception.DBException;
import com.revature.organization.exception.ServiceException;
import com.revature.organization.model.Organization;

class OrganizationServiceImplTest {

	@InjectMocks
	private OrganizationServiceImpl orgservice;

	@Mock
	OrganizationDAO org;

	@Spy
	List<Organization> organizationList = new ArrayList<Organization>();

	private Long id;

	@BeforeEach
	void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
		organizationList = getOrgList();
	}

	@Test
	void testGet() throws DBException, ServiceException {
		when(org.get()).thenReturn(organizationList);
		assertNotNull(organizationList);
		assertEquals(orgservice.get(), organizationList);

	}

	@Test
	void testGetLong() throws DBException, ServiceException {
		Organization organization = new Organization();
		organization.setName("KCG College of Engineering");
		organization.setAlias("KCG");
		organization.setUniversity("Anna University");
		LocalDateTime cDateTime = LocalDateTime.of(2020, 03, 22, 11, 42, 32);
		organization.setCreatedon(cDateTime);
		LocalDateTime mDateTime = LocalDateTime.of(2020, 03, 22, 11, 45, 20);
		organization.setCreatedon(mDateTime);
		organization.setIsActive(true);

		when(org.get(id)).thenReturn(organization);
		assertNotNull(organization);
		assertEquals(orgservice.get(id), organization);
	}

	@Test
	void testSave() {
		Organization organization = mock(Organization.class);

		when(organization.getName()).thenReturn("KCG College Of Engineering");
		when(organization.getAlias()).thenReturn("KCG");
		when(organization.getUniversity()).thenReturn("Anna University");
		LocalDateTime cDateTime = LocalDateTime.of(2020, 03, 22, 12, 30, 20);
		when(organization.getCreatedon()).thenReturn(cDateTime);
		LocalDateTime mDateTime = LocalDateTime.of(2020, 03, 22, 12, 45, 05);
		when(organization.getModifiedon()).thenReturn(mDateTime);
		when(organization.getIsActive()).thenReturn(false);

		assertEquals("KCG College Of Engineering", organization.getName());
		assertEquals("KCG", organization.getAlias());
		assertEquals("Anna University", organization.getUniversity());
		assertEquals(cDateTime, organization.getCreatedon());
		assertEquals(mDateTime, organization.getModifiedon());
		assertEquals(false, organization.getIsActive());

	}

	@Test
	void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	void testChangeStatus() throws DBException, ServiceException {
		Organization organization = new Organization();
		organization.setIsActive(true);

		when(org.get(id)).thenReturn(organization);
		assertEquals(orgservice.get(id), organization);
	}

	private List<Organization> getOrgList() {
		Organization organization = new Organization();
		organization.setId((long) 1);
		organization.setName("KCG College of Engineering");
		organization.setAlias("KCG");
		organization.setUniversity("Anna University");
		LocalDateTime cDateTime = LocalDateTime.of(2020, 03, 22, 11, 42, 32);
		organization.setCreatedon(cDateTime);
		LocalDateTime mDateTime = LocalDateTime.of(2020, 03, 22, 11, 45, 20);
		organization.setCreatedon(mDateTime);
		organization.setIsActive(true);

		organizationList.add(organization);
		return organizationList;
	}

}

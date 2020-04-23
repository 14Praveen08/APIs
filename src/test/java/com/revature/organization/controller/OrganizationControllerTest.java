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
import com.revature.organization.exception.ServiceException;
import com.revature.organization.model.Organization;
import com.revature.organization.service.OrganizationService;

@ExtendWith({ RestDocumentationExtension.class, SpringExtension.class })
@WebAppConfiguration
@AutoConfigureRestDocs
class OrganizationControllerTest {

	private MockMvc mockmvc;

	private ObjectMapper om = new ObjectMapper();

	@InjectMocks
	OrganizationController organizationcontroller;

	@Mock
	OrganizationService organizationService;

	@Spy
	List<Organization> orgList = new ArrayList<Organization>();

	private Long id;

	@BeforeEach
	void setup(RestDocumentationContextProvider restDocumentation) throws Exception {
		MockitoAnnotations.initMocks(this);
		mockmvc = MockMvcBuilders.standaloneSetup(organizationcontroller)
				.apply(documentationConfiguration(restDocumentation)).build();
		orgList = getOrgList();
	}

	private List<Organization> getOrgList() {
		Organization organization = new Organization();
		organization.setId((long) 1);
		organization.setName("KCG College of Engineering");
		organization.setAlias("KCG");
		organization.setUniversity("Anna University");
		organization.setIsActive(true);

		orgList.add(organization);
		return orgList;
	}

	@Test
	void testOrganizationGet() throws Exception {
		when(organizationService.get()).thenReturn(orgList);
		this.mockmvc.perform(get("/core/organization")).andDo(print()).andExpect(status().isOk())
				.andDo(document("{methodName}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())));
	}

	@Test
	void testOrganizationGetExpectFailure() throws Exception {
		doThrow(ServiceException.class).when(organizationService).get();
		this.mockmvc.perform(get("/core/organization")).andExpect(status().isNotFound());
	}

	@Test
	void testOrganizationGetActive() throws Exception {
		when(organizationService.getActive()).thenReturn(orgList);
		this.mockmvc.perform(get("/core/organization/active")).andDo(print()).andExpect(status().isOk())
				.andDo(document("{methodName}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())));
	}

	@Test
	void testOrganizationGetActiveExpectFailure() throws Exception {
		doThrow(ServiceException.class).when(organizationService).getActive();
		this.mockmvc.perform(get("/core/organization/active")).andExpect(status().isNotFound());
	}

	@Test
	void testOrganizationSave() throws Exception {
		Organization organization = new Organization();
		organization.setId((long) 1);
		organization.setName("KCG College of Engineering");
		organization.setAlias("KCG");
		organization.setUniversity("Anna University");
		organization.setIsActive(true);
		doNothing().when(organizationService).save(organization);
		String orgJson = om.writeValueAsString(organization);
		this.mockmvc.perform(post("/core/organization").contentType(MediaType.APPLICATION_JSON_VALUE).content(orgJson))
				.andDo(print()).andExpect(status().isCreated())
				.andDo(document("{methodName}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())));

	}

	@Test
	void testOrganizationSaveExpectFailure() throws Exception {
		Organization organization = new Organization();
		doThrow(ServiceException.class).when(organizationService).save(organization);
		this.mockmvc.perform(post("/core/organization")).andExpect(status().isBadRequest());
	}

	@Test
	void testOrganizationGetById() throws Exception {
		Organization organization = new Organization();
		organization.setName("KCG College of Engineering");
		organization.setAlias("KCG");
		organization.setUniversity("Anna University");
		organization.setIsActive(true);
		when(organizationService.get(id)).thenReturn(organization);
		this.mockmvc.perform(get("/core/organization/{id}", 1)).andDo(print()).andExpect(status().isOk())
				.andDo(document("{methodName}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())));
	}

	@Test
	void testGetOrganizationByIdExpectFailure() throws Exception {
		id = (long) 1;
		doThrow(ServiceException.class).when(organizationService).get(id);
		this.mockmvc.perform(get("/core/organization/{id}", 1)).andExpect(status().isNotFound());
	}

	@Test
	void testOrganizationDelete() throws Exception {
		Organization organization = new Organization();
		id = (long) 1;
		when(organizationService.get(id)).thenReturn(organization);
		this.mockmvc.perform(delete("/core/organization/{id}", 1)).andDo(print()).andExpect(status().isOk())
				.andDo(document("{methodName}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())));
	}

	@Test
	void testOrganizationDeleteExpectFailure() throws Exception {
		id = (long) 1;
		doThrow(ServiceException.class).when(organizationService).delete(id);
		this.mockmvc.perform(delete("/core/role/{id}", 1)).andExpect(status().isNotFound());
	}

	@Test
	void testOrganizationUpdate() throws Exception {
		Organization organization = new Organization();
		organization.setId((long) 1);
		organization.setName("KCG College of Engineering");
		organization.setAlias("KCG");
		organization.setUniversity("Anna University");
		organization.setIsActive(true);
		doNothing().when(organizationService).save(organization);
		String orgJson = om.writeValueAsString(organization);
		this.mockmvc.perform(put("/core/organization").contentType(MediaType.APPLICATION_JSON_VALUE).content(orgJson))
				.andDo(print()).andExpect(status().isOk())
				.andDo(document("{methodName}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())));
	}

	@Test
	void testOrganizationUpdateExpectFailure() throws Exception {
		Organization organization = new Organization();
		doThrow(ServiceException.class).when(organizationService).save(organization);
		this.mockmvc.perform(put("/core/organization")).andExpect(status().isBadRequest());
	}

	@Test
	void testOrganizationChangeStatus() throws Exception {
		Organization organization = new Organization();
		id = (long) 1;
		doNothing().when(organizationService).changeStatus(id);
		this.mockmvc.perform(put("/core/organization/status/{id}", 1)).andDo(print()).andExpect(status().isOk())
				.andDo(document("{methodName}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())));
	}

	@Test
	void testOrganizationChangeStatusExpectFailure() throws Exception {
		id = (long) 1;
		doThrow(ServiceException.class).when(organizationService).changeStatus(id);
		this.mockmvc.perform(put("/core/organization/status/{id}", 1)).andExpect(status().isBadRequest());
	}
}

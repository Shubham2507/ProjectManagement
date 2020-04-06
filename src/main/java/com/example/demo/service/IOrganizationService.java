package com.example.demo.service;

import java.util.List;

import com.example.demo.dao.OrganizatonDao;
import com.example.demo.entity.Organization;

public interface IOrganizationService {

	List<OrganizatonDao> getAllOrganization();

	String deleteOneOrganization(int organizationId);

	Organization addItemToOrganization(Organization organizationObj);

	Object updateOrganization(Organization organization);

}

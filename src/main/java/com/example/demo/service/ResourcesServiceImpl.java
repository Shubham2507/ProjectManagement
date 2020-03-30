package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.example.demo.entity.Designation;
import com.example.demo.entity.Resources;
import com.example.demo.repo.DesignationRepo;
import com.example.demo.repo.ResourcesRepo;

@EnableWebMvc
@Service("resourcesService")
public class ResourcesServiceImpl implements IResourcesService {
	@Autowired
	private DesignationRepo designationRepo;
	@Autowired
	private ResourcesRepo resourcesRepo;

	@Override
	public String addItemToResources(int id,Resources resources) {
		Designation des = designationRepo.getOne(id);
		resources.setDesignation(des);
		resourcesRepo.save(resources);
		
		return "Added Successfully!!!";
	}

}

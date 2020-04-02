package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.example.demo.dao.ResourcesDao;
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
	public Resources addItemToResources(int id, Resources resources) {
		Designation des = designationRepo.getOne(id);
		resources.setDesignation(des);
		Resources resources2 = resourcesRepo.save(resources);

		return resources2;
	}

	@Override
	public List<ResourcesDao> getAllResources() {
		List<ResourcesDao> resourcesDaos = new ArrayList<ResourcesDao>();
		List<Resources> resources = resourcesRepo.findAll();
		for (Resources res : resources) {
			ResourcesDao resourcesDao = new ResourcesDao();
			resourcesDao.setResource_id(res.getResource_id());
			resourcesDao.setName(res.getName());
			resourcesDao.setDesignation(res.getDesignation().getName());
			resourcesDaos.add(resourcesDao);

		}
		return resourcesDaos;
	}

	@Override
	public String deleteOneDesignation(int designationId) {
		resourcesRepo.delete(resourcesRepo.getOne(designationId));
		return "Deleted Successfully";
	}

	@Override
	public Resources updateResources(Resources resources) {
		Resources resources2 = resourcesRepo.getOne(resources.getResource_id());
		resources2.setDesignation(resources.getDesignation());
		Resources resources3 = resourcesRepo.save(resources2);

		return resources3;
	}

}

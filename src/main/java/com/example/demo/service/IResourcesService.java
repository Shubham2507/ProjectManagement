package com.example.demo.service;

import java.util.List;

import com.example.demo.dao.ResourcesDao;
import com.example.demo.entity.Resources;;

public interface IResourcesService {

	List<ResourcesDao> getAllResources();

	String deleteOneDesignation(int designationId);

	Resources addItemToResources(int id,Resources resources);

	//Resources updateDesignation(Designation designation);

}

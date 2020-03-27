package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.example.demo.entity.Designation;
import com.example.demo.repo.DesignationRepo;

@EnableWebMvc
@Service("designationService")
public class DesignationServiceImpl implements IDesignatonService{
	@Autowired
	private DesignationRepo designationRepo;

	@Override
	public List<Designation> getAllDesignation() {
		// TODO Auto-generated method stub
		return designationRepo.findAll();
	}

}

package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Projects;
import com.example.demo.entity.Resources;

public interface ProjectRepo extends JpaRepository<Projects, Integer> {
	List<Projects> findAllByOrganization_Id(int id);
	List<Projects> findAllByResources(Resources resources);

}

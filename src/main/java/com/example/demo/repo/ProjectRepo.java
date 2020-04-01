package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Projects;

public interface ProjectRepo extends JpaRepository<Projects, Integer> {
	List<Integer> findAllByOrgnization_Id(int id);

}

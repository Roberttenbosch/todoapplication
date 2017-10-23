package com.assignment.todo.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.todo.model.Serie;

@Repository
public interface SerieRepository extends JpaRepository<Serie, Serializable>
{
	public List<Serie> findByCompletedTrue();
	
	public List<Serie> findByCompletedFalse();
}

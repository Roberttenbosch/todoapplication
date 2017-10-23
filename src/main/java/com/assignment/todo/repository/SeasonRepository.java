package com.assignment.todo.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.todo.model.Season;

@Repository
public interface SeasonRepository extends JpaRepository<Season, Serializable>
{
	public List<Season> findByCompletedTrue();
	
	public List<Season> findByCompletedFalse();
}

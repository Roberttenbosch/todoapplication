package com.assignment.todo.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.todo.model.Episode;

@Repository
public interface EpisodeRepository extends JpaRepository<Episode, Serializable>
{
	public List<Episode> findByWatchedTrue();
	
	public List<Episode> findByWatchedFalse();
}

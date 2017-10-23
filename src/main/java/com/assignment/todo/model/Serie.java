package com.assignment.todo.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TODO_SERIE")
public class Serie
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TSE_ID")
	private Long id;

	@Column(name = "TSE_SERIE_TITLE")
	private String serieTitle;
	
	@Column(name = "TSE_SERIE_COMPLETED")
	private Boolean completed;
	
	@OneToMany(mappedBy = "serie", cascade = CascadeType.ALL)
	private Set<Season> seasons;

	
	
	
	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getSerieTitle()	
	{
		return serieTitle;
	}

	public void setSerieTitle(String serieTitle)
	{
		this.serieTitle = serieTitle;
	}

	public Boolean getCompleted()
	{
		if(completed == null)
		{
			completed = Boolean.FALSE;
		}
		return completed;
	}

	public void setCompleted(Boolean completed)
	{
		this.completed = completed;
	}

	public Set<Season> getSeasons()
	{
		return seasons;
	}

	public void setSeasons(Set<Season> seasons)
	{
		this.seasons = seasons;
	}
	

}

package com.assignment.todo.model;

import java.util.Collections;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.util.CollectionUtils;

@Entity
@Table(name = "TODO_SEASON")
public class Season
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TSN_ID")
	private Long id;

	@Column(name = "TSN_SEASON_NUMBER")
	private Long seasonNumber;

	@Column(name = "TSN_YEAR")
	private int year;
	
	@Column(name = "TSN_COMPLETED")
	private Boolean completed;

	@OneToMany(mappedBy = "season", cascade = CascadeType.ALL)
	private Set<Episode> episodes;
	
	@ManyToOne
	@JoinColumn(name = "TSE_ID")
	private Serie serie;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Long getSeasonNumber()
	{
		return seasonNumber;
	}

	public void setSeasonNumber(Long seasonNumber)
	{
		this.seasonNumber = seasonNumber;
	}

	public int getYear()
	{
		return year;
	}

	public void setYear(int year)
	{
		this.year = year;
	}

	public Set<Episode> getEpisodes()
	{
		if(CollectionUtils.isEmpty(episodes)) {
			return Collections.emptySet();
		}
		return episodes;
	}

	public void setEpisodes(Set<Episode> episodes)
	{
		this.episodes = episodes;
	}

	public Boolean getCompleted()
	{
		return completed;
	}

	public void setCompleted(Boolean completed)
	{
		this.completed = completed;
	}

	public Serie getSerie()
	{
		return serie;
	}

	public void setSerie(Serie serie)
	{
		this.serie = serie;
	}
	
	

}

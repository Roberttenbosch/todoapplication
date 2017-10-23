package com.assignment.todo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TODO_EPISODE")
public class Episode
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TEP_ID")
	private Long id;

	@Column(name = "TEP_EPISODE_NUMBER")
	private Long episodeNumber;

	@Column(name = "TEP_EPISODE_TITLE")
	private String episodeTitle;

	@Column(name = "TEP_WATCHED")
	private boolean watched;

	@Column(name = "TEP_RATING")
	private Long rating;

	// private Date releaseDate

	@ManyToOne
	@JoinColumn(name = "TSN_ID")
	private Season season;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Long getEpisodeNumber()
	{
		return episodeNumber;
	}

	public void setEpisodeNumber(Long episodeNumber)
	{
		this.episodeNumber = episodeNumber;
	}

	public String getEpisodeTitle()
	{
		return episodeTitle;
	}

	public void setEpisodeTitle(String episodeTitle)
	{
		this.episodeTitle = episodeTitle;
	}

	public boolean isWatched()
	{
		return watched;
	}

	public void setWatched(boolean watched)
	{
		this.watched = watched;
	}

	public Long getRating()
	{
		return rating;
	}

	public void setRating(Long rating)
	{
		this.rating = rating;
	}

	public Season getSeason()
	{
		return season;
	}

	public void setSeason(Season season)
	{
		this.season = season;
	}

}

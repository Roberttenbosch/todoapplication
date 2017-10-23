package com.assignment.todo.web.bean;

import com.assignment.todo.model.Episode;

public class EpisodeBean
{
	private Long id;

	private Long episodeNumber;

	private String episodeTitle;

	private boolean watched;

	private Long rating;
	
	private Long seasonId;
	
	private Long seasonNumber;
	
	public EpisodeBean() {};
	
	public EpisodeBean(Episode episode)
	{
		this.id = episode.getId();
		this.episodeNumber = episode.getEpisodeNumber();
		this.episodeTitle = episode.getEpisodeTitle();
		this.watched = episode.isWatched();
		this.rating =  episode.getRating();
		this.seasonId = episode.getSeason().getId();
		this.seasonNumber= episode.getSeason().getSeasonNumber();
	}

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

	public Long getSeasonId()
	{
		return seasonId;
	}

	public void setSeasonId(Long seasonId)
	{
		this.seasonId = seasonId;
	}

	public Long getSeasonNumber()
	{
		return seasonNumber;
	}

	public void setSeasonNumber(Long seasonNumber)
	{
		this.seasonNumber = seasonNumber;
	}
	
	

}

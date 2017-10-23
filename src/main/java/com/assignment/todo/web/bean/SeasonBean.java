package com.assignment.todo.web.bean;

import java.util.Set;

public class SeasonBean
{
	private Long seasonId;
	
	private Long serieId;

	private Long seasonNumber;

	private int year;

	private int numberOfEpisodes;

	private int numberOfWatchedEpisodesPerSeason;

	private int numberOfUnWatchedEpisodesPerSeason;

	private Boolean completed;

	private Set<EpisodeBean> episodes;
	
	public SeasonBean() {};
	


	public Long getSeasonId()
	{
		return seasonId;
	}

	public void setSeasonId(Long seasonId)
	{
		this.seasonId = seasonId;
	}

	public Long getSerieId()
	{
		return serieId;
	}

	public void setSerieId(Long serieId)
	{
		this.serieId = serieId;
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

	public int getNumberOfEpisodes()
	{
		return numberOfEpisodes;
	}

	public void setNumberOfEpisodes(int numberOfEpisodes)
	{
		this.numberOfEpisodes = numberOfEpisodes;
	}

	public int getNumberOfWatchedEpisodesPerSeason()
	{
		return numberOfWatchedEpisodesPerSeason;
	}

	public void setNumberOfWatchedEpisodesPerSeason(int numberOfWatchedEpisodesPerSeason)
	{
		this.numberOfWatchedEpisodesPerSeason = numberOfWatchedEpisodesPerSeason;
	}

	public int getNumberOfUnWatchedEpisodesPerSeason()
	{
		return numberOfUnWatchedEpisodesPerSeason;
	}

	public void setNumberOfUnWatchedEpisodesPerSeason(int numberOfUnWatchedEpisodesPerSeason)
	{
		this.numberOfUnWatchedEpisodesPerSeason = numberOfUnWatchedEpisodesPerSeason;
	}

	public Set<EpisodeBean> getEpisodes()
	{
		return episodes;
	}

	public void setEpisodes(Set<EpisodeBean> episodes)
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

}

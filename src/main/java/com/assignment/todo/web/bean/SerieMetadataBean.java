package com.assignment.todo.web.bean;

public class SerieMetadataBean
{
	private int numberOfSeasons;

	private int numberOfCompletedSeasons;

	private int numberOfEpisodesPerSerie;

	private int numberOfWatchedEpisodes;
	
	private int numberOfUnseenEpisodes;

	private int serieRating;
	
	private int episodesPerSeason;
	

	public int getNumberOfSeasons()
	{
		return numberOfSeasons;
	}

	public void setNumberOfSeasons(int numberOfSeasons)
	{
		this.numberOfSeasons = numberOfSeasons;
	}

	public int getNumberOfCompletedSeasons()
	{
		return numberOfCompletedSeasons;
	}

	public void setNumberOfCompletedSeasons(int numberOfCompletedSeasons)
	{
		this.numberOfCompletedSeasons = numberOfCompletedSeasons;
	}

	public int getNumberOfEpisodesPerSerie()
	{
		return numberOfEpisodesPerSerie;
	}

	public void setNumberOfEpisodesPerSerie(int numberOfEpisodesPerSerie)
	{
		this.numberOfEpisodesPerSerie = numberOfEpisodesPerSerie;
	}

	public int getNumberOfWatchedEpisodes()
	{
		return numberOfWatchedEpisodes;
	}

	public void setNumberOfWatchedEpisodes(int numberOfWatchedEpisodes)
	{
		this.numberOfWatchedEpisodes = numberOfWatchedEpisodes;
	}

	public int getNumberOfUnseenEpisodes()
	{
		return numberOfUnseenEpisodes;
	}

	public void setNumberOfUnseenEpisodes(int numberOfUnseenEpisodes)
	{
		this.numberOfUnseenEpisodes = numberOfUnseenEpisodes;
	}

	public int getSerieRating()
	{
		return serieRating;
	}

	public void setSerieRating(int serieRating)
	{
		this.serieRating = serieRating;
	}

	public int getEpisodesPerSeason()
	{
		return episodesPerSeason;
	}

	public void setEpisodesPerSeason(int episodesPerSeason)
	{
		this.episodesPerSeason = episodesPerSeason;
	}

}

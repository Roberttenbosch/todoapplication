package com.assignment.todo.web.bean;

import java.util.Set;


public class SerieBean
{
	private Long serieId;

	private String serieTitle;


	private boolean completed;
	
	private SerieMetadataBean serieMetadataBean;

	private Set<SeasonBean> seasons;

	public SerieBean()
	{
	};

	public String getSerieTitle()
	{
		return serieTitle;
	}

	public Long getSerieId()
	{
		return serieId;
	}

	public void setSerieId(Long serieId)
	{
		this.serieId = serieId;
	}

	public void setSerieTitle(String serieTitle)
	{
		this.serieTitle = serieTitle;
	}


	public Set<SeasonBean> getSeasons()
	{
		return seasons;
	}

	public SerieMetadataBean getSerieMetadataBean()
	{
		return serieMetadataBean;
	}

	public void setSerieMetadataBean(SerieMetadataBean serieMetadataBean)
	{
		this.serieMetadataBean = serieMetadataBean;
	}
	
	public void setSeasons(Set<SeasonBean> seasons)
	{
		this.seasons = seasons;
	}

	public boolean isCompleted()
	{
		return completed;
	}

	public void setCompleted(boolean completed)
	{
		this.completed = completed;
	}



}

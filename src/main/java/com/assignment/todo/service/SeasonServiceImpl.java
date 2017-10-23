package com.assignment.todo.service;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.todo.model.Episode;
import com.assignment.todo.model.Season;
import com.assignment.todo.repository.SeasonRepository;
import com.assignment.todo.web.bean.EpisodeBean;
import com.assignment.todo.web.bean.SeasonBean;

@Service
public class SeasonServiceImpl implements SeasonService
{
	private static final Logger log = LoggerFactory.getLogger(SeasonServiceImpl.class);

	@Autowired
	private SeasonRepository seasonRepository;

	@Override
	public Boolean update(SeasonBean seasonBean)
	{
		Season season = seasonRepository.findOne(seasonBean.getSeasonId());
		if (season != null)
		{

			season.setSeasonNumber(seasonBean.getSeasonNumber());
			season.setYear(seasonBean.getYear());
			
			try
			{
				seasonRepository.saveAndFlush(season);
				return Boolean.TRUE;
			} catch (Exception e)
			{
				log.info("Exception  = {}", e);
			}
		}
		return Boolean.FALSE;
	}

	@Override
	public Boolean isSeasonCompleted(Season season)
	{
		
		for(Episode episode:season.getEpisodes())
		{
			if(!episode.isWatched())
			{
				return Boolean.FALSE;
			}
		}
		return Boolean.TRUE;
	}

	@Override
	public SeasonBean getSeasonById(Long id)
	{
		Season season = seasonRepository.findOne(id);
		Set<EpisodeBean> episodeBeans = getEpisodeBeans(season);
		

		SeasonBean seasonBean = new SeasonBean();
		seasonBean.setSeasonId(season.getId());
		seasonBean.setSeasonNumber(season.getSeasonNumber());
		seasonBean.setCompleted(season.getCompleted());
		seasonBean.setEpisodes(episodeBeans);
		seasonBean.setSerieId(season.getSerie().getId());
		seasonBean.setYear(season.getYear());
		return seasonBean;
	}

	private Set<EpisodeBean> getEpisodeBeans(Season season)
	{
		Set<EpisodeBean> episodeBeans = new HashSet<>();
		for(Episode episode: season.getEpisodes())
		{
			episodeBeans.add(new EpisodeBean(episode));
		}
		return episodeBeans;
	}

}

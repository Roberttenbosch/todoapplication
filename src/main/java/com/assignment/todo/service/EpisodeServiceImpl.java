package com.assignment.todo.service;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.todo.model.Episode;
import com.assignment.todo.repository.EpisodeRepository;
import com.assignment.todo.web.bean.EpisodeBean;

@Service
public class EpisodeServiceImpl implements EpisodeService
{
	private static final Logger log = LoggerFactory.getLogger(EpisodeServiceImpl.class);
	
	@Autowired
	private EpisodeRepository episodeRepository;
	
	@Autowired
	private SeasonService seasonService;

	@Override
	public Boolean update(EpisodeBean episodeBean)
	{
		Episode episode = episodeRepository.findOne(episodeBean.getId());
		if (episode != null)
		{
			episode.setEpisodeNumber(episodeBean.getEpisodeNumber());
			episode.setEpisodeTitle(episodeBean.getEpisodeTitle());
			episode.setRating(episodeBean.getRating());
			episode.setWatched(episodeBean.isWatched());
			
			Boolean seasonCompleted = seasonService.isSeasonCompleted(episode.getSeason());  
			if(seasonCompleted)
			{
				episode.getSeason().setCompleted(seasonCompleted);
			}
			
			
			try
			{
				episodeRepository.saveAndFlush(episode);
				return Boolean.TRUE;
			} 
			catch (Exception e)
			{
				log.info("Exception  = {}", e);
				return Boolean.FALSE;
			}
		}
		return Boolean.FALSE;
	}

	@Override
	public EpisodeBean getEpisodeById(Long id)
	{
		Episode episode = episodeRepository.findOne(id);
		return new EpisodeBean(episode);
	}

}

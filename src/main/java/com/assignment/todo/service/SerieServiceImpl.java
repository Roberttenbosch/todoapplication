package com.assignment.todo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.assignment.todo.model.Episode;
import com.assignment.todo.model.Season;
import com.assignment.todo.model.Serie;
import com.assignment.todo.repository.SerieRepository;
import com.assignment.todo.web.bean.EpisodeBean;
import com.assignment.todo.web.bean.SeasonBean;
import com.assignment.todo.web.bean.SerieBean;
import com.assignment.todo.web.bean.SerieMetadataBean;

@Service
public class SerieServiceImpl implements SerieService
{

	private static final Logger log = LoggerFactory.getLogger(SerieServiceImpl.class);

	@Autowired
	private SerieRepository serieRepository;

	@Override
	public void save(SerieBean serieBean)
	{
		Serie serie = new Serie();
		if (serieBean != null)
		{
			serie.setSerieTitle(serieBean.getSerieTitle());
			serie.setCompleted(Boolean.FALSE);
			Set<Season>seasons = makeSeasons(serieBean.getSerieMetadataBean(),serie);
			serie.setSeasons(seasons);
		}

		serieRepository.save(serie);
	}

	@Override
	public Boolean update(SerieBean serieBean)
	{
		Serie serie = serieRepository.findOne(serieBean.getSerieId());
		if (serie != null)
		{
			serie.setSerieTitle(serieBean.getSerieTitle());
			Boolean serieCompleted	= isSerieCompleted(serie.getSeasons());
			if(serieBean.isCompleted() || serieCompleted)
			{
				serie.setCompleted(serieBean.isCompleted());
			}
			
			try
			{
				serieRepository.save(serie);

			} catch (Exception e)
			{
				log.info("Exception = {}", e);
				return Boolean.FALSE;
			}
		}
		return Boolean.FALSE;
	}



	@Override
	public SerieBean getSerieById(Long id)
	{
		Serie serie = serieRepository.findOne(id);
		if (serie != null)
		{
			return getSerieBean(serie);
		}
		return null;
	}

	@Override
	public List<SerieBean> getAllSeries()
	{
		List<Serie> series = serieRepository.findAll();
		List<SerieBean> serieBeans = new ArrayList<>();
		for (Iterator<Serie> serie = series.iterator(); serie.hasNext();)
		{
			serieBeans.add(getSerieBean(serie.next()));
		}

		return serieBeans;
	}

	@Override
	public List<SerieBean> getCompletedSeries()
	{
		List<Serie> series = serieRepository.findByCompletedTrue();
		List<SerieBean> serieBeans = new ArrayList<>();
		for (Iterator<Serie> serie = series.iterator(); serie.hasNext();)
		{
			serieBeans.add(getSerieBean(serie.next()));
		}

		return serieBeans;
	}

	@Override
	public List<SerieBean> getNotCompletedSeries()
	{
		List<Serie> series = serieRepository.findByCompletedFalse();
		List<SerieBean> serieBeans = new ArrayList<>();
		for (Iterator<Serie> serie = series.iterator(); serie.hasNext();)
		{
			serieBeans.add(getSerieBean(serie.next()));
		}

		return serieBeans;
	}

	private SerieBean getSerieBean(Serie serie)
	{
		SerieBean serieBean = new SerieBean();

		serieBean.setSerieId(serie.getId());
		serieBean.setSerieTitle(serie.getSerieTitle());
		serieBean.setCompleted(serie.getCompleted());
		SerieMetadataBean serieMetadataBean = getSerieMetadataBean(serie);
		serieBean.setSerieMetadataBean(serieMetadataBean);
		
		Set<SeasonBean> seasonBeans = getSeasonBeans(serie);
		serieBean.setSeasons(seasonBeans);

		return serieBean;

	}

	private Set<SeasonBean> getSeasonBeans(Serie serie)
	{
		Set<SeasonBean> seasonBeans = new HashSet<>();
		Set<Season> seasons = serie.getSeasons();
		for (Season season : seasons)
		{
			SeasonBean seasonBean = new SeasonBean();
			seasonBean.setSerieId(serie.getId());
			seasonBean.setSeasonId(season.getId());
			seasonBean.setSeasonNumber(season.getSeasonNumber());
			Set<Episode> episodes = season.getEpisodes();
			int numberOfEpisodesPerSeason = 0;
			if (CollectionUtils.isEmpty(episodes))
			{
				seasonBean.setNumberOfEpisodes(numberOfEpisodesPerSeason);
			} else
			{
				numberOfEpisodesPerSeason = season.getEpisodes().size();
				seasonBean.setNumberOfEpisodes(numberOfEpisodesPerSeason);

			}
			int numberOfCompletedEpisodesPerSeason = 0;
			Set<EpisodeBean> episodeBeans = new HashSet<>();
			for (Episode episode : season.getEpisodes())
			{
				EpisodeBean episodeBean = new EpisodeBean(episode);
				episodeBeans.add(episodeBean);
				if (episode.isWatched())
				{
					numberOfCompletedEpisodesPerSeason = numberOfCompletedEpisodesPerSeason + 1;
				}
			}
			int numberOfUnWatchedEpisodesPerSeason = numberOfEpisodesPerSeason - numberOfCompletedEpisodesPerSeason;
			seasonBean.setNumberOfUnWatchedEpisodesPerSeason(numberOfUnWatchedEpisodesPerSeason);
			seasonBean.setNumberOfWatchedEpisodesPerSeason(numberOfCompletedEpisodesPerSeason);
			seasonBean.setYear(season.getYear());
			seasonBeans.add(seasonBean);
		}
		return seasonBeans;
	}

	
	private SerieMetadataBean getSerieMetadataBean(Serie serie)
	{

		SerieMetadataBean serieMetadataBean = new SerieMetadataBean();
		Set<Season> seasons = serie.getSeasons();

		int numberOfSeasons = serie.getSeasons().size();
		int numberOfCompletedSeasons = 0;
		int numberOfEpisodesPerSerie = 0;
		int numberOfWatchedEpisodes = 0;
		int numberOfUnseenEpisodes = 0;
		int seriesRating = 0;
		int numberOfRatedEpisodes = 0;
		for (Season season : seasons)
		{
			Set<Episode> episodes = season.getEpisodes();
			numberOfEpisodesPerSerie = numberOfEpisodesPerSerie + episodes.size();
			int numberOfCompletedEpisodesPerSeason = 0;

			for (Episode episode : episodes)
			{
				if (episode.isWatched())
				{
					numberOfCompletedEpisodesPerSeason = numberOfCompletedEpisodesPerSeason + 1;
					Long rating = episode.getRating();
					if (rating != 0 || rating != null)
					{
						numberOfRatedEpisodes = numberOfRatedEpisodes + 1;
						seriesRating = (int) (seriesRating + rating);
					}
				}

			}
			if (numberOfCompletedEpisodesPerSeason != 0)
			{
				if (numberOfEpisodesPerSerie == numberOfCompletedEpisodesPerSeason)
				{
					numberOfCompletedSeasons = numberOfCompletedSeasons + 1;
				}

			}

			numberOfWatchedEpisodes = numberOfWatchedEpisodes
					+ numberOfCompletedEpisodesPerSeason;
		}

		float rating = 0;

		if (numberOfRatedEpisodes != 0)
		{
			rating = seriesRating / numberOfRatedEpisodes;
		}

		numberOfUnseenEpisodes = numberOfEpisodesPerSerie - numberOfWatchedEpisodes;

		serieMetadataBean.setNumberOfSeasons(numberOfSeasons);
		serieMetadataBean.setNumberOfCompletedSeasons(numberOfCompletedSeasons);
		serieMetadataBean.setNumberOfWatchedEpisodes(numberOfWatchedEpisodes);
		serieMetadataBean.setNumberOfEpisodesPerSerie(numberOfEpisodesPerSerie);
		serieMetadataBean.setNumberOfUnseenEpisodes(numberOfUnseenEpisodes);
		serieMetadataBean.setSerieRating(Math.round(rating));

		return serieMetadataBean;
	}
	
	private Boolean isSerieCompleted(Set<Season> seasons)
	{
		for(Season season:seasons)
		{
			if(!season.getCompleted())
			{
				return Boolean.FALSE;
			}
		}
		return Boolean.TRUE;
	}
	
	private Set<Season> makeSeasons(SerieMetadataBean serieMetadataBean, Serie serie)
	{
		if(serieMetadataBean.getNumberOfSeasons() > 0)
		{
			Set<Season>seasons = new HashSet<>();
			for(int i=0; i<serieMetadataBean.getNumberOfSeasons();i++)
			{
				Season season = new Season();
				season.setSeasonNumber((long) i+1);
				season.setSerie(serie);
				Set<Episode> episodes = makeEpisodes(serieMetadataBean, season);
				season.setEpisodes(episodes);
				seasons.add(season);
			}
			
			return seasons;
		}
		
		return Collections.emptySet();
	}

	private Set<Episode> makeEpisodes(SerieMetadataBean serieMetadataBean, Season season)
	{
		if(serieMetadataBean.getEpisodesPerSeason() > 0)
		{
			Set<Episode>episodes = new HashSet<>();
			for(int i=0; i<serieMetadataBean.getEpisodesPerSeason();i++)
			{
				Episode episode = new Episode();
				episode.setEpisodeNumber((long) i +1);
				episode.setSeason(season);
				episode.setWatched(Boolean.FALSE);
				episodes.add(episode);
			}
			
			return episodes;
		}
		
		
		return Collections.emptySet();
	}

}

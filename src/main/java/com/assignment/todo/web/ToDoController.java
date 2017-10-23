package com.assignment.todo.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.assignment.todo.service.EpisodeService;
import com.assignment.todo.service.SeasonService;
import com.assignment.todo.service.SerieService;
import com.assignment.todo.web.bean.EpisodeBean;
import com.assignment.todo.web.bean.SeasonBean;
import com.assignment.todo.web.bean.SerieBean;

@Controller
public class ToDoController
{
	private static final Logger log = LoggerFactory.getLogger(ToDoController.class);

	@Autowired
	private SerieService serieService;

	@Autowired
	private SeasonService seasonService;

	@Autowired
	private EpisodeService episodeService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String overview(Model model)
	{
		List<SerieBean> serieBeans = serieService.getAllSeries();
		model.addAttribute("serieBeans", serieBeans);
		return "index";
	}

	@RequestMapping(value = "/unseenseries", method = RequestMethod.GET)
	public String unseenseries(Model model)
	{
		List<SerieBean> serieBeans = serieService.getNotCompletedSeries();

		model.addAttribute("serieBeans", serieBeans);
		return "index";
	}

	@RequestMapping(value = "/finishedseries", method = RequestMethod.GET)
	public String finishedseries(Model model)
	{
		List<SerieBean> serieBeans = serieService.getCompletedSeries();

		model.addAttribute("serieBeans", serieBeans);
		return "index";
	}

	@RequestMapping(value = "/serie", method = RequestMethod.GET)
	public String serie(Model model, @RequestParam Long id)
	{

		List<SerieBean> uncompletedSerieBeans = serieService.getNotCompletedSeries();
		log.info("uncompletes {}", uncompletedSerieBeans);
		SerieBean serieBean = serieService.getSerieById(id);
		if (uncompletedSerieBeans.contains(serieBean))
		{
			uncompletedSerieBeans.remove(serieBean);
		}
		model.addAttribute("serieBeans", uncompletedSerieBeans);
		model.addAttribute("serieBean", serieBean);
		return "serie";
	}

	@RequestMapping(value = "/season", method = RequestMethod.GET)
	public String season(Model model, @RequestParam Long id)
	{

		SeasonBean seasonBean = seasonService.getSeasonById(id);

		model.addAttribute("seasonBean", seasonBean);
		return "season";
	}

	@RequestMapping(value = "/episode", method = RequestMethod.GET)
	public String episode(Model model, @RequestParam Long id)
	{

		EpisodeBean episodeBean = episodeService.getEpisodeById(id);

		model.addAttribute("episodeBean", episodeBean);
		return "episode";
	}

	@RequestMapping(value = "/serie-new", method = RequestMethod.GET)
	public String newSerie(Model model)
	{
		SerieBean serieBean = new SerieBean();

		model.addAttribute("serieBean", serieBean);
		return "newserie";
	}

	@RequestMapping(value = "/serie-new", method = RequestMethod.POST)
	public String newSerie(Model model, SerieBean serieBean)
	{
		serieService.save(serieBean);

		return overview(model);
	}

	@RequestMapping(value = "/serie-edit", method = RequestMethod.GET)
	public String editSerie(Model model, @RequestParam(required = true) Long id)
	{
		SerieBean serieBean = serieService.getSerieById(id);

		model.addAttribute("serieBean", serieBean);
		return "editserie";
	}

	@RequestMapping(value = "/serie-edit", method = RequestMethod.POST)
	public String editSerie(Model model, SerieBean serieBean)
	{
		serieService.update(serieBean);

		return overview(model);
	}

	@RequestMapping(value = "/season-edit", method = RequestMethod.GET)
	public String editSeason(Model model, @RequestParam(required = true) Long id)
	{
		SeasonBean seasonBean = seasonService.getSeasonById(id);

		model.addAttribute("seasonBean", seasonBean);
		return "editseason";
	}

	@RequestMapping(value = "/season-edit", method = RequestMethod.POST)
	public String editSeason(Model model, SeasonBean seasonBean)
	{
		seasonService.update(seasonBean);

		return serie(model, seasonBean.getSerieId());
	}

	@RequestMapping(value = "/episode-edit", method = RequestMethod.GET)
	public String editEpisode(Model model, @RequestParam(required = true) Long id)
	{
		EpisodeBean episodeBean = episodeService.getEpisodeById(id);

		model.addAttribute("episodeBean", episodeBean);
		return "editepisode";
	}

	@RequestMapping(value = "/episode-edit", method = RequestMethod.POST)
	public String editEpisode(Model model, EpisodeBean episodeBean)
	{
		episodeService.update(episodeBean);

		model.addAttribute("serieBean", episodeBean);
		return  season(model, episodeBean.getSeasonId());
	}

}

package com.assignment.todo.service;

import com.assignment.todo.web.bean.EpisodeBean;

public interface EpisodeService
{
	public Boolean update(EpisodeBean episodeBean);

	public EpisodeBean getEpisodeById(Long id);
}

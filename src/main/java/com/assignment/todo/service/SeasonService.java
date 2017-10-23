package com.assignment.todo.service;

import com.assignment.todo.model.Season;
import com.assignment.todo.web.bean.SeasonBean;

public interface SeasonService
{
	public Boolean update(SeasonBean seasonBean);

	public Boolean isSeasonCompleted(Season season);

	public SeasonBean getSeasonById(Long id);
}

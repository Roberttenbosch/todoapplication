package com.assignment.todo.service;

import java.util.List;

import com.assignment.todo.web.bean.SerieBean;

public interface SerieService
{
	public void save(SerieBean serieBean);

	public Boolean update(SerieBean serieBean);

	public SerieBean getSerieById(Long id);

	public List<SerieBean> getAllSeries();

	public List<SerieBean> getCompletedSeries();

	public List<SerieBean> getNotCompletedSeries();
	
}

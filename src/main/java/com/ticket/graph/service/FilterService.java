package com.ticket.graph.service;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

import com.ticket.graph.entity.Filter;
import com.ticket.graph.entity.dto.ConfigItemAndTaskTypeRequestDto;
import com.ticket.graph.entity.dto.FIlterDataResponse;
import com.ticket.graph.entity.dto.FilterDataSaveDto;
import com.ticket.graph.entity.dto.PriorityConfigurationRequestDto;
@Service

public interface FilterService {
	public String save(FilterDataSaveDto data);
	
//	public int priorityConfiguration(PriorityConfigurationRequestDto configurationRequestDto);
	public Map<String, Long> state();
	public Map<String,Long> tasktype();
	public Map<String, Long> priority();
	public Map<String, Long> assignmentGroup();

	public Map<String, Long> configurationItem();

	public Map<String, Long> opened();

//	public int taskTypeConfiguration(ConfigItemAndTaskTypeRequestDto taskTypeRequestDto);

	public FIlterDataResponse searchByNumber(String number);

	public List<Filter> getFilterData();
	
//	public Map<String, Long> month();
	
	public Map<String, Long> month(String year);

//	Map<String, Long>montha();
//
//	public Map<String, Long>monthb();
//
//	public Map<String, Long>monthc();
//
//	public Map<String, Long>monthd();
	
	public Map<String,Integer> priorityConfiguration();
	
	public Map<String,Integer>  taskTypeConfiguration();


	
	 //List<Filter> getFilterData();
}

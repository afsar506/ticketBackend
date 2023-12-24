package com.ticket.graph.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ticket.graph.entity.Filter;
import com.ticket.graph.entity.dto.FIlterDataResponse;
import com.ticket.graph.entity.dto.FilterDataSaveDto;
import com.ticket.graph.entity.dto.SearchByNumberDto;
import com.ticket.graph.service.FilterService;

@RestController
@CrossOrigin
public class FilterController {
	@Autowired
	public FilterService filterService;

	@GetMapping("/")
	public String home() {
		return "welcome to my home ";
	}

	@PostMapping("/data")
	public String save(@RequestBody FilterDataSaveDto data) {
		String res = filterService.save(data);
		return res;
	}

	@GetMapping("/search-by-number")
	public FIlterDataResponse searchByNumber(@RequestParam String number) {
		 FIlterDataResponse res= filterService.searchByNumber(number);
		 return res;
	}
	
	
	
	
	
	
	@GetMapping("/tasktype")
	public Map<String, Long> tasktype() {
		return filterService.tasktype();
	}
	@GetMapping("/state")
	public Map<String, Long> state() {
		return filterService.state();
	}
	@GetMapping("/priority")
	public Map<String, Long> priority() {
		return filterService.priority();
	}
	@GetMapping("/assignment-group")
	public Map<String, Long> assignmentGroup() {
		return filterService.assignmentGroup();
	}
	@GetMapping("/configuration-item")
	public Map<String, Long> configurationItem() {
		return filterService.configurationItem();
	}
	
	@GetMapping("priority-configuration")
	public Map<String,Integer> priorityConfiguration(){
		return filterService.priorityConfiguration();
	}
	
	@GetMapping("configuration-tasktype")
	public Map<String,Integer> configurationTasktype(){
		return filterService.taskTypeConfiguration();
	}
	
	
	
	
//	@GetMapping("/month")
//	public Map<String, Long> month() {
//		return filterService.month();
//	}
	
	@GetMapping("/month-filter")
	public Map<String, Long> month(@RequestParam(name = "year", required = false, defaultValue = "Guest") String year) {
		return filterService.month(year);
	}
	
	
	
	
	
//	@GetMapping("/search-by-number")
//	public FIlterDataResponse searchByNumber(@RequestParam String number) {
//		 FIlterDataResponse res= filterService.searchByNumber(number);
//		 return res;
//	}
//	
	
	
	@GetMapping("/ticket-details")
	public List<Filter> getfilters() {
		return this.filterService.getFilterData();
	}

	
	
	
	
}

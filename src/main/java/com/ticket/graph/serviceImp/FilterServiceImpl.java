package com.ticket.graph.serviceImp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticket.graph.entity.Filter;
import com.ticket.graph.entity.dto.ConfigItemAndTaskTypeRequestDto;
import com.ticket.graph.entity.dto.FIlterDataResponse;
import com.ticket.graph.entity.dto.FilterDataSaveDto;
import com.ticket.graph.repository.FilterRepository;
import com.ticket.graph.service.FilterService;

@SuppressWarnings("unused")
@Service
public class FilterServiceImpl implements FilterService {
	@Autowired

	FilterRepository filterRepository;

	public String save(FilterDataSaveDto data) {
		Filter filters = new Filter();
		filters.setId(1895L);
		filters.setNumber(numberGenration());
		filters.setAssignmentGroup(data.getAssignmentGroup());
		filters.setConfigurationItem(data.getConfigurationItem());
		filters.setOpened(data.getOpened());
		filters.setPriority(data.getPriority());
		filters.setShortDescription(data.getShortDescription());
		filters.setState(data.getState());
		filters.setTaskType(data.getTaskType());
		String res = "" + filters.getNumber();
		try {
			System.out.println(filters);
			filterRepository.save(filters);
			res = "Success";
		} catch (Exception e) {
			res = "Exception occured";
		}

		return res;

	}

	public String numberGenration() {
		Random random = new Random();
		int min = 10000000;
		int max = 99999999;
		int l = random.nextInt(3 - 1 + 1) + 1;
		switch (l) {
		case 1: {
			StringBuilder number = new StringBuilder();
			number.append("INC0000");
			number.append(random.nextInt(max - min + 1) + min);
			return number.toString();
		}
		case 2: {
			StringBuilder number = new StringBuilder();
			number.append("TASK0000");
			number.append(random.nextInt(max - min + 1) + min);
			return number.toString();
		}
		case 3: {
			StringBuilder number = new StringBuilder();
			number.append("CHG0000");
			number.append(random.nextInt(max - min + 1) + min);
			return number.toString();
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + l);
		}

	}

	public Map<String, Integer> priorityConfiguration() {
		Map<String, Integer> finalRes = new HashMap<String, Integer>();
		List<Filter> findAll = filterRepository.findAll();
		Set<String> priorities = new HashSet<String>();
		Set<String> configuration = new HashSet<String>();
		findAll.forEach(e -> {
			priorities.add(e.getPriority());
			configuration.add(e.getConfigurationItem());
		});
		for (String tempPri : priorities) {
			for (String tempCong : configuration) {
				findAll.forEach(r -> {
					if (r.getPriority().equalsIgnoreCase(tempPri)
							&& r.getConfigurationItem().equalsIgnoreCase(tempCong)) {
						String tempKey = tempPri + " and " + tempCong;
						if (!finalRes.containsKey(tempKey)) {
							finalRes.put(tempKey, 1);
						} else {
							int tempVal = finalRes.get(tempKey);
							finalRes.remove(tempKey);
							finalRes.put(tempKey, tempVal + 1);
						}
					}
				});
			}
		}
		return finalRes;
	}

	public Map<String, Long> tasktype() {
		List<Filter> dbRes = filterRepository.findAll();
		Map<String, Long> res = dbRes.stream()
				.collect(Collectors.groupingBy(Filter::getTaskType, Collectors.counting()));
		return res;
	}

	@Override
	public Map<String, Integer> taskTypeConfiguration() {
		Map<String, Integer> finalRes = new HashMap<String, Integer>();
		List<Filter> findAll = filterRepository.findAll();
		Set<String> taskType = new HashSet<String>();
		Set<String> configuration = new HashSet<String>();
		findAll.forEach(e -> {
			taskType.add(e.getTaskType());

			configuration.add(e.getConfigurationItem());
		});
		for (String tempTask : taskType) {
			for (String tempCong : configuration) {
				findAll.forEach(r -> {
					if (r.getTaskType().equalsIgnoreCase(tempTask)
							&& r.getConfigurationItem().equalsIgnoreCase(tempCong)) {
						String tempKey = tempTask + " and " + tempCong;
						if (!finalRes.containsKey(tempKey)) {
							finalRes.put(tempKey, 1);
						} else {
							int tempVal = finalRes.get(tempKey);
							finalRes.remove(tempKey);
							finalRes.put(tempKey, tempVal + 1);
						}
					}
				});
			}
		}
		return finalRes;

	}

	@Override
	public Map<String, Long> state() {
		List<Filter> dbRes = filterRepository.findAll();
		Map<String, Long> res = dbRes.stream().collect(Collectors.groupingBy(Filter::getState, Collectors.counting()));
		System.out.println(res);
		return res;
	}

	@Override
	public Map<String, Long> priority() {
		List<Filter> dbRes = filterRepository.findAll();
		Map<String, Long> res = dbRes.stream()
				.collect(Collectors.groupingBy(Filter::getPriority, Collectors.counting()));
		System.out.println(res);
		return res;
	}

	@Override
	public Map<String, Long> assignmentGroup() {
		List<Filter> dbRes = filterRepository.findAll();
		Map<String, Long> res = dbRes.stream()
				.collect(Collectors.groupingBy(Filter::getAssignmentGroup, Collectors.counting()));
		System.out.println(res);
		return res;
	}

	@Override
	public Map<String, Long> configurationItem() {
		List<Filter> dbRes = filterRepository.findAll();
		Map<String, Long> res = dbRes.stream()
				.collect(Collectors.groupingBy(Filter::getConfigurationItem, Collectors.counting()));
		System.out.println(res);
		return res;
	}

	// This method will return only count of dates with common month (jan, feb etc)
//	@Override
//	public Map<String, Long> month() {
//		Map<String, Long> res = new HashMap<String, Long>();
//		List<Filter> findAll = filterRepository.findAll();
//		for (Filter e : findAll) {
//			String[] split = e.getOpened().split("/");
//			String month = split[0];
//			if (res.containsKey(month)) {
//				Long temp = res.get(month);
//				res.remove(month);
//				res.put(month, temp + 1);
//
//			} else {
//				res.put(month, 1L);
//			}
//		}
//		;
//		return res;
//	}

	@Override
	public Map<String, Long> month(String year) {
		System.out.println("year="+year);
		Map<String, Long> res = new HashMap<String, Long>();
		List<Filter> findAll = filterRepository.findAll();
		for (Filter e : findAll) {
			String[] split = e.getOpened().split("/");
			String[] split2 = split[2].split(" ");
			String tempYear = split2[0];
			//String tempYear = split[2];
			System.out.println("tempYear="+tempYear);
			if (tempYear.equalsIgnoreCase(year)) {
				String month = split[0];
				String tempKey = tempYear + " - " + month;
				if (res.containsKey(tempKey)) {
					Long temp = res.get(tempKey);
					res.remove(tempKey);
					res.put(tempKey, temp + 1);
				} else {
					res.put(tempKey, 1L);
				}
			}
		}
		;
		return res;
	}

	@Override
	public FIlterDataResponse searchByNumber(String number) {
		FIlterDataResponse res = new FIlterDataResponse();
		Filter dataResponse = filterRepository.findByNumber(number);

		res.setAssignmentGroup(dataResponse.getAssignmentGroup());
		res.setConfigurationItem(dataResponse.getConfigurationItem());
		res.setNumber(dataResponse.getNumber());
		res.setOpened(dataResponse.getOpened());
		res.setPriority(dataResponse.getPriority());
		res.setShortDescription(dataResponse.getShortDescription());
		res.setState(dataResponse.getState());
		res.setTaskType(dataResponse.getTaskType());

		return res;
	}

	@Override
	public List<Filter> getFilterData() {
		List<Filter> dbRes = filterRepository.findAll();

		return dbRes;
	}

	@Override
	public Map<String, Long> opened() {
		// TODO Auto-generated method stub
		return null;
	}

	// This method will return only count of dates with common month with year 2020
//	@Override
//	public Map<String, Long> montha() {
//		Map<String, Long> res = new HashMap<String, Long>();
//		List<Filter> findAll = filterRepository.findAll();
//		for (Filter e : findAll) {
//			String[] split = e.getOpened().split("/");
//			String month = split[0];
//			if (res.containsKey(month)) {
//				Long temp = res.get(month);
//				res.remove(month);
//				res.put(month, temp + 1);
//
//			} else {
//				res.put(month, 1L);
//			}
//		}
//		;
//		return res;
//	}

	// This method will return only count of dates with common month with year 2021

	// This method will return only count of dates with common month with year 2022

	// This method will return only count of dates with common month with year 2023

//	@Override
//	public Map<String, Long> opened() {
//		List<Filter> dbRes = filterRepository.findAll();
//		
//
//        Map<String, List<Filter>> groupedByDate = new HashMap<>();
//
//        for (Filter student : dbRes) {
//            String opendDate = student.getOpened();
//
//            // Extract only the date part (without time)
//            String dateOnly = truncateTime(opendDate);
//            
//            List<Filter> studentsOnDate = groupedByDate.getOrDefault(dateOnly, new ArrayList<>());
//            studentsOnDate.add(student);
//            groupedByDate.put(dateOnly, studentsOnDate);
//        }
//        
//        
//        Map<String, Long> countMap = new HashMap<>();
//
//        for (Map.Entry<String, List<Filter>> entry : groupedByDate.entrySet()) {
//            String key = entry.getKey();
//            List<Filter> list = entry.getValue();
//            long count = list.size();
//            countMap.put(key, count);
//        }
//        return  countMap;
//		
///*		Map<String, Long> res = dbRes.stream() 
//				.collect(Collectors.groupingBy(Filter::getOpened,
//						Collectors.counting()));
//		System.out.println(res);
//		return res;
//		*/
//	}
//        
//        private  String truncateTime(String dateString) {
//        	
//        	String[] dateTimeParts = dateString.split(" ");
//        	System.out.println(dateTimeParts[0]+ ":" +dateTimeParts[1]);
//        	return dateTimeParts[0];
//        }
//   

}

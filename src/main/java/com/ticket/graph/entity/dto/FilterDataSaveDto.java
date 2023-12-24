package com.ticket.graph.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterDataSaveDto {
	private String priority;
	private String state;
	private String shortDescription;
	private String assignmentGroup;
	private String configurationItem;
	private String taskType;
	private String opened;
}

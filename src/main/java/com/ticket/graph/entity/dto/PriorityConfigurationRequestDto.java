package com.ticket.graph.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriorityConfigurationRequestDto {
	private String priority;
	private String configuration;
}

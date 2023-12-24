package com.ticket.graph.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfigItemAndTaskTypeRequestDto {
	private String taskType;
	private String configuration;
}

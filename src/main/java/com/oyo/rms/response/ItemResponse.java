package com.oyo.rms.response;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

public class ItemResponse {

	@Getter
	@Setter
	private Integer id;

	@Getter
	@Setter
	private String name;

	@Getter
	private String lastUpdatedOn;

	public void setLastUpdatedOn(LocalDateTime updatedAt) {
		this.lastUpdatedOn = updatedAt.toLocalDate().toString();
	}
}

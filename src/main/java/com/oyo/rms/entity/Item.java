package com.oyo.rms.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Entity
@Table(name = "items")
@ToString
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	@Setter
	Integer id;

	@NotNull
	@Getter
	@Setter
	private String name;

	@NotNull
	@Getter
	@Setter
	private Status status = Status.ACTIVE;

	@Getter
	@Setter
	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@Getter
	@Setter
	@Column(nullable = false)
	private LocalDateTime updatedAt;

	@PrePersist
	protected void onCreate() {
		this.updatedAt = this.createdAt = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = LocalDateTime.now();
	}

	public enum Status {
		ACTIVE("ACTIVE"), INACTIVE("INACTIVE");

		String name;

		Status(String value) {
			name = value;
		}

		String getName() {
			return name;
		}
	}

}

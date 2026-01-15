package com.ohgiraffers.ingredientstockservice.common.dto;

import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;

import java.time.LocalDateTime;

@Embeddable
@Getter
public class BaseTime {

	private LocalDateTime createdAt; // 기본값: created_at 매핑
	private LocalDateTime updatedAt; // 기본값: updated_at 매핑

	@PrePersist
	public void prePersist() {
		LocalDateTime now = LocalDateTime.now();
		this.createdAt = now;
		this.updatedAt = now;
	}

	@PreUpdate
	public void preUpdate() {
		this.updatedAt = LocalDateTime.now();
	}
}
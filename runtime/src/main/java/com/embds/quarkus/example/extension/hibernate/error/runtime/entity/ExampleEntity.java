package com.embds.quarkus.example.extension.hibernate.error.runtime.entity;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class ExampleEntity {

	@Id
	private UUID id;
	
	private String value;
	
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
	private Instant creationDate;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Instant getCreationDate() {
		return creationDate;
	}
	
}

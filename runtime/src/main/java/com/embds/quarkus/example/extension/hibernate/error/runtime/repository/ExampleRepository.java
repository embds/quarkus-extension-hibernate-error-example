package com.embds.quarkus.example.extension.hibernate.error.runtime.repository;

import java.util.List;
import java.util.UUID;

import com.embds.quarkus.example.extension.hibernate.error.runtime.entity.ExampleEntity;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ExampleRepository implements PanacheRepository<ExampleEntity> {

	public Uni<ExampleEntity> findById(final UUID id){
        return find("id", id).firstResult();
    }
	
	public Uni<List<ExampleEntity>> findById(final List<UUID> ids){
        return list("id in(?1)", ids);
    }
}

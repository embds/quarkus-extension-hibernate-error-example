/*
* Licensed to the Apache Software Foundation (ASF) under one or more
* contributor license agreements.  See the NOTICE file distributed with
* this work for additional information regarding copyright ownership.
* The ASF licenses this file to You under the Apache License, Version 2.0
* (the "License"); you may not use this file except in compliance with
* the License.  You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.embds.quarkus.example.extension.hibernate.error.it;

import java.util.List;
import java.util.UUID;

import com.embds.quarkus.example.extension.hibernate.error.runtime.entity.ExampleEntity;
import com.embds.quarkus.example.extension.hibernate.error.runtime.repository.ExampleRepository;

import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("it")
@ApplicationScoped
@WithTransaction
public class QuarkusExtensionHibernateErrorExampleResource {

	private ExampleRepository exampleRepository;
	
	public QuarkusExtensionHibernateErrorExampleResource(final ExampleRepository exampleRepository) {
		this.exampleRepository = exampleRepository;
	}
	

    @GET
    @Path("single-save-and-return")
    public Uni<ExampleEntity> singlePersistAndreturn() {
    	return this.exampleRepository.persist(createExampleEntity(UUID.randomUUID(), "single return"));
    }
    

    @GET
    @Path("single-save-and-get")
    public Uni<ExampleEntity> singlePersistAndGet() {
    	UUID id1 = UUID.randomUUID();
    	return this.exampleRepository.persist(createExampleEntity(id1, "single get"))
    			.chain(v -> this.exampleRepository.findById(id1));
    	
    }
	
    @GET
    @Path("multiple-save-and-get")
    public Uni<List<ExampleEntity>> multiplePersistAndGet() {
    	UUID id1 = UUID.randomUUID();
    	UUID id2 = UUID.randomUUID();
    	return this.exampleRepository.persist(createExampleEntity(id1, "1"))
    			.chain(v -> this.exampleRepository.persist(createExampleEntity(id2, "2")))
    			.chain(v -> this.exampleRepository.findById(List.of(id1, id2)));
    	
    }
    
    private ExampleEntity createExampleEntity(final UUID id, final String value) {
    	ExampleEntity ent = new ExampleEntity();
    	ent.setId(id);
    	ent.setValue("value : " + value);
    	return ent;
    }
}

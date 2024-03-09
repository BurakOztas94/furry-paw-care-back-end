package com.oztasburak.furrypawcare.config;

public interface BaseService<Entity, EntityRequest, EntityResponse> {
    Entity getById(Long id);
    EntityResponse getResponseById(Long id);
    Entity getAll();
    EntityResponse getAllResponses();
    EntityResponse create(EntityRequest entityRequest);
    EntityResponse update(Long id, EntityRequest entityRequest);
    void deleteById(Long id);
}

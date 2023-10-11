package ru.poplaukhin.test.response;

public class EntityResponse<T> {
    private final boolean entityFound;
    private final T entity;

    public EntityResponse(boolean entityFound, T entity) {
        this.entityFound = entityFound;
        this.entity = entity;
    }

    public boolean isEntityFound() {
        return entityFound;
    }

    public T getEntity() {
        return entity;
    }
}

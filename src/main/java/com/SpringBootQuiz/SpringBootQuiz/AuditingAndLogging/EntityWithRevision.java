package com.SpringBootQuiz.SpringBootQuiz.AuditingAndLogging;

public class EntityWithRevision<T> {
    private final RevisionsEntity revision;

    private final T entity;

    public EntityWithRevision(RevisionsEntity revision, T entity) {
        this.revision = revision;
        this.entity = entity;
    }

    public RevisionsEntity getRevision() {
        return revision;
    }

    public T getEntity() {
        return entity;
    }
}

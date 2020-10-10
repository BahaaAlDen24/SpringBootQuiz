package com.SpringBootQuiz.SpringBootQuiz.SalesOperationsRevision;

import com.SpringBootQuiz.SpringBootQuiz.AuditingAndLogging.EntityWithRevision;
import com.SpringBootQuiz.SpringBootQuiz.AuditingAndLogging.RevisionsEntity;
import com.SpringBootQuiz.SpringBootQuiz.SalesOperations.SaleOperation;
import com.SpringBootQuiz.SpringBootQuiz.SalesOperations.SaleOperationService;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class SaleOperationRevisionRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private SaleOperationService saleOperationService;

    public List<EntityWithRevision<SaleOperation>> getSaleOperationRevisions(Long SaleOperationID) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);
        SaleOperation saleOperationObject = saleOperationService.getSaleOperationByID(SaleOperationID);

        List<Number> revisions = auditReader.getRevisions(SaleOperation.class, SaleOperationID);

        List<EntityWithRevision<SaleOperation>> saleOperationRevisions = new ArrayList<>();
        for (Number revision : revisions) {
            SaleOperation saleOperationRevision = auditReader.find(SaleOperation.class, SaleOperationID, revision);
            Date revisionDate = auditReader.getRevisionDate(revision);

            saleOperationRevisions.add(
                    new EntityWithRevision(
                            new RevisionsEntity(revision.longValue(), revisionDate), saleOperationRevision));
        }

        return saleOperationRevisions;
    }
}

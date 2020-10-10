package com.SpringBootQuiz.SpringBootQuiz.SalesTransactionsRevision;

import com.SpringBootQuiz.SpringBootQuiz.AuditingAndLogging.EntityWithRevision;
import com.SpringBootQuiz.SpringBootQuiz.AuditingAndLogging.RevisionsEntity;
import com.SpringBootQuiz.SpringBootQuiz.SalesTransactions.SaleTransaction;
import com.SpringBootQuiz.SpringBootQuiz.SalesTransactions.SaleTransactionService;
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
public class SaleTransactionRevisionRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private SaleTransactionService saleTransactionService;

    public List<EntityWithRevision<SaleTransaction>> getSaleTransactionRevisions(Long saleTransactionID) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);
        SaleTransaction saleTransactionObject = saleTransactionService.getSaleTransactionByID(saleTransactionID);

        List<Number> revisions = auditReader.getRevisions(SaleTransaction.class, saleTransactionID);

        List<EntityWithRevision<SaleTransaction>> saleTransactionRevisions = new ArrayList<>();
        for (Number revision : revisions) {
            SaleTransaction saleTransactionRevision = auditReader.find(SaleTransaction.class, saleTransactionID, revision);
            Date revisionDate = auditReader.getRevisionDate(revision);

            saleTransactionRevisions.add(
                    new EntityWithRevision(
                            new RevisionsEntity(revision.longValue(), revisionDate), saleTransactionRevision));
        }

        return saleTransactionRevisions;
    }

}

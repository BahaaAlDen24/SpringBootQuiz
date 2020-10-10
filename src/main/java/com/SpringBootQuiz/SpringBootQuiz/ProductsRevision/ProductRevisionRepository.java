package com.SpringBootQuiz.SpringBootQuiz.ProductsRevision;

import com.SpringBootQuiz.SpringBootQuiz.AuditingAndLogging.EntityWithRevision;
import com.SpringBootQuiz.SpringBootQuiz.AuditingAndLogging.RevisionsEntity;
import com.SpringBootQuiz.SpringBootQuiz.Products.Product;
import com.SpringBootQuiz.SpringBootQuiz.Products.ProductService;
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
public class  ProductRevisionRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ProductService productService;

    public List<EntityWithRevision<Product>> getProductRevisions(Long productID) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);
        Product productObject = productService.getProductByID(productID);

        List<Number> revisions = auditReader.getRevisions(Product.class, productID);

        List<EntityWithRevision<Product>> productRevisions = new ArrayList<>();
        for (Number revision : revisions) {
            Product productRevision = auditReader.find(Product.class, productID, revision);
            Date revisionDate = auditReader.getRevisionDate(revision);

            productRevisions.add(
                    new EntityWithRevision(
                            new RevisionsEntity(revision.longValue(), revisionDate), productRevision));
        }

        return productRevisions;
    }
}

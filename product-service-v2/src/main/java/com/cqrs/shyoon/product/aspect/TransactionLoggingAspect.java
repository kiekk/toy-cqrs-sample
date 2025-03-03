package com.cqrs.shyoon.product.aspect;

import jakarta.persistence.EntityManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Objects;

@Slf4j
@Aspect
@Component
public class TransactionLoggingAspect {

    private final EntityManagerFactory writeEntityManagerFactory;
    private final EntityManagerFactory readEntityManagerFactory;

    public TransactionLoggingAspect(
            @Qualifier("writeEntityManagerFactory") EntityManagerFactory writeEntityManagerFactory,
            @Qualifier("readEntityManagerFactory") EntityManagerFactory readEntityManagerFactory) {
        this.writeEntityManagerFactory = writeEntityManagerFactory;
        this.readEntityManagerFactory = readEntityManagerFactory;
    }

    @Around("within(@org.springframework.transaction.annotation.Transactional *)")
    public Object logTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        Transactional transactional = joinPoint.getTarget().getClass().getAnnotation(Transactional.class);
        boolean isReadOnly = Objects.nonNull(transactional) && transactional.readOnly();

        String transactionType = isReadOnly ? "READ" : "WRITE";
        String currentDataSource = TransactionSynchronizationManager.getCurrentTransactionName();
        String persistenceUnit = getPersistenceUnit(isReadOnly);

        log.info("🔄 [Transaction Start] Type: {}, DataSource: {}, PersistenceUnit: {}",
                transactionType, currentDataSource, persistenceUnit);

        try {
            Object result = joinPoint.proceed();
            log.info("✅ [Transaction Commit] Type: {}, DataSource: {}, PersistenceUnit: {}",
                    transactionType, currentDataSource, persistenceUnit);
            return result;
        } catch (Exception e) {
            log.error("❌ [Transaction Rollback] Type: {}, DataSource: {}, PersistenceUnit: {}",
                    transactionType, currentDataSource, persistenceUnit, e);
            throw e;
        }
    }

    private String getPersistenceUnit(boolean isReadOnly) {
        EntityManagerFactory emf = isReadOnly ? readEntityManagerFactory : writeEntityManagerFactory;
        return emf.getProperties().getOrDefault("hibernate.persistenceUnitName", "").toString();
    }
}

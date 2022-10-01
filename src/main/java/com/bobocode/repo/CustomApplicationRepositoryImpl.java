package com.bobocode.repo;

import com.bobocode.domain.Application;
import com.bobocode.domain.CreditRange;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.Optional;

@RequiredArgsConstructor
public class CustomApplicationRepositoryImpl implements CustomApplicationRepository {

    private final EntityManager entityManager;
    private final static String SELECT_NEW_APPLICATION_QUERY_BY_CREDIT_RANGE = """
            select a from Application a
            where a.status = 'NEW' 
            and a.creditAmountUSD >=:min and a.creditAmountUSD <= :max 
            order by a.createdAt
            """;

    @Override
    public Optional<Application> findNewApplicationWithinCreditBracket(CreditRange creditRange) {
        return entityManager.createQuery(SELECT_NEW_APPLICATION_QUERY_BY_CREDIT_RANGE, Application.class)
                .setMaxResults(1)
                .setParameter("min", creditRange.min())
                .setParameter("max", creditRange.max())
                .getResultStream()
                .findFirst();
    }
}

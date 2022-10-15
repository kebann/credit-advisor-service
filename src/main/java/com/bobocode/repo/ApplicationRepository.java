package com.bobocode.repo;

import com.bobocode.domain.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long>, CustomApplicationRepository {

//    Optional<Application> findFirstByStatusAndCreditAmountUSDBetweenOrderByCreatedAt(Status status,
//                                                                                     BigDecimal minCreditAmount, BigDecimal maxCreditAmount);
//
//    default Optional<Application> findNewApplicationWithinCreditBracket(CreditRange creditRange) {
//        return findFirstByStatusAndCreditAmountUSDBetweenOrderByCreatedAt(Status.NEW, creditRange.min(), creditRange.max());
//    }
}

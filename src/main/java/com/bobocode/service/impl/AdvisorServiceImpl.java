package com.bobocode.service.impl;

import com.bobocode.domain.Advisor;
import com.bobocode.domain.Application;
import com.bobocode.domain.CreditRange;
import com.bobocode.enums.RoleType;
import com.bobocode.exception.NoAdvisorFoundException;
import com.bobocode.exception.NoApplicationFoundException;
import com.bobocode.repo.AdvisorRepository;
import com.bobocode.repo.ApplicationRepository;
import com.bobocode.service.AdvisorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import static com.bobocode.enums.RoleType.*;
import static com.bobocode.enums.Status.NEW;
import static java.math.BigDecimal.ZERO;
import static java.math.BigDecimal.valueOf;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdvisorServiceImpl implements AdvisorService {

    private final ApplicationRepository applicationRepository;
    private final AdvisorRepository advisorRepository;

    private final Map<RoleType, CreditRange> creditRangeByRoles = Map.of(
            ASSOCIATE, new CreditRange(ZERO, valueOf(9999)),
            PARTNER, new CreditRange(valueOf(10000), valueOf(50000)),
            SENIOR, new CreditRange(valueOf(50001), valueOf(Integer.MAX_VALUE)));

    @Override
    @Transactional
    public Application assignApplication(Long advisorId) {
        log.info("Searching for advisor with id = {}", advisorId);

        Advisor advisor = advisorRepository.findById(advisorId)
                .orElseThrow(() -> new NoAdvisorFoundException("No advisor with id=%s is found".formatted(advisorId)));

//      To fail fast prior to fetching applications from DB
        if (advisor.hasAssignedApplication()) {
            throw new IllegalStateException("Advisor already has assigned application");
        }

        Application application = findNewApplicationForAdvisor(advisor);
        advisor.assignApplication(application);

        log.info("Assigned application with id={} to advisor with id={}", application.getId(), advisor.getId());

        return application;
    }

    private Application findNewApplicationForAdvisor(Advisor advisor) {
        log.info("Searching for NEW application to assign to the advisor ");

        CreditRange creditRange = getQualifiedCreditRangeByRole(advisor.getRoleType());
        return applicationRepository.findNewApplicationWithinCreditBracket(creditRange)
                .orElseThrow(() -> new NoApplicationFoundException(NEW, creditRange));
    }

    private CreditRange getQualifiedCreditRangeByRole(RoleType advisorRole) {
        return creditRangeByRoles.get(advisorRole);
    }
}

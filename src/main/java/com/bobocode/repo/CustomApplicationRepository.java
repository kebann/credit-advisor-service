package com.bobocode.repo;

import com.bobocode.domain.Application;
import com.bobocode.domain.CreditRange;

import java.util.Optional;

public interface CustomApplicationRepository {

    Optional<Application> findNewApplicationWithinCreditBracket(CreditRange creditRange);
}
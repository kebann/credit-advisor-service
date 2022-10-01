package com.bobocode.exception;

import com.bobocode.domain.CreditRange;
import com.bobocode.enums.Status;

public class NoApplicationFoundException extends RuntimeException {

    public NoApplicationFoundException(String message) {
        super(message);
    }

    public NoApplicationFoundException(Status status, CreditRange creditRange) {
        super(
                "No qualifying application with status = %s and within the credit range: %s - %s USD is found".formatted(
                        status,
                        creditRange.min(), creditRange.max()));
    }
}
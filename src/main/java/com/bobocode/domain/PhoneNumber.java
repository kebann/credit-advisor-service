package com.bobocode.domain;

import com.bobocode.enums.PhoneNumberType;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
@Data
public class PhoneNumber {

    @Enumerated(EnumType.STRING)
    private PhoneNumberType type;

    @Column(nullable = false, unique = true)
    private String number;
}
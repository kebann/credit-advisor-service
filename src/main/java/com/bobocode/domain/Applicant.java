package com.bobocode.domain;

import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "applicants")
@Table
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Applicant extends User {

    @OneToMany(mappedBy = "applicant")
    @Setter(AccessLevel.PRIVATE)
    private List<Application> applications = new ArrayList<>();

    @NaturalId
    @Column(nullable = false, unique = true)
    private String ssn;

    @Embedded
    private Address address;
    @ElementCollection
    @CollectionTable(name = "applicant_phones_numbers", joinColumns = @JoinColumn(name = "applicant_id"))
    private List<PhoneNumber> phoneNumbers = new ArrayList<>();
}

package com.bobocode.domain;

import com.bobocode.enums.RoleType;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.EnumType.STRING;

@Entity(name = "advisors")
@Table
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Advisor extends User {

    @Enumerated(STRING)
    @Column(nullable = false)
    private RoleType roleType;

    @OneToMany(mappedBy = "advisor")
    @Setter(AccessLevel.PRIVATE)
    private List<Application> applications = new ArrayList<>();

    public void assignApplication(Application application) {
        application.assignTo(this);
        this.applications.add(application);
    }

    public boolean hasAssignedApplication() {
        return applications.stream()
                .anyMatch(Application::isAssigned);
    }
}

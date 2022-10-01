package com.bobocode.domain;

import com.bobocode.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.bobocode.enums.Status.ASSIGNED;
import static com.bobocode.enums.Status.NEW;

@Entity
@Table(name = "applications")
@Getter
@Setter
@ToString(exclude = {"advisor", "applicant"})
@NoArgsConstructor
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "credit_amount_usd")
    private BigDecimal creditAmountUSD;

    @Version
    private Long version;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "advisor_id")
    private Advisor advisor;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;

    @Enumerated(EnumType.STRING)
    private Status status = NEW;

    @Column(nullable = false, updatable = false, insertable = false)
    private LocalDateTime createdAt;

    private LocalDateTime assignedAt;

    private LocalDateTime resolvedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Application that)) {
            return false;
        }

        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public void assignTo(Advisor advisor) {
        if (isAssigned()) {
            throw new IllegalStateException("Application is already assigned");
        }

        this.setAdvisor(advisor);
        this.setStatus(ASSIGNED);
        this.setAssignedAt(LocalDateTime.now());
    }

    public boolean isAssigned() {
        return status == ASSIGNED;
    }
}
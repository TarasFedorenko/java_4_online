package ua.com.alevel.Finance.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class Operation extends BaseEntity {

    @Column(name = "operation_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date operationDate;
    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private Category category;
    @Column(name = "amount")
    private BigDecimal amount;
    @ManyToOne
    private Account senderAccount;
    @ManyToOne
    private Account recipientAccount;

    public Operation() {

    }
}

package ua.com.alevel.Finance.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "accounts")
public class Account extends BaseEntity {

    @Column(name = "account_name")
    @NotEmpty(message = "Field should not be empty!")
    @Size(min = 3, max = 30, message = "Field must be at least 3 to 30 characters!")
    private String accountName;

    @Column(name = "account_number")
    private int accountNumber;

    @Column(name = "balance")
    @NotNull(message = "Field should not be empty!")
    @Min(value = 0, message = "Balance must be greater than 0!")
    private BigDecimal balance;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "recipientAccount", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<DebitOperation> debitOperations;

    @OneToMany(mappedBy = "senderAccount", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CreditOperation> creditOperations;

    public Account() {
        super();
    }
}

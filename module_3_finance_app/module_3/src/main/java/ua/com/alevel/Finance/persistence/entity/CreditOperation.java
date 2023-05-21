package ua.com.alevel.Finance.persistence.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "credit_operation")
public class CreditOperation extends Operation {

    public CreditOperation() {
        super();
    }

}

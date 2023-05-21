package ua.com.alevel.Finance.persistence.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "debit_operation")
public class DebitOperation extends Operation {

    public DebitOperation() {
        super();
    }

}

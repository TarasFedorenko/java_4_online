package ua.com.alevel.Finance.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "clients")
public class Client extends BaseEntity {

    @Column(name = "full_name")
    @NotEmpty(message = "Field should not be empty!")
    @Size(min = 3, max = 30, message = "Field must be at least 3 to 30 characters!")
    private String fullName;

    @Column(name = "email")
    @Email(message = "Incorrect email!")
    @NotEmpty(message = "Field should not be empty!")
    private String email;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Account> accounts;

    public Client() {
        super();
    }
}

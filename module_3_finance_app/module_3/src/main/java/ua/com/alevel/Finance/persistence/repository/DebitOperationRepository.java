package ua.com.alevel.Finance.persistence.repository;

import org.springframework.stereotype.Repository;
import ua.com.alevel.Finance.persistence.entity.Account;
import ua.com.alevel.Finance.persistence.entity.DebitOperation;

import java.util.Date;
import java.util.List;


@Repository
public interface DebitOperationRepository extends OperationRepository {

    List<DebitOperation> findByRecipientAccountAndOperationDateBetween(Account account, Date fromDate, Date toDate);
}

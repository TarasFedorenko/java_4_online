package ua.com.alevel.Finance.persistence.repository;

import org.springframework.data.repository.NoRepositoryBean;
import ua.com.alevel.Finance.persistence.entity.Operation;

@NoRepositoryBean
public interface OperationRepository extends BaseRepository<Operation> {

}
package TODOservice.dao;

import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
@Repository
public class TODOServiceDAOImpl {
    @PersistenceContext
    private EntityManager entityManager;

}

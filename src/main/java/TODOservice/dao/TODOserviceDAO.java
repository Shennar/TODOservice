package TODOservice.dao;

import TODOservice.domain.TODOPost;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

    @Repository
    public interface TODOserviceDAO extends CrudRepository <TODOPost, Long>{}

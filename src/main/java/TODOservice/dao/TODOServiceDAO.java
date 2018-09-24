package TODOservice.dao;

import TODOservice.domain.TODOPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

    @Repository
    public interface TODOServiceDAO extends JpaRepository<TODOPost, Long> {}

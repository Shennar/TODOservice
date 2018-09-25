package TODOservice.dao;

import TODOservice.domain.TODOPost;
import org.springframework.data.jpa.repository.JpaRepository;

    public interface TODOServiceDAO extends JpaRepository<TODOPost, Long> {}

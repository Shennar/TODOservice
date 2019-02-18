package todoservice.dao;

import todoservice.domain.TodoPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component ("todoServiceDAO")
public interface TodoServiceDao extends JpaRepository<TodoPost, Long> {
}
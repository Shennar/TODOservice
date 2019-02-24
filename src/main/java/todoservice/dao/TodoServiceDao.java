package todoservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import todoservice.domain.TodoPost;

@Component("todoServiceDAO")
public interface TodoServiceDao extends JpaRepository<TodoPost, Long> {
}
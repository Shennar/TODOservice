package todoservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import todoservice.domain.TodoPost;

public interface TodoServiceDao extends JpaRepository<TodoPost, Long> {
}
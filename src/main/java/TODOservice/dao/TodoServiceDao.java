package TODOservice.dao;

import TODOservice.domain.TodoPost;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoServiceDao extends JpaRepository<TodoPost, Long> {
}
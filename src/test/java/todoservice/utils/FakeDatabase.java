package todoservice.utils;

import org.springframework.data.jpa.repository.JpaRepository;
import todoservice.dao.TodoServiceDao;
import todoservice.domain.TodoPost;
import todoservice.web.dto.TodoPostDto;
import org.dozer.DozerBeanMapper;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class FakeDatabase implements TodoServiceDao {

    final private DozerBeanMapper mapper = new DozerBeanMapper();
    private List<TodoPost> fakeRepository = new ArrayList<>();

    @Override
    public TodoPost saveAndFlush(final TodoPost entity) {
        final String datum = entity.getDatum();
        final String whatTodo = entity.getWhatTODO();
        final boolean doneStatus = entity.isDoneStatus();
        final TodoPost post = new TodoPost();
        post.setId(1L);
        post.setDatum(datum);
        post.setWhatTODO(whatTodo);
        post.setDoneStatus(doneStatus);
        return post;
    }

    @Override
    public List<TodoPost> findAll() {
        List<TodoPost> fakeDatabase = new ArrayList<>();
        TodoPostDto postDto;
        TodoPost post;
        Long id;
        for (int i = 0; i < 5; i++) {
            id = Long.parseLong("" + i);
            postDto = new TodoPostDto(id, "Fakedate" + (i + 1), "Task " + (i + 1), "false");
            post = mapper.map(postDto, TodoPost.class);
            fakeDatabase.add(post);
        }
        return fakeDatabase;
    }

    public List<TodoPost> getFakeRepository() {
        return fakeRepository;
    }

    @Override
    public long count() {
        return fakeRepository.size();
    }

    @Override
    public TodoPost save(TodoPost entity) {
        if (entity == null) {
            fakeRepository.clear();
        } else {
            fakeRepository.add(entity);
        }
        return entity;
    }

    @Override
    public void deleteById(final Long id) {
        if (id < 0) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public <S extends TodoPost> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<TodoPost> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public void flush() {

    }

    @Override
    public void deleteInBatch(Iterable<TodoPost> entities) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public TodoPost getOne(Long aLong) {
        return null;
    }

    @Override
    public <S extends TodoPost> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends TodoPost> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends TodoPost> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends TodoPost> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends TodoPost> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends TodoPost> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<TodoPost> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<TodoPost> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<TodoPost> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public void delete(TodoPost entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends TodoPost> entities) {

    }

    @Override
    public void deleteAll() {

    }
}

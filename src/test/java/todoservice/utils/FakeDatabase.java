package todoservice.utils;

import org.dozer.DozerBeanMapper;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import todoservice.dao.TodoServiceDao;
import todoservice.domain.TodoPost;
import todoservice.web.dto.TodoPostDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FakeDatabase implements TodoServiceDao {

    final private DozerBeanMapper mapper = new DozerBeanMapper();

    @Override
    public <S extends TodoPost> S save(S entity) {
        return null;
    }

    @Override
    public <S extends TodoPost> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public void flush() {

    }

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
    public Optional<TodoPost> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<TodoPost> findAll() {
        List<TodoPost> fakeDatabase = new ArrayList<>();
        TodoPostDto postDto;
        TodoPost post;
        Long id;
        for (int i = 0; i < 5; i++) {
            id = Long.parseLong("" + i);
            postDto = new TodoPostDto(id, "Fakedate" + (i + 1), "Task " + (i + 1), "Done");
            post = mapper.map(postDto, TodoPost.class);
            fakeDatabase.add(post);
        }
        return fakeDatabase;
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
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

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

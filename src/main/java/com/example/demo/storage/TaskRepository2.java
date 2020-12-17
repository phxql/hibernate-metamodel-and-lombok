package com.example.demo.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RequiredArgsConstructor
@Component
public class TaskRepository2 {
    private final EntityManager entityManager;

    @Transactional
    public void save(Task task) {
        entityManager.persist(task);
    }

    @Transactional(readOnly = true)
    public List<Task> findAllByTitle(String title) {
        var builder = entityManager.getCriteriaBuilder();
        var query = builder.createQuery(Task.class);

        var task = query.from(Task.class);

        var criteria = query.select(task).where(builder.equal(task.get(Task_.title), title));

        return this.entityManager.createQuery(criteria).getResultList();
    }

    public List<Task> findAll() {
        return this.entityManager.createQuery("SELECT t FROM Task t", Task.class).getResultList();
    }
}

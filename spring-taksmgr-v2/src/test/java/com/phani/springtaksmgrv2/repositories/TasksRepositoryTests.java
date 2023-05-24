package com.phani.springtaksmgrv2.repositories;

import com.phani.springtaksmgrv2.entities.TaskEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@DataJpaTest // When we run test spring creates a testdb in RAM and does not use the main db, (file db in this case)
public class TasksRepositoryTests {
    @Autowired TasksRepository tasksRepository;

    @Test // Each method will be running on different temp db. If we want to run more than one tests in a single temp db we need to configure in @DataJPA()
    public void testCreateTask(){
        TaskEntity task = new TaskEntity();
        task.setTitle("testTask");
        task.setCompleted(true);
        task.setDescription("test description");
        var savedTask = tasksRepository.save(task);
        assertNotNull(savedTask);
    }
    @Test
    public void readTasksWorks() {
        TaskEntity task1 = new TaskEntity();
        task1.setTitle("Test Task");
        task1.setDescription("Test Description");
        task1.setCompleted(false);
        TaskEntity task2 = new TaskEntity();
        task2.setTitle("Test Task 2");
        task2.setDescription("Test Description 2");
        task2.setCompleted(false);
        tasksRepository.save(task1);
        tasksRepository.save(task2);
        var tasks = tasksRepository.findAll();
        assertNotNull(tasks);
        assertEquals(2, tasks.size());
    }

    @Test
    public void findByCompletedWorks() {
        TaskEntity task1 = new TaskEntity();
        task1.setTitle("Test Task");
        task1.setDescription("Test Description");
        task1.setCompleted(false);
        TaskEntity task2 = new TaskEntity();
        task2.setTitle("Test Task 2");
        task2.setDescription("Test Description 2");
        task2.setCompleted(true);
        tasksRepository.save(task1);
        tasksRepository.save(task2);
        var completedTasks = tasksRepository.findAllByCompleted(true);
        var incompleteTasks = tasksRepository.findAllByCompleted(false);

        assertEquals(1, completedTasks.size());
        assertEquals(1, incompleteTasks.size());

    }
}

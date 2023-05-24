package com.phani.springtaksmgrv2.repositories;

import com.phani.springtaksmgrv2.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository // this is needed to inject this in the service layer
// This repository is extending another repository, but the final implementations are present in SimpleJPARepository<T, ID>
public interface TasksRepository extends JpaRepository<TaskEntity,Integer> {
    List<TaskEntity> findAllByCompleted(boolean completed);

    // Ideally this is 'business logic' terminology (i.e. 'overdue') so shouldn't be here
    @Query("SELECT t FROM tasks t WHERE t.completed = false AND t.dueDate < CURRENT_DATE")
    List<TaskEntity> findAllOverdue();

    List<TaskEntity> findAllByCompletedAndDueDateBefore(boolean completed, Date dueDate);

    List<TaskEntity> findAllByTitleContainingIgnoreCase(String titleFragment);
}

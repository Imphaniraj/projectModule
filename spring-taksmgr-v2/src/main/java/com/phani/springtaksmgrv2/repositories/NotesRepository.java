package com.phani.springtaksmgrv2.repositories;

import com.phani.springtaksmgrv2.entities.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // this is needed to inject this in the service layer
public interface NotesRepository extends JpaRepository<NoteEntity, Integer> {
}

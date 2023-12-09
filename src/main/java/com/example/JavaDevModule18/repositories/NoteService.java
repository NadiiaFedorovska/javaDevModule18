package com.example.JavaDevModule18.repositories;

import com.example.JavaDevModule18.entities.NoteEntity;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class NoteService {
    private static final Logger LOGGER = Logger.getLogger(NoteService.class);
    private final NoteRepository noteRepository;

    public void createNote(NoteEntity noteEntity) {
        long id = generateUniqueId();
        noteEntity.setId(id);
        noteRepository.save(noteEntity);
        LOGGER.info("add NoteEntity " + noteEntity + " to DB");
    }

    private long generateUniqueId() {
        Random random = new Random();
        long id = random.nextInt(100);
        LOGGER.info("generate Unique Id " + id);
        return id;
    }

    public boolean exists(long id){
        LOGGER.info("exist method");
        if (id == 0){
            return false;
        }
        return noteRepository.existsById(id);
    }

    public List<NoteEntity> findAll() {
        LOGGER.info("exist method");
        return noteRepository.findAll();
    }

    public NoteEntity getById(long id) {
        LOGGER.info("getById " + id);
        exists(id);
        return noteRepository.findById(id).orElseThrow();
    }

    public NoteEntity update(NoteEntity noteEntity) {
        long id = noteEntity.getId();
        NoteEntity foundNote = getById(id);
        foundNote.setTitle(noteEntity.getTitle());
        foundNote.setContent(noteEntity.getContent());
        LOGGER.info("Note was updated " + foundNote);
        return noteRepository.save(foundNote);
    }

    public void deleteById(long id) {
        noteRepository.deleteById(id);
        LOGGER.info("Note was deleted");
    }
}
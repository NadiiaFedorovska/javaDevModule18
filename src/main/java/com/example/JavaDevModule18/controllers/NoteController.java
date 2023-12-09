package com.example.JavaDevModule18.controllers;


import com.example.JavaDevModule18.entities.NoteEntity;
import com.example.JavaDevModule18.repositories.NoteService;
//import com.example.JavaDevModule18.security.SecurityService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/note")
public class NoteController {
    private static final Logger LOGGER = Logger.getLogger(NoteController.class);
    private final NoteService noteService;
    private List<NoteEntity> noteEntityList;

    @GetMapping
    public String startHello() {
        LOGGER.info("start hello method");
        return "hello";
    }

    @PostMapping
    public String startToCreate() {
        LOGGER.info("start to create method");
        return "redirect:/note/create";
    }

    @GetMapping("/create")
    public String createNotePage(Model model) {
        LOGGER.info("get create method");
        model.addAttribute("notes", new NoteEntity());
        return "newNote";
    }

    @PostMapping("/create")
    public String createNote(@ModelAttribute NoteEntity newNote) {
        LOGGER.info("post create method");
        noteService.createNote(newNote);
        return "redirect:/note/list";
    }

    @GetMapping("/list")
    public String getNoteList(Model model) {
        noteEntityList = noteService.findAll();
        model.addAttribute("notes", noteEntityList);
        LOGGER.info("get list method");
        return "noteList";
    }

    @PostMapping("/search")
    public String getNoteForId(@RequestParam (name = "id") long id, Model model) {

        if (noteService.exists(id)) {
            NoteEntity note = noteService.getById(id);
            model.addAttribute("note", note);
            LOGGER.info("post search method");
            return "noteSearch";
        }else {
            long id1 = id;
            model.addAttribute("id1", id1);
            LOGGER.info("post search method/error");
            return "error";
        }
    }

    @GetMapping("/update")
    public String updateId(@RequestParam long id, Model model) {
        LOGGER.info("get update method");
            NoteEntity searchNote = noteService.getById(id);
            model.addAttribute("note", searchNote);
            return "noteUpdate";
    }

    @PostMapping("/update")
    public String updateNote(@ModelAttribute NoteEntity noteEntity) {
        LOGGER.info("post update method");
        noteService.update(noteEntity);
        return "redirect:/note/list";
    }

    @PostMapping("/delete")
    public String deleteNote(@RequestParam long id) {
        LOGGER.info("post delete method");
                    noteService.deleteById(id);
            return "redirect:/note/list";
    }
}
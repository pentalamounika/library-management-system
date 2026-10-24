
package com.example.library.controller;

import com.example.library.model.LibraryRecord;
import com.example.library.repository.LibraryRecordRepository;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class LibraryController {

    private final LibraryRecordRepository repo;

    private final List<String> students = List.of(
        "Aarav Sharma",
        "Ananya Reddy",
        "Rahul Kumar",
        "Priya Singh",
        "Mounika"
    );

    private final List<String> books = List.of(
        "Java Programming",
        "Python Basics",
        "Database Systems",
        "Spring Boot Guide",
        "Web Development"
    );

    public LibraryController(LibraryRecordRepository repo) {
        this.repo = repo;
    }

    @GetMapping({"/", "/library"})
    public String show(
            @RequestParam(required = false) String search,
            Model model) {

        model.addAttribute("record", new LibraryRecord());
        model.addAttribute("records", repo.findAll());
        model.addAttribute("students", students);
        model.addAttribute("books", books);

        return "library";
    }

    @PostMapping("/library/save")
    public String save(
            @Valid @ModelAttribute("record") LibraryRecord r,
            BindingResult result,
            Model model,
            RedirectAttributes ra) {

        if (r.getStartDate() != null
                && r.getEndDate() != null
                && r.getEndDate().isBefore(r.getStartDate())) {

            result.rejectValue(
                "endDate",
                "invalid",
                "End date must be on or after start date"
            );
        }

        if (result.hasErrors()) {
            model.addAttribute("records", repo.findAll());
            model.addAttribute("students", students);
            model.addAttribute("books", books);

            return "library";
        }

        repo.save(r);

        ra.addFlashAttribute(
            "successMessage",
            "Record saved successfully."
        );

        return "redirect:/library";
    }

    @GetMapping("/library/edit/{id}")
    public String edit(
            @PathVariable Long id,
            Model m) {

        m.addAttribute(
            "record",
            repo.findById(id).orElseThrow()
        );

        m.addAttribute("records", repo.findAll());
        m.addAttribute("students", students);
        m.addAttribute("books", books);

        return "library";
    }

    @PostMapping("/library/delete/{id}")
    public String delete(
            @PathVariable Long id,
            RedirectAttributes ra) {

        repo.deleteById(id);

        ra.addFlashAttribute(
            "successMessage",
            "Record deleted successfully."
        );

        return "redirect:/library";
    }
}

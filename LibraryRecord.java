
package com.example.library.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "library_records")
public class LibraryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Select a student")
    private String studentName;

    @NotBlank(message = "Select a book")
    private String bookName;

    @NotNull(message = "Select start date")
    private LocalDate startDate;

    @NotNull(message = "Select end date")
    private LocalDate endDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String v) {
        studentName = v;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String v) {
        bookName = v;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate v) {
        startDate = v;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate v) {
        endDate = v;
    }
}

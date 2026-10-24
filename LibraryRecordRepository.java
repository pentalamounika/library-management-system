package com.example.library.repository;
import com.example.library.model.LibraryRecord; 
import org.springframework.data.jpa.repository.JpaRepository;
public interface LibraryRecordRepository extends JpaRepository<LibraryRecord,Long> {}

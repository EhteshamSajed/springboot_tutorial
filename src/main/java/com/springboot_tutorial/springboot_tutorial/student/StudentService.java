package com.springboot_tutorial.springboot_tutorial.student;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.internal.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;

    // private final StudentRepository repository;

    // @Autowired
    // public StudentService (StudentRepository repository){
    // this.repository = repository;
    // }

    public List<Student> getStudents() {
        return repository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = repository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        repository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = repository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException(
                    "Student with id: " + studentId + " not found");
        }
        repository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = repository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("Student with id: " + studentId + " not found"));
        if (!StringHelper.isEmpty(name)){
            student.setName(name);
        }

        if (!StringHelper.isEmpty(email)){
            Optional<Student> studentOptional = repository.findStudentByEmail(email);
            if (studentOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }
    }
}

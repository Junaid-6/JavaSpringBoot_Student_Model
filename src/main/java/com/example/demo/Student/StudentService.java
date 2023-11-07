package com.example.demo.Student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {

        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return  studentRepository.findAll();
    }

    public  void addNewStudent(Student student){
        Optional<Student> studentOptional=  studentRepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("Email Taken Already");
        }
        studentRepository.save(student);
        System.out.println(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exits = studentRepository.existsById(studentId);
        if(!exits){
            throw new IllegalStateException("Student with id: "+studentId+" does not exit");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email){
        Student student  = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException(
                        "Student with Id: "+ studentId + " doesn't exit"
                ));
        if(name != null && name.length() > 0 && !Objects.equals(student.getName(), name) ){
            student.setName(name);
        }
        if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)){
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()){
                throw  new IllegalStateException("Email already Taken");
            }
            student.setEmail(email);
        }


    }
}

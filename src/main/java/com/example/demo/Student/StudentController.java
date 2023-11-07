package com.example.demo.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController()
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService)
    {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudent(){

        return studentService.getStudents();
    }
    @GetMapping(value = "/myPage")
    public String myPage(){
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <title>Add Student</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>Add Student</h1>\n" +
                "<form action=\"http://localhost:8080/students\" method=\"post\">\n" +
                "    <label for=\"id\">ID:</label>\n" +
                "    <input type=\"number\" id=\"id\" name=\"id\" required><br><br>\n" +
                "\n" +
                "    <label for=\"name\">Name:</label>\n" +
                "    <input type=\"text\" id=\"name\" name=\"name\" required><br><br>\n" +
                "\n" +
                "    <label for=\"email\">Email:</label>\n" +
                "    <input type=\"email\" id=\"email\" name=\"email\" required><br><br>\n" +
                "\n" +
                "    <label for=\"dob\">Date of Birth:</label>\n" +
                "    <input type=\"date\" id=\"dob\" name=\"dob\" required><br><br>\n" +
                "\n" +
                "    <label for=\"age\">Age:</label>\n" +
                "    <input type=\"number\" id=\"age\" name=\"age\" required><br><br>\n" +
                "\n" +
                "    <input type=\"submit\" value=\"Submit\">\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>\n";
    }

    @PostMapping("/add")
    public void registerNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
        System.out.println("Adding student");
    }
    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId){
            studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email){
        studentService.updateStudent(studentId, name, email);
    }
}

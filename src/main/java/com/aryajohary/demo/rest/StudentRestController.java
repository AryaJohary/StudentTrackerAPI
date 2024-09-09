package com.aryajohary.demo.rest;


import com.aryajohary.demo.entity.Student;
import com.aryajohary.demo.service.StudentService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/api")
public class StudentRestController {

    private StudentService studentService;

    @Autowired
    public StudentRestController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostConstruct
    public void createStudents(){
        Student arya = new Student("Arya","Johary","a@a.com");
        Student arya2 = new Student("Arya","Johary","a@a.com");
        studentService.save(arya);
        studentService.save(arya2);
    }


    @GetMapping("/students")
    public List<Student> getStudents(){
        return studentService.findAll();
    }

    @GetMapping("/students/{studentId}")
    public Student getStudentById(@PathVariable int studentId){
        return studentService.findById(studentId);
    }

    @PostMapping("/students")
    public Student createStudent(@RequestBody Student student){
        return studentService.save(student);
    }

    @PutMapping("/students")
    public Student updateStudent(@RequestBody Student student){
        return studentService.save(student);
    }

    @DeleteMapping("/students/{studentId}")
    public String deleteStudent(@PathVariable int studentId){
        studentService.delete(studentId);
        return "Successfully deleted";
    }
}

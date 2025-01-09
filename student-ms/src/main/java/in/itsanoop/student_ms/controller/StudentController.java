package in.itsanoop.student_ms.controller;

import in.itsanoop.student_ms.dto.StudentDto;
import in.itsanoop.student_ms.model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import in.itsanoop.student_ms.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        Student createdStudent = studentService.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }
    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents(){
        System.out.println("Method called");
        List<StudentDto> students = studentService.getAllStudents();
        return ResponseEntity.status(HttpStatus.OK).body(students);
    }
    @GetMapping("/studentById/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Integer id) throws Exception {
        System.out.println("Got request: " + id);
        StudentDto studentDto = studentService.getStudentById(id);
        return ResponseEntity.status(HttpStatus.OK).body(studentDto);
    }
}

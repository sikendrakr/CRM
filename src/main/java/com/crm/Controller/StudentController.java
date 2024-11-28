package com.crm.Controller;
import com.crm.entity.Student;
import com.crm.payload.StudentDto;
import com.crm.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    //http://localhost/api/v1/student/add
    @PostMapping("/add")
    public ResponseEntity<?> addStudent(@Valid @RequestBody StudentDto dto, BindingResult result) {
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        StudentDto studentDto = studentService.addStudent(dto);
        return new ResponseEntity<>(studentDto, HttpStatus.CREATED);
    }
    //https://api.getpostman.com/api/v1/student?id=1
    @DeleteMapping
    public ResponseEntity<String> deleteStudent(@RequestParam Long id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<>("Student deleted successfully", HttpStatus.NO_CONTENT);
    }
    @PutMapping
    public ResponseEntity<StudentDto> updateStudent(@RequestParam Long id,@RequestBody StudentDto dto) {
        // TODO: Implement update logic
        StudentDto studentDto = studentService.updateStudent(id, dto);
        return new ResponseEntity<>(studentDto, HttpStatus.OK);
    }
    //http://localhost:8080/api/v1/student?pageNo=1&pageSize=3
    @GetMapping
    public ResponseEntity<List<StudentDto>> getStudents(
            @RequestParam (name = "pageSize", required = false, defaultValue ="5") int pageSize,
            @RequestParam (name = "pageNo", required = false, defaultValue ="1") int pageNo,
            @RequestParam (name = "sortBy", required = false, defaultValue ="5") String sortBy,
            @RequestParam (name = "sortDir", required = false, defaultValue ="5") String sortDir) {

        List<StudentDto> studentsDto = studentService.getStudent(pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(studentsDto, HttpStatus.OK);
    }
    //http://localhost:8080/api/v1/student/studentId/{stdId}
    @GetMapping("/studentId/{stdId}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable long stdId) {
        StudentDto dto = studentService.getStudentById(stdId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}

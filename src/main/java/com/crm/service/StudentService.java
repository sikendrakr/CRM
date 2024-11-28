package com.crm.service;

import com.crm.Exception.ResourceNotFound;
import com.crm.Repository.StudentRepository;
import com.crm.entity.Student;
import com.crm.payload.StudentDto;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    // Constructor injection for dependencies
    public StudentService(StudentRepository studentRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    // Create and save a new student
    public StudentDto addStudent(StudentDto dto) {
        Student student = mapToEntity(dto);
        Student savedStudent = studentRepository.save(student);
        StudentDto studentDto = mapToDto(savedStudent);
        studentDto.setDate(new Date()); // Add the current date
        return studentDto;
    }

    // Delete a student by ID
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    // Update a student
    public StudentDto updateStudent(Long id, StudentDto dto) {
        Student student = mapToEntity(dto);
        student.setId(id); // Set the ID of the student being updated
        Student updatedStudent = studentRepository.save(student);
        return mapToDto(updatedStudent);
    }

    // Get all students
    public List<StudentDto> getStudent(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable page = PageRequest.of(pageNo, pageSize, sort);
        Page<Student> all = studentRepository.findAll(page);
        List<Student> students = all.getContent();
        List<StudentDto> dto = students.stream().map(this::mapToDto).collect(Collectors.toList());
        return dto;
    }

    // Get a student by ID
    public StudentDto getStudentById(long stdId) {
        Student student = studentRepository.findById(stdId)
                .orElseThrow(() -> new ResourceNotFound("Student not found with id: " + stdId));
        return mapToDto(student);
    }

    // Helper method to map Student to StudentDto
    private StudentDto mapToDto(Student student) {
        return modelMapper.map(student, StudentDto.class);
    }

    // Helper method to map StudentDto to Student
    private Student mapToEntity(StudentDto dto) {
        return modelMapper.map(dto, Student.class);
    }
}

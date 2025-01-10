package in.itsanoop.student_ms.service;

import in.itsanoop.student_ms.dto.AddressDto;
import in.itsanoop.student_ms.dto.StudentDto;
import in.itsanoop.student_ms.exceptions.StudentNotFoundException;
import in.itsanoop.student_ms.feignclients.AddressFeignClient;
import in.itsanoop.student_ms.feignclients.GlobalFeignClient;
import in.itsanoop.student_ms.mapper.StudentMapper;
import in.itsanoop.student_ms.model.Student;
import org.springframework.stereotype.Service;
import in.itsanoop.student_ms.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private StudentRepository studentRepository;
    private AddressFeignClient addressFeignClient;
    private GlobalFeignClient globalFeignClient;

    public StudentService(StudentRepository studentRepository, GlobalFeignClient globalFeignClient) {
        this.studentRepository = studentRepository;
        this.globalFeignClient = globalFeignClient;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<StudentDto> getAllStudents() {
        List<StudentDto> studentDtos = new ArrayList<>();
        List<Student> students = studentRepository.findAll();
        for( Student s: students){
            StudentDto studentDto= new StudentDto();
            StudentMapper.toStudentDto(s,studentDto);
            studentDto.setAddressDto(globalFeignClient.getAddressById(s.getAddressId()));
            studentDtos.add(studentDto);
        }

        return studentDtos;
    }

    public StudentDto getStudentById(Integer id) throws Exception {
        Student s = studentRepository.findById(id).orElse(null);
        if( s == null){
            throw new StudentNotFoundException("Student not found");
        }
        StudentDto studentDto = new StudentDto();
        StudentMapper.toStudentDto(s, studentDto);
        studentDto.setAddressDto(globalFeignClient.getAddressById(s.getAddressId()));
        return studentDto;
    }
}

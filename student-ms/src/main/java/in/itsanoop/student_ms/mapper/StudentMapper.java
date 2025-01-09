package in.itsanoop.student_ms.mapper;

import in.itsanoop.student_ms.dto.StudentDto;
import in.itsanoop.student_ms.model.Student;

public class StudentMapper {
    public static void toStudentDto(Student student, StudentDto studentDto){
        studentDto.setId(student.getId());
        studentDto.setFirstName(student.getFirstName());
        studentDto.setLastName(student.getLastName());
        studentDto.setEmail(student.getEmail());
    }
}

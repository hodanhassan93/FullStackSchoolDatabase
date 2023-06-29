package com.wileyedge.fullstackschool.service;

import com.wileyedge.fullstackschool.dao.StudentDao;
import com.wileyedge.fullstackschool.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentServiceInterface {

    //YOUR CODE STARTS HERE
    @Autowired
    StudentDao studentDao;

    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Autowired
    CourseServiceImpl courseService;



    //YOUR CODE ENDS HERE

    public List<Student> getAllStudents() {
        //YOUR CODE STARTS HERE

        try {
            return studentDao.getAllStudents();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

        //YOUR CODE ENDS HERE
    }

    public Student getStudentById(int id) {
        //YOUR CODE STARTS HERE

        try {
            return studentDao.findStudentById(id);
        } catch (EmptyResultDataAccessException ex) {
            Student student = new Student();
            student.setStudentFirstName("Student Not Found");
            student.setStudentLastName("Student Not Found");
            return student;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

        //YOUR CODE ENDS HERE
    }

    public Student addNewStudent(Student student) {
        //YOUR CODE STARTS HERE

        if (student.getStudentFirstName().isBlank()) {
            student.setStudentFirstName("First Name blank, student NOT added");
            student.setStudentLastName("Last Name blank, student NOT added");
            return student;
        }

        if (student.getStudentLastName().isBlank()) {
            student.setStudentFirstName("Student first name provided");
            student.setStudentLastName("Last Name blank, student NOT added");
            return student;
        }

        try {
            return studentDao.createNewStudent(student);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

        //YOUR CODE ENDS HERE
    }

    public Student updateStudentData(int id, Student student) {
        //YOUR CODE STARTS HERE

        if (id != student.getStudentId()) {
            student.setStudentFirstName("IDs do not match, student not updated");
            student.setStudentLastName("IDs do not match, student not updated");
            return student;
        }

        try {
            studentDao.updateStudent(student);
            return student;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

        //YOUR CODE ENDS HERE
    }

    public void deleteStudentById(int id) {
        //YOUR CODE STARTS HERE
        try {
            studentDao.deleteStudent(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //YOUR CODE ENDS HERE
    }

    public void deleteStudentFromCourse(int studentId, int courseId) {
        //YOUR CODE STARTS HERE
        try {
            Student student = getStudentById(studentId);
            String studentFirstName = student.getStudentFirstName();
            String courseName = courseService.getCourseById(courseId).getCourseName();

            if (studentFirstName.equals("Student Not Found")) {
                System.out.println("Student not found");
            } else if (courseName.equals("Course Not Found")) {
                System.out.println("Course not found");
            } else {
                studentDao.deleteStudentFromCourse(studentId, courseId);
                System.out.println("Student: " + studentId + " deleted from course: " + courseId);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        //YOUR CODE ENDS HERE
    }

    public void addStudentToCourse(int studentId, int courseId) {
        //YOUR CODE STARTS HERE
        try {
            Student student = getStudentById(studentId);
            String studentFirstName = student.getStudentFirstName();
            String courseName = courseService.getCourseById(courseId).getCourseName();

            if (studentFirstName.equals("Student Not Found")) {
                System.out.println("Student not found");
            } else if (courseName.equals("Course Not Found")) {
                System.out.println("Course not found");
            } else {
                try {
                    studentDao.addStudentToCourse(studentId, courseId);
                    System.out.println("Student: " + studentId + " added to course: " + courseId);
                } catch (Exception ex) {
                    System.out.println("Student: " + studentId + " already enrolled in course: " + courseId);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        //YOUR CODE ENDS HERE
    }
}

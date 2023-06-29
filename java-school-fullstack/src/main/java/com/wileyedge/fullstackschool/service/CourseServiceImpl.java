package com.wileyedge.fullstackschool.service;

import com.wileyedge.fullstackschool.dao.CourseDao;
import com.wileyedge.fullstackschool.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseServiceInterface {

    //YOUR CODE STARTS HERE
    @Autowired
    CourseDao courseDao;

    public CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }


    //YOUR CODE ENDS HERE

    public List<Course> getAllCourses() {
        //YOUR CODE STARTS HERE

        try {
            return courseDao.getAllCourses();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

        //YOUR CODE ENDS HERE
    }

    public Course getCourseById(int id) {
        //YOUR CODE STARTS HERE
        try {
            return courseDao.findCourseById(id);
        } catch (EmptyResultDataAccessException ex) {
            Course course = new Course();
            course.setCourseName("Course Not Found");
            course.setCourseDesc("Course Not Found");
            return course;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

        //YOUR CODE ENDS HERE
    }

    public Course addNewCourse(Course course) {
        //YOUR CODE STARTS HERE

        if (course.getCourseName().isBlank()) {
            course.setCourseName("Name blank, course NOT added");
            course.setCourseDesc("Description blank, course NOT added");
            return course;
        }

        if (course.getCourseDesc().isBlank()) {
            course.setCourseName("Course name provided");
            course.setCourseDesc("Description blank, course NOT added");
            return course;
        }

        try {
            return courseDao.createNewCourse(course);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

        //YOUR CODE ENDS HERE
    }

    public Course updateCourseData(int id, Course course) {
        //YOUR CODE STARTS HERE

        if (id != course.getCourseId()) {
            course.setCourseName("IDs do not match, course not updated");
            course.setCourseDesc("IDs do not match, course not updated");
            return course;
        }

        try {
            courseDao.updateCourse(course);
            return course;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

        //YOUR CODE ENDS HERE
    }

    public void deleteCourseById(int id) {
        //YOUR CODE STARTS HERE
        try {
            courseDao.deleteCourse(id);
            System.out.println("Course ID: " + id + " deleted");
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        //YOUR CODE ENDS HERE
    }
}

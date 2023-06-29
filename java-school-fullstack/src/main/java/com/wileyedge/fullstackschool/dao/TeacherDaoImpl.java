package com.wileyedge.fullstackschool.dao;

import com.wileyedge.fullstackschool.dao.mappers.TeacherMapper;
import com.wileyedge.fullstackschool.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class TeacherDaoImpl implements TeacherDao {

    private final JdbcTemplate jdbcTemplate;

    public TeacherDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Teacher createNewTeacher(Teacher teacher) {
        //YOUR CODE STARTS HERE
        try {
            jdbcTemplate.update(
                    "INSERT INTO teacher (tid, tFName, tLName, dept) VALUES (?, ?, ?, ?)",
                    teacher.getTeacherId(), teacher.getTeacherFName(), teacher.getTeacherLName(), teacher.getDept()
            );
            return teacher;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

        //YOUR CODE ENDS HERE
    }

    @Override
    public List<Teacher> getAllTeachers() {
        //YOUR CODE STARTS HERE

        try {
            return jdbcTemplate.query("SELECT * FROM teacher", new TeacherMapper());
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

        //YOUR CODE ENDS HERE
    }

    @Override
    public Teacher findTeacherById(int id) {
        //YOUR CODE STARTS HERE

        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM teacher WHERE tid = ?",
                    new TeacherMapper(),
                    id
            );
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

        //YOUR CODE ENDS HERE
    }

    @Override
    public void updateTeacher(Teacher t) {
        //YOUR CODE STARTS HERE
        try {
            jdbcTemplate.update(
                    "UPDATE teacher SET tFName = ?, tLName = ?, dept = ? WHERE tid = ?",
                    t.getTeacherFName(), t.getTeacherLName(), t.getDept(), t.getTeacherId()
            );
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //YOUR CODE ENDS HERE
    }

    @Override
    public void deleteTeacher(int id) {
        //YOUR CODE STARTS HERE
        try {
            jdbcTemplate.update("DELETE FROM teacher WHERE tid = ?", id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //YOUR CODE ENDS HERE
    }
}

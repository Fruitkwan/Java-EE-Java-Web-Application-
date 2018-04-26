/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;
import transferobjects.Students;

/**
 *
 * @author Sidhant Soni
 */


public interface StudentsDAO {
    List<Students> getAllStudents();
    void addStudent(Students student);
    void deleteStudent(Students student);
    Students getStudentByNumber(String studentNumber);
}


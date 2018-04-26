/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import transferobjects.Students;

/**
 *
 * 
 *
 */
public class StudentsDAOImpl implements StudentsDAO{
 
    
     private static final String GET_ALL_STUDENTS = "SELECT student_num, first_name, last_name, date_birth, enrolled FROM Students ORDER BY student_num";
    private static final String INSERT_STUDENTS = "INSERT INTO Students (student_num, first_name, last_name, date_birth, enrolled) VALUES(?, ?, ?, ?, ?)";
    private static final String DELETE_STUDENTS = "DELETE FROM Students WHERE student_num = ?";
    private static final String UPDATE_STUDENTS = "UPDATE Students SET first_name = ?, last_name = ?, date_birth = ?, enrolled = ? WHERE student_num = ?";
    private static final String GET_BY_NUMBER_STUDENTS = "SELECT student_num, first_name, last_name, date_birth, enrolled FROM Students WHERE student_num = ?";
    
    /**
     * get all students information
     * @return 
     */
    @Override
    public List<Students> getAllStudents(){
        List<Students> students = Collections.EMPTY_LIST;
        Students student;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            DataSource ds = new DataSource();
            con = ds.createConnection();
            pstmt = con.prepareStatement( GET_ALL_STUDENTS);
            rs = pstmt.executeQuery();
            students = new ArrayList<>(100);
            while( rs.next()){
                student = new Students();
                student.setStudentNum(rs.getString("student_num"));
                student.setFirstName(rs.getString("first_name"));
                student.setLastName(rs.getString("last_name"));
                student.setDateBirth(rs.getString("date_birth"));
                student.setEnrolled(rs.getString("enrolled"));
                students.add(student);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentsDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try{ if(rs != null){ rs.close(); } }
            catch(SQLException ex){System.out.println(ex.getMessage());}
            try{ if(pstmt != null){ pstmt.close(); }}
            catch(SQLException ex){System.out.println(ex.getMessage());}
            try{ if(con != null){ con.close(); }}
            catch(SQLException ex){System.out.println(ex.getMessage());}
	}
        return students;
    }
    /**
     * add a student information to database
     * @param student 
     */
    @Override
    public void addStudent(Students student){
        try( Connection con = new DataSource().createConnection();
            PreparedStatement pstmt = con.prepareStatement( INSERT_STUDENTS);){
            pstmt.setString(1, student.getStudentNum());
            pstmt.setString(2, student.getFirstName());
            pstmt.setString(3, student.getLastName());
            pstmt.setString(4, student.getDateBirth());
            pstmt.setString(5, student.getEnrolled());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentsDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * get a student information by student number
     * @param studentNumber
     * @return 
     */
    @Override
    public Students getStudentByNumber(String studentNumber){
       Connection con = null;
       PreparedStatement pstmt = null;
       ResultSet rs = null;
       Students student = null;
	try{
            DataSource ds = new DataSource();
            con = ds.createConnection();
            pstmt = con.prepareStatement(GET_BY_NUMBER_STUDENTS);
            pstmt.setString(1, studentNumber);
            rs = pstmt.executeQuery();
            
                student = new Students();
                student.setStudentNum(rs.getString("student_num"));
                student.setFirstName(rs.getString("first_name"));
                student.setLastName(rs.getString("last_name"));
                student.setDateBirth(rs.getString("date_birth"));
                student.setEnrolled(rs.getString("enrolled"));
            
	}
	catch(SQLException ex){
            Logger.getLogger(StudentsDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
	}
	finally{
            try{ if(rs != null){ rs.close(); } }
            catch(SQLException ex){System.out.println(ex.getMessage());}
            try{ if(pstmt != null){ pstmt.close(); }}
            catch(SQLException ex){System.out.println(ex.getMessage());}
            try{ if(con != null){ con.close(); }}
            catch(SQLException ex){System.out.println(ex.getMessage());}
	}
	return student;
    }

    @Override
    public void deleteStudent(Students student) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
}

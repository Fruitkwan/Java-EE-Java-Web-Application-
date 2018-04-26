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
import transferobjects.ClassRep;

/**
 *
 * @author Sidhant Soni
 */
public class ClassRepDAOImpl implements ClassRepDAO{

    private static final String GET_ALL_REGISTRY = "SELECT student_num, course_num, term, year FROM Classrep ORDER BY student_num";
    private static final String INSERT_REGISTRY = "INSERT INTO Classrep (student_num, course_num, term, year) VALUES(?, ?, ?, ?)";
    private static final String DELETE_REGISTRY = "DELETE FROM Classrep WHERE student_num = ?";
    private static final String UPDATE_REGISTRY = "UPDATE Classrep SET course_num = ?, term = ?, year = ? WHERE student_num = ?";
    private static final String GET_BY_NUMBER_REGISTRY = "SELECT student_num, course_num, term, year FROM Classrep WHERE student_num = ?";
    private List<ClassRep> classreps;
    
    @Override
    public List<ClassRep> getAllClassRep(){
        classreps = Collections.EMPTY_LIST;
        ClassRep classrep;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            DataSource ds = new DataSource();
            con = ds.createConnection();
            pstmt = con.prepareStatement( GET_ALL_REGISTRY);
            rs = pstmt.executeQuery();
            classreps = new ArrayList<>(100);
            while( rs.next()){
                classrep = new ClassRep();
                classrep.setStudentNum(rs.getString("student_num"));
                classrep.setCourseNumber(rs.getString("course_num"));
                classrep.setTerm(rs.getString("term"));
                classrep.setYear(rs.getString("year"));
                classreps.add(classrep);
            }
        } catch (SQLException ex) {
            Logger.getLogger( RegistryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try{ if(rs != null){ rs.close(); } }
            catch(SQLException ex){System.out.println(ex.getMessage());}
            try{ if(pstmt != null){ pstmt.close(); }}
            catch(SQLException ex){System.out.println(ex.getMessage());}
            try{ if(con != null){ con.close(); }}
            catch(SQLException ex){System.out.println(ex.getMessage());}
	}
        return classreps;
    }
    
    @Override
    public void addClassRep(ClassRep classrep){
        try( Connection con = new DataSource().createConnection();
                PreparedStatement pstmt = con.prepareStatement( INSERT_REGISTRY);){
            pstmt.setString(1, classrep.getStudentNum());
            pstmt.setString(2, classrep.getCourseNumber());
            pstmt.setString(3, classrep.getTerm());
            pstmt.setString(4, classrep.getYear());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RegistryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void deleteClassRep(ClassRep classrep){
        try( Connection con = new DataSource().createConnection();
                PreparedStatement pstmt = con.prepareStatement( DELETE_REGISTRY);){
            pstmt.setString(1, classrep.getStudentNum());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RegistryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    @Override
    public List<ClassRep> getClassRepByNumber(String studentNumber){
       classreps = Collections.EMPTY_LIST;
       Connection con = null;
       PreparedStatement pstmt = null;
       ResultSet rs = null;
       ClassRep classrep;
	try{
            DataSource ds = new DataSource();
            con = ds.createConnection();
            pstmt = con.prepareStatement(GET_BY_NUMBER_REGISTRY);
            pstmt.setString(1, studentNumber);
            rs = pstmt.executeQuery();
            classreps = new ArrayList<>(100);
            while( rs.next()){
                classrep = new ClassRep();
                classrep.setStudentNum(rs.getString("student_num"));
                classrep.setCourseNumber(rs.getString("course_num"));
                classrep.setTerm(rs.getString("term"));
                classrep.setYear(rs.getString("year"));
                classreps.add(classrep);
            }
	}
	catch(SQLException ex){
            Logger.getLogger(RegistryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
	}
	finally{
            try{ if(rs != null){ rs.close(); } }
            catch(SQLException ex){System.out.println(ex.getMessage());}
            try{ if(pstmt != null){ pstmt.close(); }}
            catch(SQLException ex){System.out.println(ex.getMessage());}
            try{ if(con != null){ con.close(); }}
            catch(SQLException ex){System.out.println(ex.getMessage());}
	}
	return classreps;
    }
}

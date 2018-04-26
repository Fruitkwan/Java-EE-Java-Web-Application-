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
import transferobjects.Tuition;




public class TuitionDAOImpl implements TuitionDAO{

    private static final String GET_ALL_TUITION = "SELECT student_num, paid, remainder FROM Tuition ORDER BY student_num";
    private static final String INSERT_TUITION = "INSERT INTO Tuition (student_num, paid, remainder) VALUES(?, ?, ?)";
    private static final String DELETE_TUITION = "DELETE FROM Tuition WHERE student_num = ?";
    private static final String UPDATE_TUITION = "UPDATE Tuition SET paid = ?, remainder = ? WHERE student_num = ?";
    private static final String GET_BY_NUMBER_TUITION = "SELECT student_num, paid, remainder FROM Tuition WHERE student_num = ?";
    
    
    @Override
    public List<Tuition> getAllTuition(){
        List<Tuition> tuitions = Collections.EMPTY_LIST;
        Tuition tuition;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            DataSource ds = new DataSource();
            con = ds.createConnection();
            pstmt = con.prepareStatement( GET_ALL_TUITION);
            rs = pstmt.executeQuery();
            tuitions = new ArrayList<>(100);
            while( rs.next()){
                tuition = new Tuition();
                tuition.setStudentNum(rs.getString("student_num"));
                tuition.setPaid(rs.getString("paid"));
                tuition.setRemainder(rs.getString("remainder"));
                tuitions.add(tuition);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TuitionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try{ if(rs != null){ rs.close(); } }
            catch(SQLException ex){System.out.println(ex.getMessage());}
            try{ if(pstmt != null){ pstmt.close(); }}
            catch(SQLException ex){System.out.println(ex.getMessage());}
            try{ if(con != null){ con.close(); }}
            catch(SQLException ex){System.out.println(ex.getMessage());}
	}
        return tuitions;
    }
    
    
    @Override
    public void addTuition(Tuition tuition){
        try( Connection con = new DataSource().createConnection();
                PreparedStatement pstmt = con.prepareStatement( INSERT_TUITION);){
            pstmt.setString(1, tuition.getStudentNum());
            pstmt.setString(2,tuition.getPaid());
            pstmt.setString(3, tuition.getRemainder());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TuitionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Override
    public void deleteTuition(Tuition tuition){
        try( Connection con = new DataSource().createConnection();
                PreparedStatement pstmt = con.prepareStatement( DELETE_TUITION);){
            pstmt.setString(1, tuition.getStudentNum());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TuitionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    
    @Override
    public Tuition getTuitionByNumber(String studentNumber){
       Connection con = null;
       PreparedStatement pstmt = null;
       ResultSet rs = null;
       Tuition tuition = null;
	try{
            DataSource ds = new DataSource();
            con = ds.createConnection();
            pstmt = con.prepareStatement(GET_BY_NUMBER_TUITION);
            pstmt.setString(1, studentNumber);
            rs = pstmt.executeQuery();
            while(rs.next()){
                tuition = new Tuition();
                tuition.setStudentNum(rs.getString("student_num"));
                tuition.setPaid(rs.getString("paid"));
                tuition.setRemainder(rs.getString("remainder"));
            }
	}
	catch(SQLException ex){
            Logger.getLogger(TuitionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
	}
	finally{
            try{ if(rs != null){ rs.close(); } }
            catch(SQLException ex){System.out.println(ex.getMessage());}
            try{ if(pstmt != null){ pstmt.close(); }}
            catch(SQLException ex){System.out.println(ex.getMessage());}
            try{ if(con != null){ con.close(); }}
            catch(SQLException ex){System.out.println(ex.getMessage());}
	}
	return tuition;
    }
    
}

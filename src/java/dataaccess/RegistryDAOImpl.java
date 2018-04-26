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
import transferobjects.Registry;

/**
 *
 * @author Sidhant Soni
 */


public class RegistryDAOImpl implements RegistryDAO{

    private static final String GET_ALL_REGISTRY = "SELECT student_num, course_num, term, year FROM Registry ORDER BY student_num";
    private static final String INSERT_REGISTRY = "INSERT INTO Registry (student_num, course_num, term, year) VALUES(?, ?, ?, ?)";
    private static final String DELETE_REGISTRY = "DELETE FROM Registry WHERE student_num = ?";
    private static final String UPDATE_REGISTRY = "UPDATE Registry SET course_num = ?, term = ?, year = ? WHERE student_num = ?";
    private static final String GET_BY_NUMBER_REGISTRY = "SELECT student_num, course_num, term, year FROM Registry WHERE student_num = ?";
    private List<Registry> registries;
    
    @Override
    public List<Registry> getAllRegistry(){
        registries = Collections.EMPTY_LIST;
        Registry registry;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            DataSource ds = new DataSource();
            con = ds.createConnection();
            pstmt = con.prepareStatement( GET_ALL_REGISTRY);
            rs = pstmt.executeQuery();
            registries = new ArrayList<>(100);
            while( rs.next()){
                registry = new Registry();
                registry.setStudentNum(rs.getString("student_num"));
                registry.setCourseNumber(rs.getString("course_num"));
                registry.setTerm(rs.getString("term"));
                registry.setYear(rs.getString("year"));
                registries.add(registry);
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
        return registries;
    }
    
    @Override
    public void addRegistry(Registry registry){
        try( Connection con = new DataSource().createConnection();
                PreparedStatement pstmt = con.prepareStatement( INSERT_REGISTRY);){
            pstmt.setString(1, registry.getStudentNum());
            pstmt.setString(2, registry.getCourseNumber());
            pstmt.setString(3, registry.getTerm());
            pstmt.setString(4, registry.getYear());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RegistryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void deleteRegistry(Registry registry){
        try( Connection con = new DataSource().createConnection();
                PreparedStatement pstmt = con.prepareStatement( DELETE_REGISTRY);){
            pstmt.setString(1, registry.getStudentNum());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RegistryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    @Override
    public List<Registry> getRegistryByNumber(String studentNumber){
       registries = Collections.EMPTY_LIST;
       Connection con = null;
       PreparedStatement pstmt = null;
       ResultSet rs = null;
       Registry registry = null;
	try{
            DataSource ds = new DataSource();
            con = ds.createConnection();
            pstmt = con.prepareStatement(GET_BY_NUMBER_REGISTRY);
            pstmt.setString(1, studentNumber);
            rs = pstmt.executeQuery();
            registries = new ArrayList<>(100);
            while( rs.next()){
                registry = new Registry();
                registry.setStudentNum(rs.getString("student_num"));
                registry.setCourseNumber(rs.getString("course_num"));
                registry.setTerm(rs.getString("term"));
                registry.setYear(rs.getString("year"));
                registries.add(registry);
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
	return registries;
    }
    
}

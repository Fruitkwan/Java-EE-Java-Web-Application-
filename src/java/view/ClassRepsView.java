/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.servlet.http.HttpServlet;
import business.ClassRepLogic;
import business.ValidationException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import transferobjects.ClassRep;

/**
 *
 * @author Sidhant Soni
 */


public class ClassRepsView extends HttpServlet {
    
     /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>CLASS REP</title>");            
            out.println("</head>");
            out.println("<body style=\"background-color:red;\">");
            ClassRepLogic logic = new ClassRepLogic();

            if(request.getParameter("Add Classrep Button") != null){
                String studentNumber = request.getParameter("Add Student Number");
                String courseNumber = request.getParameter("Add Course Number");
                String term = request.getParameter("Add Term");
                String year = request.getParameter("Add Year");
                ClassRep newClassRep = new ClassRep(studentNumber,courseNumber, term, year);
                try {
                    //add an element into Registry table
                    logic.addClassRep(newClassRep);
                } catch (ValidationException ex) {
                    Logger.getLogger(RegistryView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(request.getParameter("Delete Classrep Button") != null){
                String studentNumber = request.getParameter("Delete Classrep");
                ClassRep deleteClassRep = new ClassRep(studentNumber,null, null, null);
                try {
                    //delete the element from Registry table
                    logic.deleteClassRep(deleteClassRep);
                } catch (ValidationException ex) {
                    Logger.getLogger(RegistryView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //load the Registry table into a list, get ready for delete or modify if required in future
            List<ClassRep> classReps = logic.getAllClassRep();
            
        
            
            out.println("<h1>Classrep View " + request.getContextPath() + "</h1>");
            out.println("<table border=\"2\">");
            out.println("<tr>");
            out.println("<td>Student Number</td>");
            out.println("<td>Course Number</td>");
            out.println("<td>Term</td>");
            out.println("<td>Year</td>");
            out.println("</tr>");
            for(ClassRep classrep : classReps){
                out.printf("<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>", classrep.getStudentNum(), classrep.getCourseNumber(), classrep.getTerm(), classrep.getYear());
            }
            out.println("</table>");
            
           
            
            out.println("<form action=\"Classrep\" method=\"post\">");
            out.println("<h3>Add a class Representative</h3>");
            out.println("<table border=\"1\">");
            out.println("<tr>");
            out.println("<td>Student Number</td>");
            out.println("<td>Course Number</td>");
            out.println("<td>Term</td>");
            out.println("<td>Year</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td><input type=\"number\" name=\"Add Student Number\"></td>");
            out.println("<td><input type=\"text\" name=\"Add Course Number\"></td>");
            out.println("<td><select name=\"Add Term\" id=\"Add Term\">");
            out.println("<option value=\"W\">W</option>");       
            out.println("<option value=\"F\">F</option>");  
            out.println("<option value=\"S\">S</option>");  							
            out.println("</select>");                                                    
            out.println("</td>");
            out.println("<td><input type=\"number\" name=\"Add Year\"></td>");
            out.println("</tr>");
            out.println("</table>");
            out.println("<input type=\"submit\" name=\"Add Classrep Button\" value=\"Add\">");
            out.println("</form>");
             
            
            out.println("</body>");
            out.println("</html>");
        }
    }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
}

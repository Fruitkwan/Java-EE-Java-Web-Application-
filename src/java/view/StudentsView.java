/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import business.StudentsLogic;
import business.ValidationException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import transferobjects.Students;

/**
 *
 * @author Nsang Joanne
 */
public class StudentsView extends HttpServlet {
    
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
            out.println("<title>STUDENTS</title>");            
            out.println("</head>");
            out.println("<body style=\"background-color:red;\">");
            StudentsLogic logic = new StudentsLogic();

            if(request.getParameter("Add Student Button") != null){
                String studentNumber = request.getParameter("Add Student Number");
                String firstName = request.getParameter("Add First Name");
                String lastName = request.getParameter("Add Last Name");
                String dateBirth = request.getParameter("Add Date Birth");
                String enrolled = request.getParameter("Add Enrolled");
                Students newStudent = new Students(studentNumber,firstName, lastName, dateBirth, enrolled);
                try {
                    //add an element into Registry table
                    logic.addStudent(newStudent);
                } catch (ValidationException ex) {
                    Logger.getLogger(StudentsView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
     
            List<Students> students = logic.getAllStudents();
           
            out.println("<h1>Student View at " + request.getContextPath() + "</h1>");
            out.println("<table border=\"1\">");
            out.println("<tr>");
            out.println("<td>Student Number</td>");
            out.println("<td>First Name</td>");
            out.println("<td>Last Name</td>");
            out.println("<td>Date of Birth</td>");
            out.println("<td>Date Enrolled</td>");
            out.println("</tr>");
            for(Students student : students){
                out.printf("<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>", student.getStudentNum(), student.getFirstName(), student.getLastName(), student.getDateBirth(), student.getEnrolled());
            }
            out.println("</table>");
            
            
            
            out.println("<form action=\"Students\" method=\"post\">");
            out.println("<h3>ADD A STUDENT</h3>");
            out.println("<table border=\"1\">");
            out.println("<tr>");
            out.println("<td>Student Number</td>");
            out.println("<td>First Name</td>");
            out.println("<td>Last Name</td>");
            out.println("<td>Date of Birth</td>");
            out.println("<td>Date Enrolled</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td><input type=\"number\" name=\"Add Student Number\"></td>");
            out.println("<td><input type=\"text\" name=\"Add First Name\"></td>");
            out.println("<td><input type=\"text\" name=\"Add Last Name\"></td>");
            out.println("<td><input type=\"date\" name=\"Add Date Birth\"></td>");
            out.println("<td><input type=\"date\" name=\"Add Enrolled\"></td>");
            out.println("</tr>");
            out.println("</table>");
            out.println("<input type=\"submit\" name=\"Add Student Button\" value=\"Add\">");
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

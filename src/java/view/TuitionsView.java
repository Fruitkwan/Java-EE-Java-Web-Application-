/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.servlet.http.HttpServlet;
import business.TuitionLogic;
import business.ValidationException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import transferobjects.Tuition;




public class TuitionsView extends HttpServlet {
    
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
            out.println("<title>TUITION</title>");            
            out.println("</head>");
            out.println("<body style=\"background-color:red;\">");
            TuitionLogic logic = new TuitionLogic();

            if(request.getParameter("Add Tuition Button") != null){
                String studentNumber = request.getParameter("Add Student Number");
                String paid = request.getParameter("Add Paid");
                String remainder = request.getParameter("Add Remainder");
                Tuition newTuition = new Tuition(studentNumber, paid, remainder);
                try {
                    //add an element into Registry table
                    logic.addTuition(newTuition);
                } catch (ValidationException ex) {
                    Logger.getLogger(TuitionsView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(request.getParameter("Delete Tuition Button") != null){
                String studentNumber = request.getParameter("Delete Tuition");
                Tuition deleteTuition = new Tuition(studentNumber,null,null);
                try {
                    //delete the element from Registry table
                    logic.deleteTuition(deleteTuition);
                } catch (ValidationException ex) {
                    Logger.getLogger(TuitionsView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            //load the Registry table into a list, get ready for delete or modify if required in future
            List<Tuition> tuitions = logic.getAllTuition();
            
            if(request.getParameter("Find Tuition Button") != null){
                String studentNumber = request.getParameter("Find Tuition");
                for(int i = 0; i < tuitions.size(); i++){
                    if(studentNumber.equals(tuitions.get(i).getStudentNum())){
                        tuitions.clear();//remove all elements inside the list
                        try {       
                            tuitions.add(logic.getTuitionByNumber(studentNumber));
                            System.out.println("done");
                        } catch (ValidationException ex) {
                            Logger.getLogger(TuitionsView.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
            
            out.println("<h1>Tuition View " + request.getContextPath() + "</h1>");
            out.println("<table border=\"1\">");
            out.println("<tr>");
            out.println("<td>Student Number</td>");
            out.println("<td>Paid</td>");
            out.println("<td>Remainder</td>");
            out.println("</tr>");
            for(Tuition tuition : tuitions){
                out.printf("<tr><td>%s</td><td>%s</td><td>%s</td></tr>", tuition.getStudentNum(), tuition.getPaid(), tuition.getRemainder());
            }
            out.println("</table>");
            
          
            
            out.println("<form action=\"Tuition\" method=\"post\">");
            out.println("<h3>ADD TUITION</h3>");
            out.println("<table border=\"1\">");
            out.println("<tr>");
            out.println("<td>Student Number</td>");
            out.println("<td>Paid</td>");
            out.println("<td>Remainder</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td><input type=\"number\" name=\"Add Student Number\"></td>");
            out.println("<td><input type=\"number\" name=\"Add Paid\"></td>");
            out.println("<td><input type=\"number\" name=\"Add Remainder\"></td>");
            out.println("</tr>");
            out.println("</table>");
            out.println("<input type=\"submit\" name=\"Add Tuition Button\" value=\"Add\">");
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

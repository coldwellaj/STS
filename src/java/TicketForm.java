/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author coldwellaj
 */
public class TicketForm extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Ticket newTicket = new Ticket();
        
        newTicket.setPriority(Integer.valueOf(request.getParameter("priority")));
        newTicket.setStartDate(request.getParameter("start_date"));
        newTicket.setEndDate(request.getParameter("end_date"));
        newTicket.setQueue(request.getParameter("department"));
        newTicket.setDetails(request.getParameter("details"));
        newTicket.setClientId(request.getParameter("client_id"));
        newTicket.setResource(request.getParameter("resource"));
        newTicket.setTicketTitle(request.getParameter("title"));
        
        try{
            try (PrintWriter out = response.getWriter()) {
                if (DataBase.addNewTicket(newTicket)) {
                    JSONObject obj = new JSONObject();
                    obj.put("Submission", true);
                    out.println(request.getParameter("callback")+"([");
                    out.print(obj + "])");
                }
                else {
                    JSONObject obj = new JSONObject();
                    obj.put("Submission", false);
                    out.println(request.getParameter("callback")+"([");
                    out.print(obj + "])");
                }
            }
        }
        catch(Exception ex){
            try (PrintWriter out = response.getWriter()) {
                out.println("<h1>An Error Occured "+ex.getMessage()+" </h1>");
            }  
        }
       
    }

    
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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

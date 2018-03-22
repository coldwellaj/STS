/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author coldwellaj
 */
public class TicketList extends HttpServlet {

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
        String user = request.getParameter("user");
        ArrayList<Ticket> tickets = new ArrayList<>();
        
        
        try{
            try (PrintWriter out = response.getWriter()) {
                
                
                
                tickets = DataBase.getTickets(user);
                out.print(request.getParameter("callback"));
                out.print("([");
                for(int x = 0; x < tickets.size(); x++){
                    JSONObject obj = new JSONObject();

                    obj.put("TicketID", tickets.get(x).getId());
                    obj.put("Priority", tickets.get(x).getPriority());
                    obj.put("Title", tickets.get(x).getTicketTitle());
                    obj.put("StartDate", tickets.get(x).getStartDate());
                    obj.put("EndDate", tickets.get(x).getEndDate());
                    obj.put("Detail", tickets.get(x).getDetails());
                    obj.put("Department", tickets.get(x).getQueue());
                    obj.put("ClientId", tickets.get(x).getClientId());
                    obj.put("Status", tickets.get(x).getStatus());
                    obj.put("Resource", tickets.get(x).getResource());
                    out.print(obj);
                    if (x+1 != tickets.size()){
                        out.print(",");
                    }
                }
                out.print("])");
            }
        }
        catch(Exception ex){
            try (PrintWriter out = response.getWriter()) {
                out.println("<h1>An Error Occured "+ex.getMessage()+" </h1>");
            }  
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

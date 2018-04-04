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
 * @author Coldwellaj
 */
public class ClientList extends HttpServlet {

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
        ArrayList<Client> clients = new ArrayList<>();
        
        
        try{
            try (PrintWriter out = response.getWriter()) {
                
                
                
                clients = DataBase.getClients();
                out.print(request.getParameter("callback"));
                out.print("([");
                for(int x = 0; x < clients.size(); x++){
                    JSONObject obj = new JSONObject();

                    obj.put("UserID", clients.get(x).getId());
                    obj.put("FirstName", clients.get(x).getFName());
                    obj.put("LastName", clients.get(x).getLName());
                    obj.put("Email", clients.get(x).getEmail());
                    obj.put("Phone", clients.get(x).getPhone());
                    out.print(obj);
                    if (x+1 != clients.size()){
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

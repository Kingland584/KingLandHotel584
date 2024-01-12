/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.RoomDashboardBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mohd azrin
 */
public class AddRoomServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddRoomServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddRoomServlet at " + request.getContextPath() + "</h1>");
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
        
        Connection connection = null;
        List<String> errorMsgs = new ArrayList<>();
        
        try{
            String roomID = request.getParameter("roomID");
            String roomType = request.getParameter("roomType");
            String roomStatus = request.getParameter("roomStatus");
            double roomPrice = Double.parseDouble(request.getParameter("roomPrice"));
            int roomPax = Integer.parseInt(request.getParameter("roomPax"));
            
            
            if (roomID == null || roomID.isEmpty()) {
                errorMsgs.add("Room ID is required");
            }
            
            if (roomType == null || roomType.isEmpty()) {
                errorMsgs.add("Room Type is required");
            }
            
            if (roomStatus == null || roomStatus.isEmpty()) {
                errorMsgs.add("Room Status is required");
            }
            
            if (roomPrice == 0.00) {
                errorMsgs.add("Room Price is required");
            }

            if (roomPax == 0) {
                errorMsgs.add("Room Pax is required");
            }

            if (!errorMsgs.isEmpty()) {
                request.setAttribute("errorMsgs", errorMsgs);
                request.getRequestDispatcher("/error.jsp").forward(request, response);
                return; 
            }
            
            RoomDashboardBean room = new RoomDashboardBean();
            room.setRoomID(roomID);
            room.setRoomType(roomType);
            room.setRoomStatus(roomStatus);
            room.setRoomPrice(roomPrice);
            room.setRoomPax(roomPax);
            
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/KingLandHotel", "app", "app");

            connection.setAutoCommit(true);

            String action = request.getParameter("action");
            if ("Add".equals(action)) {
                
                String insertQuery = "INSERT INTO ROOM (ROOMID, ROOMTYPE, ROOMSTATUS, ROOMPRICE,ROOMPAX) VALUES (?,?,?,?,?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    preparedStatement.setString(1, room.getRoomID());
                    preparedStatement.setString(2, room.getRoomType());
                    preparedStatement.setString(3, room.getRoomStatus());
                    preparedStatement.setDouble(4, room.getRoomPrice());
                    preparedStatement.setInt(5, room.getRoomPax());

                    preparedStatement.executeUpdate();

                    connection.setAutoCommit(true);
                
                    errorMsgs.clear();
                    
                    request.getRequestDispatcher("/success.jsp").forward(request, response);
                }
            }
        }catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception: " + e.getMessage());

            /*
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            */
            
            errorMsgs.add("An error occurred. Please try again later.");
            request.setAttribute("errorMsgs", errorMsgs);
            request.getRequestDispatcher("/error.jsp").forward(request, response);

        } finally {
            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                    connection.close();
                }
            } catch (SQLException ex) {
                // Log or handle the exception if needed
                ex.printStackTrace();
            }
        }
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

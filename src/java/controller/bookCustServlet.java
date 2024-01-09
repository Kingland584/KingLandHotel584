/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.bookCust;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author User
 */
public class bookCustServlet extends HttpServlet {

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
            out.println("<title>Servlet bookCustServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet bookCustServlet at " + request.getContextPath() + "</h1>");
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
     * @return 
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse //<editor-fold defaultstate="collapsed" desc="comment">
            response
//</editor-fold>
)
            throws ServletException, IOException {
        //processRequest(request, response);
        
        
    Connection connection = null;
        List<String> errorMsgs = new ArrayList<>();
        
        try{
            String custFName = request.getParameter("custFName");
            String custLName = request.getParameter("custLName");
            String custPhoneNumber = request.getParameter("custPhoneNumber");
            String custEmail = request.getParameter("custEmail");
            String checkInDate = request.getParameter("checkInDate");
            String checkOutDate = request.getParameter("checkOutDate");
            String roomType = request.getParameter("roomType");
            String paymentMethod = request.getParameter("paymentMethod");
            
            if (custFName == null || custFName.isEmpty()) {
                errorMsgs.add("Customer First Name is required");
            }
            
            if (custLName == null || custLName.isEmpty()) {
                errorMsgs.add("Customer Last Name is required");
            }
            
            if (custPhoneNumber == null || custPhoneNumber.isEmpty()) {
                errorMsgs.add("Customer phone number is required");
            }
            
            if (custEmail == null || custEmail.isEmpty()) {
                errorMsgs.add("Customer Email is required");
            }
            
             if (checkInDate == null || checkInDate.isEmpty()) {
                errorMsgs.add("Check-in Date is required");
            }

            if (checkOutDate == null || checkOutDate.isEmpty()) {
                errorMsgs.add("Check-out Date is required");
            }
            
            if (roomType == null || roomType.isEmpty()) {
                errorMsgs.add("Room Type is required");
            }
            
            if (paymentMethod == null || paymentMethod.isEmpty()) {
                errorMsgs.add("Payment Method is required");
            }
            
             if (!errorMsgs.isEmpty()) {
                request.setAttribute("errorMsgs", errorMsgs);
                request.getRequestDispatcher("/error.jsp").forward(request, response);
                return;
                
            }
            
            bookCust book = new bookCust();
            book.setCustFName(custFName);
            book.setCustLName(custLName);
            book.setCustPhoneNumber(custPhoneNumber);
            book.setCustEmail(custEmail);
            book.setCheckInDate(checkInDate);
            book.setCheckOutDate(checkOutDate);
            book.setPaymentMethod(paymentMethod);
            
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/KingLandHotel", "app", "app");
            
            connection.setAutoCommit(false);

            String action = request.getParameter("action");
            if ("Add".equals(action)) {
                
                String insertCustomerQuery = "INSERT INTO CUSTOMER (custFName, custLName, custPhoneNumber, custEmail) " +
                 "VALUES (?, ?, ?, ?)";

                
                String insertBookingQuery = "INSERT INTO BOOKING (checkInDate, checkOutDate) " +
"                VALUES (?, ?)";
                
                String insertRoomQuery = "INSERT INTO ROOM (roomType) VALUES (?)";
                
                String insertPaymentQuery = "INSERT INTO PAYMENT (paymentMethod) VALUES (?)";
                
                
                   
                   try (PreparedStatement customerStatement = connection.prepareStatement(insertCustomerQuery);
                        PreparedStatement bookingStatement = connection.prepareStatement(insertBookingQuery);
                        PreparedStatement roomStatement = connection.prepareStatement(insertRoomQuery);
                        PreparedStatement paymentStatement = connection.prepareStatement(insertPaymentQuery)) {

                  // Set parameters for CUSTOMER table
    customerStatement.setString(1, book.getCustFName());
    customerStatement.setString(2, book.getCustLName());
    customerStatement.setString(3, book.getCustPhoneNumber());
    customerStatement.setString(4, book.getCustEmail());
    

    // Execute CUSTOMER table insert
    customerStatement.executeUpdate();

    // Set parameters for BOOKING table
    bookingStatement.setString(1, book.getCheckInDate());
    bookingStatement.setString(2, book.getCheckOutDate());

    // Execute BOOKING table insert
    bookingStatement.executeUpdate();

    // Set parameters for ROOM table
    roomStatement.setString(1, book.getRoomType());

    // Execute ROOM table insert
    roomStatement.executeUpdate();

    // Set parameters for PAYMENT table
    paymentStatement.setString(1, book.getPaymentMethod());

    // Execute PAYMENT table insert
    paymentStatement.executeUpdate();
    
    
    

    
    //commit transaction
    connection.commit();
    //close connection
    connection.setAutoCommit(true);
    connection.close();
    request.getRequestDispatcher("/homeCust.jsp").forward(request, response);

} catch (SQLException e) {
    // Handle exceptions
    e.printStackTrace();



                   }
            }
            
            }catch (ClassNotFoundException | SQLException e) {
   // List<String> errorMsgs = new ArrayList<>();

    try {
        if (connection != null) {
            connection.rollback();
            connection.setAutoCommit(true);
             connection.close();

        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }

    errorMsgs.add("An error occurred. Please try again later.");
    request.setAttribute("errorMsgs", errorMsgs);

    try {
        if (request != null) {
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    } catch (ServletException | IOException ex) {
        ex.printStackTrace();
    }
 finally {
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
    }
}
    

   





           
    
        
    
   

package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bean.CustDashboardBean;


public class UpdateCustServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Connection connection = null;
        List<String> errorMsgs = new ArrayList<>();
        
        try{
            String custID = request.getParameter("custID");
            String custFName = request.getParameter("custFName");
            String custLName = request.getParameter("custLName");
            String custPhoneNumber = request.getParameter("custPhoneNumber");
            String custEmail = request.getParameter("custEmail");
            String custAddress = request.getParameter("custAddress");
            
            if (custFName == null || custFName.isEmpty()) {
                errorMsgs.add("First Name is required");
            }

            if (custLName == null || custLName.isEmpty()) {
                errorMsgs.add("Last Name is required");
            }

            if (custPhoneNumber == null || custPhoneNumber.isEmpty()) {
                errorMsgs.add("Phone Number is required");
            }

            if (custEmail == null || custEmail.isEmpty()) {
                errorMsgs.add("Email is required");
            }

            if (custAddress == null || custAddress.isEmpty()) {
                errorMsgs.add("Address is required");
            }

            if (!errorMsgs.isEmpty()) {
                request.setAttribute("errorMsgs", errorMsgs);
                request.getRequestDispatcher("/error.jsp").forward(request, response);
                return; 
            }
            
            CustDashboardBean customer = new CustDashboardBean();
            customer.setCustID(custID);
            customer.setCustFName(custFName);
            customer.setCustLName(custLName);
            customer.setCustPhoneNumber(custPhoneNumber);
            customer.setCustEmail(custEmail);
            customer.setCustAddress(custAddress);
            
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/KingLandHotel", "app", "app");

            connection.setAutoCommit(false);

            String action = request.getParameter("action");
            if ("Update".equals(action)) {
                
                String updateQuery = "UPDATE CUSTOMER SET CUSTFNAME=?, CUSTLNAME=?, CUSTPHONENUMBER=?, CUSTEMAIL=?, CUSTADDRESS=? WHERE CUSTID=?";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                        preparedStatement.setString(1, customer.getCustFName());
                        preparedStatement.setString(2, customer.getCustLName());
                        preparedStatement.setString(3, customer.getCustPhoneNumber());
                        preparedStatement.setString(4, customer.getCustEmail());
                        preparedStatement.setString(5, customer.getCustAddress());
                        preparedStatement.setString(6, customer.getCustID()); 
                        
                        preparedStatement.executeUpdate();

                        connection.commit();
                        request.getRequestDispatcher("/success.jsp").forward(request, response);
                    }
            }

        } catch (ClassNotFoundException | SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

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
                ex.printStackTrace();
            }
        }
    }
}

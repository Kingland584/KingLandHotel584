package controller;

import bean.LoginAdminBean;
import dao.LoginAdminDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginAdminServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String username = request.getParameter("adminUsername");
            String password = request.getParameter("adminPassword");
            LoginAdminBean loginBean = new LoginAdminBean(username, password);

            LoginAdminDao loginDao = new LoginAdminDao(); // create LoginDao Object
            String userValidate = loginDao.authenticateUser(loginBean);

            if (userValidate.equals("SUCCESS")) {
                request.setAttribute("adminUsername", username);
                RequestDispatcher view = request.getRequestDispatcher("/AdminPage.jsp");
                view.forward(request, response);
            } else {
                request.setAttribute("errMessage", userValidate);
                RequestDispatcher view = request.getRequestDispatcher("/LoginAdmin.jsp");
                view.include(request, response);

                // Display pop-up for invalid credentials using JavaScript
                out.println("<script type=\"text/javascript\">");
                out.println("alert('" + userValidate + "');");
                out.println("</script>");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}

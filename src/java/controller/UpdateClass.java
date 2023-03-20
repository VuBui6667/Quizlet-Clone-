/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DAO;
import model.Class;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author vieta
 */
@WebServlet(name = "UpdateClass", urlPatterns = {"/updateClass"})
public class UpdateClass extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateClass</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateClass at " + request.getContextPath() + "</h1>");
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
        String id_raw = request.getParameter("id");
        int id;
        DAO d = new DAO();
        try {
            id = Integer.parseInt(id_raw);
            Class c = d.getClassByClassId(id);
            request.setAttribute("c", c);
            request.getRequestDispatcher("ClassSet.jsp").forward(request, response);
        } catch (Exception e) {

        }

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
        String id_raw = request.getParameter("classId");
        String className = request.getParameter("classname1");
        String details = request.getParameter("detailsclass1");
        String schoolName = request.getParameter("schoolname1");
//        Boolean isControllStudySet = Boolean.parseBoolean(request.getParameter("adddel"));
//        Boolean isControllMember = Boolean.parseBoolean(request.getParameter("addpeople"));
        HttpSession ses = request.getSession();
        User user = (User) ses.getAttribute("user");
        DAO d = new DAO();
        try {
            int id = Integer.parseInt(id_raw);
            Class c = new Class(id, className, details, true, "", true, schoolName, user.getId());
            d.updateClass(c);
            System.out.println("test");
            response.sendRedirect("classSet?id=" + id);
        } catch (NumberFormatException e) {
            System.out.println(e);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

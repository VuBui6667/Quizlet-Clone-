/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.User;
import model.Class;

/**
 *
 * @author vieta
 */
@WebServlet(name = "CreateClass", urlPatterns = {"/class"})
public class CreateClass extends HttpServlet {

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
            out.println("<title>Servlet CreateClass</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateClass at " + request.getContextPath() + "</h1>");
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
        DAO d = new DAO();
        HttpSession ses = request.getSession();
        User user = (User) ses.getAttribute("user");
        int userId = user.getId();
        ArrayList<Class> listMemCl = d.getListMemberByUserId(userId);
        ArrayList<Class> listClass = d.getClassByUserId(userId);
        request.setAttribute("listClass", listClass);
         request.setAttribute("listMemCl", listMemCl);
        ArrayList<Class> listC = d.getAllClass();
        ses.setAttribute("listC", listC);
        ses.setAttribute("d", d);
        
        request.getRequestDispatcher("class.jsp").forward(request, response);
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

//            String name = request.getParameter("className");
        String className = request.getParameter("classname");
        String details = request.getParameter("detailsclass");
        String schoolName = request.getParameter("schoolname");
        Boolean isControllStudySet = Boolean.parseBoolean(request.getParameter("adddel"));
        Boolean isControllMember = Boolean.parseBoolean(request.getParameter("addpeople"));
        HttpSession ses = request.getSession();
        User user = (User) ses.getAttribute("user");
        Class c = new Class(1, className, details, isControllMember, "", isControllStudySet, schoolName, user.getId());
        DAO dao = new DAO();
        request.setAttribute("schoolname", schoolName);
        request.setAttribute("classname", className);
        request.setAttribute("c", c);
        dao.createClass(c);
        doGet(request, response);

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

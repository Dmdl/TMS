/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lakmal.controllers;

import com.lakmal.dao.CourseDao;
import com.lakmal.dao.CourseDaoImpl;
import com.lakmal.dao.PersonDao;
import com.lakmal.dao.PersonDaoImpl;
import com.lakmal.domain.Course;
import com.lakmal.domain.Person;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lakmal
 */
@WebServlet(name = "ProcessData", urlPatterns = {"/ProcessData"})
public class ProcessData extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        if ("save".equals(action)) {
            try (PrintWriter out = response.getWriter()) {
                String code = request.getParameter("code");
                String name = request.getParameter("name");
                Person person = new Person(code, name);

                PersonDao personDao = new PersonDaoImpl();
                int id = personDao.save(person);
                if (-1 != id) {
                    out.println("Successfully Saved....");
                } else {
                    out.println("Error Saving record....");
                }
            }
        } else if ("saveCourse".equals(action)) {
            try (PrintWriter out = response.getWriter()) {
                String code = request.getParameter("code");
                String name = request.getParameter("name");
                Course course = new Course(code, name);

                CourseDao courseDao = new CourseDaoImpl();
                int id = courseDao.save(course);
                if (-1 != id) {
                    out.println("Successfully Saved....");
                } else {
                    out.println("Error Saving record....");
                }
            }
        } else if ("saveSession".equals(action)) {
            try (PrintWriter out = response.getWriter()) {
                String code = request.getParameter("code");
                String name = request.getParameter("name");
                String course = request.getParameter("course");
                String date = request.getParameter("date");
                String fromT = request.getParameter("fromT");
                String toT = request.getParameter("toT");
                System.out.println("code " + code);
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

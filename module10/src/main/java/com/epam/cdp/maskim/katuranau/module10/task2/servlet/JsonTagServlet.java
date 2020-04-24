package com.epam.cdp.maskim.katuranau.module10.task2.servlet;

import com.epam.cdp.maskim.katuranau.module10.task2.util.file.JsonFileLoader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JsonTagServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("chooseJsonFile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message = req.getParameter("fileName");
        req.setAttribute("json", JsonFileLoader.loadFile(message));
        req.getRequestDispatcher("/jsonTag.jsp").forward(req, resp);
    }
}

package com.epam.cdp.maskim.katuranau.module10.task1.servlet;

import com.epam.cdp.maskim.katuranau.module10.task1.exception.ValidationException;
import com.epam.cdp.maskim.katuranau.module10.task1.service.MessageService;
import com.epam.cdp.maskim.katuranau.module10.task1.service.impl.MessageServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

public class BaseServlet extends HttpServlet {

    private MessageService messageService;
    private ObjectMapper objectMapper;
    private Properties properties;

    public BaseServlet() throws IOException {
        super();
        this.messageService = new MessageServiceImpl();
        this.objectMapper = new ObjectMapper();
        String filePath = this.getClass().getClassLoader().getResource("application.properties").getPath();
        properties = new Properties();
        properties.load(new FileInputStream(filePath));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int viewsAmount = updateCookies(req, resp);
        req.setAttribute("viewsAmount", viewsAmount);
        req.setAttribute("dateNow", new Date());
        req.setAttribute("messages", messageService.getMessages());
        req.getRequestDispatcher("messages.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String message = req.getParameter("message");
        try {
            messageService.addMessage(message.trim());
            writeResponse(resp, updateCookies(req, resp));
        } catch (ValidationException e) {
            writeErrorResponse(resp, e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, String> parameters = getParameters(req);
        int messageId = Integer.parseInt(parameters.get("messageId"));
        String message = parameters.get("message");
        try {
            messageService.updateMessage(messageId, message);
            writeResponse(resp, updateCookies(req, resp));
        } catch (ValidationException e) {
            writeErrorResponse(resp, e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, String> parameters = getParameters(req);
        int messageId = Integer.parseInt(parameters.get("messageId"));
        try {
            messageService.deleteMessage(messageId);
            writeResponse(resp, updateCookies(req, resp));
        } catch (ValidationException e) {
            writeErrorResponse(resp, e.getMessage());
        }
    }

    private Map<String, String> getParameters(HttpServletRequest req) throws IOException {
        String body = IOUtils.toString(req.getInputStream(), properties.getProperty("base.servlet.encoding"));
        String[] splitBody = body.split(properties.getProperty("base.servlet.conjunction"));
        Map<String, String> parameters = new HashMap<>(splitBody.length);
        Arrays.stream(splitBody).forEach(s -> splitParameterIntoMap(s, parameters));
        return parameters;
    }

    private void splitParameterIntoMap(String parameter, Map<String, String> parameters) {
        final String[] split = parameter.split(properties.getProperty("base.servlet.equals.regex"));
        if (split.length == 2) {
            parameters.put(split[0], split[1].replaceAll(properties.getProperty("base.servlet.url.space"),
                    properties.getProperty("base.servlet.string.space")));
        }
    }

    private void writeResponse(HttpServletResponse response, int viewsAmount) throws IOException {
        List<Object> responseList = new ArrayList<>();
        responseList.add(viewsAmount);
        responseList.add(messageService.getMessages());
        PrintWriter printWriter = response.getWriter();
        response.setContentType("application/json;charset=UTF-8");
        printWriter.println(objectMapper.writeValueAsString(responseList));
    }

    private void writeErrorResponse(HttpServletResponse response, String errorMessage) throws IOException {
        PrintWriter printWriter = response.getWriter();
        printWriter.write(errorMessage);
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    private int updateCookies(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        Cookie views;
        if (Objects.nonNull(cookies)) {
            views = Arrays.stream(cookies)
                    .filter(cookie -> cookie.getName().equals(properties.getProperty("base.servlet.cookie.name")))
                    .findFirst()
                    .orElseGet(this::getDefaultCookie);
        } else {
            views = getDefaultCookie();
        }
        int updatedValue = 0;
        try {
            updatedValue = Integer.parseInt(views.getValue()) + 1;
        } catch (NumberFormatException e) {
            System.out.println("Invalid cookie");
        }
        views.setValue(String.valueOf(updatedValue));
        response.addCookie(views);
        return updatedValue;
    }

    private Cookie getDefaultCookie() {
        return new Cookie(properties.getProperty("base.servlet.cookie.name"), "0");
    }
}

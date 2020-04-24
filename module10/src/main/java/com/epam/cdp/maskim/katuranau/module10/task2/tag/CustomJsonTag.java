package com.epam.cdp.maskim.katuranau.module10.task2.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomJsonTag extends SimpleTagSupport {

    StringWriter sw = new StringWriter();
    private static Properties properties = new Properties();

    static {
        String filePath = CustomJsonTag.class.getClassLoader().getResource("application.properties").getPath();
        try {
            properties.load(new FileInputStream(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doTag() throws JspException, IOException {
        getJspBody().invoke(sw);
        String response = sw.toString().trim();
        if (String.valueOf(response.charAt(0)).equals("[")
                && String.valueOf(response.charAt(response.length() - 1)).equals("]")) {
            response = processList(response);
        } else {
            response = processObject(response.substring(1, response.length() - 1));
        }
        response = properties.getProperty("custom.tag.prefix")
                + response + properties.getProperty("custom.tag.postfix");
        JspWriter out = getJspContext().getOut();
        out.println(response);
    }

    private static String conditionProcess(String string, String regex,
                                           String insertString1, String insertString2) {
        int start;
        int lastOperationEnd = 0;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        StringBuilder newResponse = new StringBuilder();
        while (matcher.find()) {
            start = matcher.start();
            newResponse.append(string, lastOperationEnd, start);
            newResponse.append(insertString1);
            newResponse.append(string, start, matcher.end());
            newResponse.append(insertString2);
            lastOperationEnd = matcher.end();
        }
        newResponse.append(string, lastOperationEnd, string.length());
        return newResponse.toString();
    }

    private static String processList(String response) {
        response = response.replaceFirst(properties.getProperty("custom.tag.list.prefix.remove"),
                properties.getProperty("custom.tag.empty.string"));
        response = response.replaceFirst(properties.getProperty("custom.tag.list.postfix.remove"),
                properties.getProperty("custom.tag.empty.string"));
        StringBuilder stringBuilder = new StringBuilder();
        int i = 1;
        for (String string : response.split(properties.getProperty("custom.tag.list.split.regex"))) {
            string = String.format(properties.getProperty("custom.tag.prefix.for.item.of.list"), i) + string;
            string = processObject(string) + properties.getProperty("custom.tag.postfix.for.item.of.list");
            stringBuilder.append(string);
            i++;
        }
        return stringBuilder.toString();
    }

    private static String processObject(String string) {
        string = conditionProcess(string, properties.getProperty("custom.tag.regex_1"),
                properties.getProperty("custom.tag.condition_1_1"), properties.getProperty("custom.tag.condition_1_2"));
        string = conditionProcess(string, properties.getProperty("custom.tag.regex_2"),
                properties.getProperty("custom.tag.condition_2_1"), properties.getProperty("custom.tag.condition_2_2"));
        string = conditionProcess(string, properties.getProperty("custom.tag.regex_3"),
                properties.getProperty("custom.tag.condition_3_1"), properties.getProperty("custom.tag.condition_3_2"));
        return string;
    }
}

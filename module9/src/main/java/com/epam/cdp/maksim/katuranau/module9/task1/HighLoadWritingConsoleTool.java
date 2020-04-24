package com.epam.cdp.maksim.katuranau.module9.task1;

import com.epam.cdp.maksim.katuranau.module9.task1.config.AppConfiguration;
import com.epam.cdp.maksim.katuranau.module9.task1.dao.DataBaseDao;
import com.epam.cdp.maksim.katuranau.module9.task1.dao.impl.DataBaseDaoImpl;
import com.epam.cdp.maksim.katuranau.module9.task1.model.DataBase;
import com.epam.cdp.maksim.katuranau.module9.task1.util.db.DataBaseGenerator;
import com.epam.cdp.maksim.katuranau.module9.task1.util.db.SQLQueryGenerator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//Path to resources: module9/src/main/resources/jdbc.properties
public class HighLoadWritingConsoleTool {
    public static void main(String[] args) {
        System.setProperty("path", args[0]);

        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfiguration.class);

        DataBaseDao dataBaseDao = context.getBean(DataBaseDaoImpl.class);
        DataBaseGenerator dataBaseGenerator = context.getBean(DataBaseGenerator.class);
        SQLQueryGenerator sqlQueryGenerator = context.getBean(SQLQueryGenerator.class);

        DataBase dataBase = dataBaseGenerator.generateDataBase();
        dataBaseDao.execute(sqlQueryGenerator.generateSchemaCreatingQuery(dataBase.getName()));
        dataBaseDao.execute(sqlQueryGenerator.generateTablesCreatingQuery(dataBase.getName(), dataBase.getTables()));
    }
}

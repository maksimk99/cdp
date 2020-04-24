package com.epam.cdp.maksim.katuranau.module9.task2;

import com.epam.cdp.maksim.katuranau.module9.task2.config.AppConfiguration;
import com.epam.cdp.maksim.katuranau.module9.task2.dao.DataBaseCreateDao;
import com.epam.cdp.maksim.katuranau.module9.task2.dao.DataBasePopulateDao;
import com.epam.cdp.maksim.katuranau.module9.task2.model.DataBase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/*
First command line parameter is schema name to copy
Second command line parameter is order in which data should be populated (for example - reverse)
 */
public class DatabaseCopyConsoleTool {
    public static void main(String[] args) {
        String schemaName = args[0];
        String order = args[1];

        final ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfiguration.class);

        DataBaseCreateDao dataBaseCreateDao = context.getBean(DataBaseCreateDao.class);
        DataBasePopulateDao dataBasePopulateDao = context.getBean(DataBasePopulateDao.class);

        DataBase dataBase = dataBaseCreateDao.createDatabase(schemaName);
        dataBasePopulateDao.populateData(dataBase, schemaName, order);
    }
}

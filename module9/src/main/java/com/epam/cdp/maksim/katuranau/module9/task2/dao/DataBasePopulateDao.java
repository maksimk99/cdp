package com.epam.cdp.maksim.katuranau.module9.task2.dao;

import com.epam.cdp.maksim.katuranau.module9.task2.model.DataBase;

public interface DataBasePopulateDao {

    void populateData(DataBase dataBase, String oldSchemaName, String order);
}

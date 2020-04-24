package com.epam.cdp.maksim.katuranau.module9.task1.util.generation;

import com.epam.cdp.maksim.katuranau.module9.task1.model.SQLType;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class DataGeneratorUtil {

    private Faker faker;

    @Autowired
    public DataGeneratorUtil(Faker faker) {
        this.faker = faker;
    }

    public String generateDataBaseName() {
        return generateName(1);
    }

    public String generateTableName() {
        return generateName(2);
    }

    public String generateColumnName() {
        return generateName(3);
    }

    public String generateSQLType() {
        final SQLType[] values = SQLType.values();
        return values[ThreadLocalRandom.current().nextInt(0, values.length)].getType();
    }

    private String generateName(int amount) {
        return String.join("_", faker.lorem().words(amount))
                + ThreadLocalRandom.current().nextInt(0, 10000);
    }
}

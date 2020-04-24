package com.epam.cdp.maksim.katuranau.module3.task5;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Train> trainList = createData();
        TrainCollector trainCollector = new TrainCollector();

        System.out.println("Statistic for all trains in list: "
                + trainList.stream().parallel().collect(trainCollector));

        System.out.println("\nStatistic for trains which seats more expensive than 10.5: "
                + trainList.stream().parallel()
                .filter(train -> train.getSeatPrice().compareTo(new BigDecimal("10.5")) > 0)
                .collect(trainCollector));

        System.out.println("\nStatistic for first 3 trains which count of seats more than 35: "
                + trainList.stream().parallel()
                .filter(train -> train.getCountOfSeatsInCarriage() >= 35).limit(3).collect(trainCollector));
    }

    private static List<Train> createData() {
        return new ArrayList<Train>() {{
            add(new Train(7, 35, "Station1", "Station2",
                    new BigDecimal("12.6")));
            add(new Train(8, 32, "Station3", "Station2",
                    new BigDecimal("11.3")));
            add(new Train(11, 38, "Station4", "Station1",
                    new BigDecimal("14.5")));
            add(new Train(6, 30, "Station1", "Station5",
                    new BigDecimal("15.7")));
            add(new Train(7, 33, "Station5", "Station3",
                    new BigDecimal("9.8")));
            add(new Train(9, 40, "Station1", "Station2",
                    new BigDecimal("8.9")));
            add(new Train(10, 39, "Station5", "Station4",
                    new BigDecimal("10.2")));
            add(new Train(5, 35, "Station6", "Station4",
                    new BigDecimal("11.7")));
            add(new Train(3, 38, "Station1", "Station6",
                    new BigDecimal("12.8")));
            add(new Train(9, 32, "Station4", "Station1",
                    new BigDecimal("8.8")));
            add(new Train(8, 38, "Station5", "Station3",
                    new BigDecimal("10.6")));
            add(new Train(10, 36, "Station3", "Station2",
                    new BigDecimal("7.4")));
            add(new Train(11, 33, "Station1", "Station2",
                    new BigDecimal("13.9")));
        }};
    }
}

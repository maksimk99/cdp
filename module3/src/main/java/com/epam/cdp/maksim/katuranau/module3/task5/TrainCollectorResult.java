package com.epam.cdp.maksim.katuranau.module3.task5;

import java.math.BigDecimal;
import java.util.HashSet;

public class TrainCollectorResult {
    private Integer countOfTrainSeats;
    private BigDecimal totalSeatsPrice;
    private HashSet<String> routes;

    public TrainCollectorResult() {
        this.countOfTrainSeats = 0;
        this.totalSeatsPrice = BigDecimal.ZERO;
        this.routes = new HashSet<>();
    }

    public Integer getCountOfTrainSeats() {
        return countOfTrainSeats;
    }

    public TrainCollectorResult setCountOfTrainSeats(Integer countOfTrainSeats) {
        this.countOfTrainSeats = countOfTrainSeats;
        return this;
    }

    public HashSet<String> getRoutes() {
        return routes;
    }

    public TrainCollectorResult addRoutes(String route) {
        routes.add(route);
        return this;
    }

    public TrainCollectorResult addAllRouts(HashSet<String> routes) {
        this.routes.addAll(routes);
        return this;
    }

    public BigDecimal getTotalSeatsPrice() {
        return totalSeatsPrice;
    }

    public TrainCollectorResult addTotalSeatsPrice(BigDecimal totalSeatsPrice) {
        this.totalSeatsPrice = this.totalSeatsPrice.add(totalSeatsPrice);
        return this;
    }

    @Override
    public String toString() {
        return "TrainCollectorResult{" +
                "countOfTrainSeats=" + countOfTrainSeats +
                ", totalSeatsPrice=" + totalSeatsPrice +
                ", routes=" + routes +
                '}';
    }
}

package com.epam.cdp.maksim.katuranau.module3.task5;

import java.math.BigDecimal;

public class Train {
    private int countOfTrainCarriage;
    private int countOfSeatsInCarriage;
    private String departureStation;
    private String arrivalStation;
    private BigDecimal seatPrice;

    public Train() {
    }

    public Train(int countOfTrainCarriage, int countOfSeatsInCarriage, String departureStation, String arrivalStation,
                 BigDecimal seatPrice) {
        this.countOfTrainCarriage = countOfTrainCarriage;
        this.countOfSeatsInCarriage = countOfSeatsInCarriage;
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
        this.seatPrice = seatPrice;
    }

    public int getCountOfTrainCarriage() {
        return countOfTrainCarriage;
    }

    public Train setCountOfTrainCarriage(int countOfTrainCarriage) {
        this.countOfTrainCarriage = countOfTrainCarriage;
        return this;
    }

    public int getCountOfSeatsInCarriage() {
        return countOfSeatsInCarriage;
    }

    public Train setCountOfSeatsInCarriage(int countOfSeatsInCarriage) {
        this.countOfSeatsInCarriage = countOfSeatsInCarriage;
        return this;
    }

    public String getDepartureStation() {
        return departureStation;
    }

    public Train setDepartureStation(String departureStation) {
        this.departureStation = departureStation;
        return this;
    }

    public String getArrivalStation() {
        return arrivalStation;
    }

    public Train setArrivalStation(String arrivalStation) {
        this.arrivalStation = arrivalStation;
        return this;
    }

    public BigDecimal getSeatPrice() {
        return seatPrice;
    }

    public Train setSeatPrice(BigDecimal seatPrice) {
        this.seatPrice = seatPrice;
        return this;
    }

    public int calculateNumberOfSeatsInTrain() {
        return countOfTrainCarriage * countOfSeatsInCarriage;
    }

    public BigDecimal calculateTotalSeatsPrice() {
        return seatPrice.multiply(BigDecimal.valueOf(countOfTrainCarriage * countOfSeatsInCarriage));
    }

    @Override
    public String toString() {
        return "Train{" +
                "countOfTrainCarriage=" + countOfTrainCarriage +
                ", countOfSeatsInCarriage=" + countOfSeatsInCarriage +
                ", departureStation='" + departureStation + '\'' +
                ", arrivalStation='" + arrivalStation + '\'' +
                ", seatPrice=" + seatPrice +
                '}';
    }
}

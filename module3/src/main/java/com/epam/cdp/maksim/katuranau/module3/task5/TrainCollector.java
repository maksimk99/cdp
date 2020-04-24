package com.epam.cdp.maksim.katuranau.module3.task5;

import java.util.EnumSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class TrainCollector implements Collector<Train, TrainCollectorResult, String> {
    @Override
    public Supplier supplier() {
        return TrainCollectorResult::new;
    }

    @Override
    public BiConsumer<TrainCollectorResult, Train> accumulator() {
        return (result, train) -> {
            result.setCountOfTrainSeats(result.getCountOfTrainSeats() + train.calculateNumberOfSeatsInTrain());
            result.addRoutes(train.getDepartureStation() + " - " + train.getArrivalStation());
            result.addTotalSeatsPrice(train.calculateTotalSeatsPrice());
        };
    }

    @Override
    public BinaryOperator<TrainCollectorResult> combiner() {
        return (result1, result2) -> {
            result1.setCountOfTrainSeats(result1.getCountOfTrainSeats() + result2.getCountOfTrainSeats());
            result1.addAllRouts(result2.getRoutes());
            return result1.addTotalSeatsPrice(result2.getTotalSeatsPrice());
        };
    }

    @Override
    public Function<TrainCollectorResult, String> finisher() {
        return TrainCollectorResult::toString;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return EnumSet.of(Characteristics.CONCURRENT);
    }
}

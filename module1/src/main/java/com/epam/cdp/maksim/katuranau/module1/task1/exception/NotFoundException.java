package com.epam.cdp.maksim.katuranau.module1.task1.exception;

public final class NotFoundException extends RuntimeException {
    public NotFoundException(int airCraftId, int size) {
        super(createMessage(airCraftId, size));
    }

    private static String createMessage(int airCraftId, int size) {
        if (airCraftId <= 0) {
            return "air craft id(" + airCraftId + ") should be positive";
        } else {
            return "air craft id(" + airCraftId + ") can't be larger than list size(" + size + ")";
        }
    }
}

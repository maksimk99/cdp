package com.epam.cdp.maksim.katuranau.module1.task2;

public class DynamicDoubleArray {
    private int arraySize;
    private double[] doubleArray;

    public DynamicDoubleArray() {
        this.doubleArray = new double[16];
    }

    public DynamicDoubleArray(int arrayLength) {
        this.doubleArray = new double[arrayLength];
    }

    public double get(int itemId) {
        return doubleArray[itemId];
    }

    public void add(double item) {
        int realArrayLength = doubleArray.length;
        if (realArrayLength <= arraySize) {
            double[] newDoubleArray = new double[realArrayLength * 2];
            System.arraycopy(doubleArray, 0, newDoubleArray, 0, arraySize);
            doubleArray = newDoubleArray;
        }
        doubleArray[arraySize] = item;
        arraySize++;
    }

    public boolean remove(int itemId) {
        if (itemId >=0 && itemId < arraySize) {
            double[] newDoubleArray = new double[doubleArray.length - 1];
            System.arraycopy(doubleArray, 0, newDoubleArray, 0, itemId);
            System.arraycopy(doubleArray, itemId + 1, newDoubleArray, itemId, arraySize - (itemId + 1));
            doubleArray = newDoubleArray;
            arraySize--;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        StringBuffer array = new StringBuffer();
        for (int i = 0; i < arraySize; i++) {
            array.append(doubleArray[i]);
            if (i != arraySize - 1) {
                array.append(", ");
            }
        }
        return "DynamicArrayWithGenerics{"
                + "size = " + arraySize
                + ", length = " + doubleArray.length
                + ", array = [" + array.toString() +
                "]}";
    }
}

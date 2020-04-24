package com.epam.cdp.maksim.katuranau.module1.task3;

public class DynamicArrayWithGenerics<T> {
    private int arraySize;
    private Object[] array;

    public DynamicArrayWithGenerics() {
        this.array = new Object[16];
    }

    public DynamicArrayWithGenerics(int arrayLength) {
        this.array = new Object[arrayLength];
    }

    public T get(int itemId) {
        return (T) array[itemId];
    }

    public void add(T item) {
        if (array.length <= arraySize) {
            Object[] newDoubleArray = new Object[array.length * 2];
            for (int i = 0; i < arraySize; i++) {
                newDoubleArray[i] = array[i];
            }
            array = newDoubleArray;
        }
        array[arraySize] = item;
        arraySize++;
    }

    public boolean remove(int itemId) {
        if (itemId >= 0 && itemId < arraySize) {
            Object[] newDoubleArray = new Object[array.length - 1];
            System.arraycopy(array, 0, newDoubleArray, 0, itemId);
            System.arraycopy(array, itemId + 1, newDoubleArray, itemId, arraySize - (itemId + 1));
            array = newDoubleArray;
            arraySize--;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringArray = new StringBuilder();
        for (int i = 0; i < arraySize; i++) {
            stringArray.append(this.array[i]);
            if (i != arraySize - 1) {
                stringArray.append(", ");
            }
        }
        return "DynamicArrayWithGenerics{"
                + "size = " + arraySize
                + ", length = " + array.length
                + ", array = [" + stringArray.toString() +
                "]}";
    }
}

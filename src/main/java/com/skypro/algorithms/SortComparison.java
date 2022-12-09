package com.skypro.algorithms;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class SortComparison {
    public static void main(String[] args) {
        int[] array = generateRandomArray();
        benchmarkSort(Arrays.copyOf(array, array.length), SortComparison::bubbleSort);
        benchmarkSort(Arrays.copyOf(array, array.length), SortComparison::selectionSort);
        benchmarkSort(Arrays.copyOf(array, array.length), SortComparison::insertSort);
    }

    private static void benchmarkSort(int[] array, Consumer<int[]> sortFunction) {
        long start = System.currentTimeMillis();
        sortFunction.accept(array);
        long end = System.currentTimeMillis();
        System.out.println("isSorted = " + isSorted(array));
        System.out.println("executionTime = " + (end - start) + "ms");
    }

    private static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++   ) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j+1]) {
                    int tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                }
            }
        }
    }

    private static void selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            int tmp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = tmp;
        }
    }

    private static void insertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int tmp = array[i];
            int j = i;
            while (j > 0 && array[j - 1] >= tmp) {
                array[j] = array [j - 1];
                j--;
            }
            array[j] = tmp;
        }
    }

    private static int[] generateRandomArray() {
        return IntStream.generate(() -> ThreadLocalRandom.current().nextInt(0, 1_000_000))
                .limit(10000)
                .toArray();
    }

    private static boolean isSorted(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }
}

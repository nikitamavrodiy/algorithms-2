package com.skypro.algorithms;

import com.skypro.algorithms.exceptions.*;

import java.util.Arrays;

public class IntegerListImpl implements IntegerList {
    private Integer[] array;

    public IntegerListImpl() {
        this.array = new Integer[0];
    }

    @Override
    public Integer add(Integer item) {
        checkItem(item);
        grow();
        return array[array.length - 1] = item;
    }

    @Override
    public Integer add(int index, Integer item) {
        checkItem(item);
        checkIndex(index);
        Integer[] newArray = new Integer[array.length + 1];
        for (int i = 0; i < this.array.length; i++) {
            if (i < index) {
                newArray[i] = array[i];
            } else {
                newArray[i + 1] = array[i];
            }
        }
        array = newArray;
        return array[index] = item;
    }

    @Override
    public Integer set(int index, Integer item) {
        checkItem(item);
        checkIndex(index);
        return array[index] = item;
    }

    @Override
    public Integer remove(Integer item) {
        checkItem(item);
        for (int i = 0; i < array.length; i++) {
            Integer deleted;
            if (array[i].equals(item)) {
                deleted = array[i];
                array = copyWithoutIndex(i);
                return deleted;
            }
        }
        throw new StringListIncorrectItemException();
    }

    @Override
    public Integer remove(int index) {
        checkIndex(index);
        Integer deleted = array[index];
        array = copyWithoutIndex(index);
        return deleted;
    }

    @Override
    public boolean contains(Integer item) {
        checkItem(item);
        for (Integer s : array) {
            if (item.equals(s)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Integer item) {
        checkItem(item);
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    public int indexOfBinary(Integer item) {
        Integer[] array = toArray();
        sort(array);
        return binarySearch(array, item);
    }

    @Override
    public int lastIndexOf(Integer item) {
        checkItem(item);
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        checkIndex(index);
        return array[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        if (otherList == null) {
            throw new IntegerListArgumentNullException();
        }
        if (otherList.getClass() != this.getClass() || otherList.size() != this.size()) {
            return false;
        }
        for (int i = 0; i < array.length; i++) {
            if (!otherList.get(i).equals(this.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return array.length;
    }

    @Override
    public boolean isEmpty() {
        return array.length == 0;
    }

    @Override
    public void clear() {
        this.array = new Integer[0];
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(array, array.length);
    }

    private void checkItem(Integer item) {
        if (item == null) {
            throw new IntegerListArgumentNullException();
        }
    }

    private void checkIndex(int index) {
        if (index > array.length - 1 || index < 0) {
            throw new IntegerListIncorrectIndexException();
        }
    }

    private void sort(Integer[] array) {
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

    private int binarySearch(Integer[] array, int el) {
        int min = 0;
        int max = array.length - 1;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (el == array[mid]) {
                return mid;
            }
            if (el < array[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return -1;
    }

    private void grow() {
        array = Arrays.copyOf(array, array.length + 1);
    }

    private Integer[] copyWithoutIndex(int index) {
        Integer[] arrayNew = new Integer[array.length - 1];
        int j = 0;
        for (int i = 0; i < array.length - 1; i++) {
            if (j == index) {
                j++;
            }
            arrayNew[i] = array[j];
            j++;
        }
        return arrayNew;
    }
}
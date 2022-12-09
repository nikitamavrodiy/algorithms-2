package com.skypro.algorithms;

import com.skypro.algorithms.exceptions.StringListArgumentNullException;
import com.skypro.algorithms.exceptions.StringListIncorrectIndexException;
import com.skypro.algorithms.exceptions.StringListIncorrectItemException;

import java.util.Arrays;

public class StringListImpl implements StringList {
    private String[] array;

    public StringListImpl() {
        this.array = new String[0];
    }

    @Override
    public String add(String item) {
        checkItem(item);
        grow();
        return array[array.length - 1] = item;
    }

    @Override
    public String add(int index, String item) {
        checkItem(item);
        checkIndex(index);
        String[] newArray = new String[array.length + 1];
        for (int i = 0; i < array.length; i++) {
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
    public String set(int index, String item) {
        checkItem(item);
        checkIndex(index);
        return array[index] = item;
    }

    @Override
    public String remove(String item) {
        checkItem(item);
        for (int i = 0; i < array.length; i++) {
            String deleted;
            if (array[i].equals(item)) {
                deleted = array[i];
                array = copyWithoutIndex(i);
                return deleted;
            }
        }
        throw new StringListIncorrectItemException();
    }

    @Override
    public String remove(int index) {
        checkIndex(index);
        String deleted = array[index];
        array = copyWithoutIndex(index);
        return deleted;
    }

    @Override
    public boolean contains(String item) {
        checkItem(item);
        for (String s : array) {
            if (item.equals(s)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(String item) {
        checkItem(item);
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        checkItem(item);
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        checkIndex(index);
        return array[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        if (otherList == null) {
            throw new StringListArgumentNullException();
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
        this.array = new String[0];
    }

    @Override
    public String[] toArray() {
        return Arrays.copyOf(array, array.length);
    }

    private void checkItem(String item) {
        if (item == null) {
            throw new StringListArgumentNullException();
        }
    }

    private void checkIndex(int index) {
        if (index > array.length - 1 || index < 0) {
            throw new StringListIncorrectIndexException();
        }
    }

    private void grow() {
        array = Arrays.copyOf(array, array.length + 1);
    }

    private String[] copyWithoutIndex(int index) {
        String[] arrayNew = new String[array.length - 1];
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

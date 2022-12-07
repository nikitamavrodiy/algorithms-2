package com.skypro.algorithms;

import com.skypro.algorithms.exceptions.StringListArgumentNullException;
import com.skypro.algorithms.exceptions.StringListIncorrectIndexException;
import com.skypro.algorithms.exceptions.StringListIncorrectItemException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StringListTest {
    private StringList array;

    public static List<Arguments> testSuites() {
        return List.of(
                Arguments.of("Test1"),
                Arguments.of("test2"),
                Arguments.of("  "),
                Arguments.of("")
        );
    }

    @BeforeEach
    public void setUp() {
        array = new StringListImpl();
        array.add("default1");
        array.add("default2");
        array.add("default3");
        array.add("default4");
    }

    @ParameterizedTest
    @MethodSource("testSuites")
    public void add(String item) {
        int defaultSize = array.size();
        assertEquals(item, array.add(item), "Сопоставление входных и выходных данных");
        assertThrows(StringListArgumentNullException.class, () -> array.add(null), "Выброс ошибки при нулевом аргументе");
        assertEquals(defaultSize + 1, array.size(), "Проверка длины массива");
    }

    @ParameterizedTest
    @MethodSource("testSuites")
    public void addToIndex(String item) {
        int defaultSize = array.size();
        int index = defaultSize / 2;
        assertEquals(item, array.add(index, item), "Сопоставление входных и выходных данных");
        assertThrows(StringListArgumentNullException.class, () -> array.add(index, null), "ошибка, аргумент null");
        assertThrows(StringListIncorrectIndexException.class, () -> array.add(array.size(), item), "ошибка, превышение индекса");
        assertEquals(defaultSize + 1, array.size(), "Проверка длины массива");
    }

    @ParameterizedTest
    @MethodSource("testSuites")
    public void set(String item) {
        int defaultSize = array.size();
        int index = defaultSize / 2;
        assertEquals(item, array.set(index, item), "Сопоставление входных и выходных данных");
        assertThrows(StringListArgumentNullException.class, () -> array.set(index, null), "ошибка, аргумент null");
        assertThrows(StringListIncorrectIndexException.class, () -> array.set(array.size(), item), "ошибка, если превышение индекса");
        assertThrows(StringListIncorrectIndexException.class, () -> array.set(-1, item), "ошибка, если индекс меньше нуля");
        assertEquals(defaultSize, array.size(), "Проверка длины массива");
    }

    @Test
    public void removeItem() {
        int defaultSize = array.size();
        assertEquals("default2", array.remove("default2"), "Сопоставление входных и выходных данных");
        assertThrows(StringListArgumentNullException.class, () -> array.remove(null), "ошибка, аргумент null");
        assertThrows(StringListIncorrectItemException.class, () -> array.remove("default2"), "ошибка, отсутствие объекта");
        assertEquals(defaultSize - 1, array.size(), "Проверка длины массива");
    }

    @Test
    public void removeIndex() {
        int defaultSize = array.size();
        assertEquals("default2", array.remove(1), "Сопоставление входных и выходных данных");
        assertThrows(StringListIncorrectIndexException.class, () -> array.remove(array.size()), "ошибка, если превышение индекса");
        assertThrows(StringListIncorrectIndexException.class, () -> array.remove(-1), "ошибка, если индекс меньше нуля");
        assertEquals(defaultSize - 1, array.size(), "Проверка длины массива");
    }

    @Test
    public void contains() {
        assertTrue(array.contains("default2"));
        assertFalse(array.contains("default0"));
        assertThrows(StringListArgumentNullException.class, () -> array.contains(null));
    }

    @Test
    public void size() {
        assertEquals(array.size(), 4);
    }

    @Test
    void indexOf() {
        assertEquals(-1, array.indexOf("def"));
        assertEquals(2, array.indexOf("default3"));
        assertThrows(StringListArgumentNullException.class, () -> array.indexOf(null));
    }

    @Test
    void lastIndexOf() {
        assertEquals(-1, array.lastIndexOf("def"));
        assertEquals(0, array.lastIndexOf("default1"));
        assertThrows(StringListArgumentNullException.class, () -> array.lastIndexOf(null));
    }

    @Test
    void getTest() {
        assertEquals("default3", array.get(2));
        assertThrows(StringListIncorrectIndexException.class, () -> array.get(array.size()), "ошибка, если превышение индекса");
        assertThrows(StringListIncorrectIndexException.class, () -> array.get(-1), "ошибка, если индекс меньше нуля");
    }

    @Test
    void equalsTest() {
        assertThrows(StringListArgumentNullException.class, () -> array.equals(null));
        StringList otherList = new StringListImpl();
        otherList.add("default1");
        otherList.add("default2");
        otherList.add("default3");
        assertFalse(array.equals(otherList));
        otherList.add("default453");
        assertFalse(array.equals(otherList));
        otherList.set(3,"default4");
        assertTrue(array.equals(otherList));
    }

    @Test
    void isEmptyTest() {
        assertFalse(array.isEmpty());
        StringList emptyList = new StringListImpl();
        assertTrue(emptyList.isEmpty());
    }

    @Test
    void clearTest() {
        array.clear();
        assertTrue(array.isEmpty() && array.size() == 0);
    }

    @Test
    void toArray() {
        String[] array2 = new String[]{"default1", "default2", "default3", "default4"};
        assertNotSame(array.toArray(), array2);
        assertEquals(array.size(), array.toArray().length);
        assertArrayEquals(array2, array.toArray());
    }
}

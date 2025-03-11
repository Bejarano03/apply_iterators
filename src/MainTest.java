import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @org.junit.jupiter.api.AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void testArrayListSum() {
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);

        Iterator<Integer> iterator = numbers.iterator();
        int sum = 0;

        while(iterator.hasNext()) {
            sum += iterator.next();
        }

        assertEquals(10, sum);
    }

    @Test
    void testArrayListRemoveEven() {
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);
        numbers.add(6);

        Iterator<Integer> iterator = numbers.iterator();
        while(iterator.hasNext()) {
            int num = iterator.next();
            if(num % 2 == 0) {
                iterator.remove();
            }
        }

        assertEquals(3, numbers.size());
        assertTrue(numbers.contains(1));
        assertTrue(numbers.contains(3));
        assertTrue(numbers.contains(5));
        assertFalse(numbers.contains(2));
        assertFalse(numbers.contains(4));
        assertFalse(numbers.contains(6));
    }

    @Test
    void testArrayListDirectRemoval() {
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);

        Iterator<Integer> iterator = numbers.iterator();
        assertThrows(java.util.ConcurrentModificationException.class, () -> {
            while (iterator.hasNext()) {
                int num = iterator.next();
                if (num % 2 != 0) {
                    numbers.remove(Integer.valueOf(num)); // Direct removal from list
                }
            }
        });
    }

    @Test
    void testHashMapIterationAndModification() {
        HashMap<String, Integer> categoryCounts = new HashMap<>();
        categoryCounts.put("Books", 12);
        categoryCounts.put("Movies", 8);
        categoryCounts.put("Music", 15);
        categoryCounts.put("Games", 10);
        categoryCounts.put("Electronics", 3);

        Iterator<Map.Entry<String, Integer>> hashIterator = categoryCounts.entrySet().iterator();
        while (hashIterator.hasNext()) {
            Map.Entry<String, Integer> entry = hashIterator.next();
            if (entry.getKey().equals("Books")) {
                entry.setValue(entry.getValue() + 5);
            }
            if (entry.getKey().equals("Electronics")) {
                hashIterator.remove();
            }
        }

        assertEquals(4, categoryCounts.size());
        assertEquals(17, categoryCounts.get("Books"));
        assertNull(categoryCounts.get("Electronics"));
    }

    @Test
    void testTreeMapColdestAndHottest() {
        TreeMap<String, Double> monthlyTemperatures = new TreeMap<>();
        monthlyTemperatures.put("January", 32.5);
        monthlyTemperatures.put("February", 35.2);
        monthlyTemperatures.put("March", 45.8);
        monthlyTemperatures.put("April", 55.1);
        monthlyTemperatures.put("May", 65.0);
        monthlyTemperatures.put("June", 72.3);

        Iterator<Map.Entry<String, Double>> treeMapIterator = monthlyTemperatures.entrySet().iterator();

        String coldestMonth = "";
        String hottestMonth = "";
        double minTemp = Double.MAX_VALUE;
        double maxTemp = Double.MIN_VALUE;

        while (treeMapIterator.hasNext()) {
            Map.Entry<String, Double> entry = treeMapIterator.next();
            String month = entry.getKey();
            double temp = entry.getValue();

            if (temp < minTemp) {
                minTemp = temp;
                coldestMonth = month;
            }

            if (temp > maxTemp) {
                maxTemp = temp;
                hottestMonth = month;
            }
        }

        assertEquals("January", coldestMonth);
        assertEquals(32.5, minTemp);
        assertEquals("June", hottestMonth);
        assertEquals(72.3, maxTemp);
    }

    @Test
    void testRandomArrayList() {
        ArrayList<Integer> numbers = new ArrayList<>();
        Random random = new Random();

        for (int i = 1; i <= 10; i++) {
            numbers.add(random.nextInt(100));
        }

        Iterator<Integer> iterator = numbers.iterator();
        int sum = 0;
        while(iterator.hasNext()) {
            int num = iterator.next();
            sum += num;
        }

        assertTrue(sum >= 0);
    }
}
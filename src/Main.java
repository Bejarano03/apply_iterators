import java.util.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>();
        Random random = new Random();

        for (int i = 1; i <= 10; i++) {
            numbers.add(random.nextInt(100));
        }

        System.out.println("Initial ArrayList: " + numbers + "\n");

        Iterator<Integer> iterator = numbers.iterator();
        int sum = 0;

        while(iterator.hasNext()) {
            int num = iterator.next();
            System.out.println("Iterator: " + num);
            sum += num;
        }

        System.out.println("\nTotal Sum: " + sum + "\n");

        iterator = numbers.iterator();
        try {
            while(iterator.hasNext()) {
                int num = iterator.next();
                if(num % 2 == 0) {
                    iterator.remove();
                    System.out.println("Removed (using iterator): " + num);
                }
            }
        } catch (Exception e) {
            System.out.println("\nException caught: " + e.getMessage());
        }
        System.out.println("\nArrayList after removing even numbers: " + numbers);

        iterator = numbers.iterator(); // Reset iterator
        try {
            while (iterator.hasNext()) {
                int num = iterator.next();
                if (num % 2 != 0) {
                    numbers.remove(Integer.valueOf(num)); // Direct removal from list
                    System.out.println("Removed (using list's remove()): " + num);
                }
            }
        } catch (Exception e) {
            System.out.println("Exception caught: " + e.getMessage());
        }

        System.out.println("\nList after direct modification: " + numbers);

        HashMap<String, Integer> categoryCounts = new HashMap<>();
        categoryCounts.put("Books", 12);
        categoryCounts.put("Movies", 8);
        categoryCounts.put("Music", 15);
        categoryCounts.put("Games", 10);
        categoryCounts.put("Electronics", 3);

        System.out.println("\nInitial HashMap: " + categoryCounts + "\n");

        Iterator<Map.Entry<String, Integer>> hashIterator = categoryCounts.entrySet().iterator();
        while (hashIterator.hasNext()) {
            Map.Entry<String, Integer> entry = hashIterator.next();
            System.out.println("Category: " + entry.getKey() + ", Count: " + entry.getValue());

            // Step 3: Modify values during iteration
            if (entry.getKey().equals("Books")) {
                entry.setValue(entry.getValue() + 5); // Increase Books count by 5
            }

            // Step 4: Safely remove an entry during iteration
            if (entry.getKey().equals("Electronics")) {
                hashIterator.remove(); // Safe removal using iterator
                System.out.println("Removed category: Electronics");
            }
        }

        System.out.println("Updated HashMap after iteration: " + categoryCounts + "\n");

        TreeMap<String, Double> monthlyTemperatures = new TreeMap<>();
        monthlyTemperatures.put("January", 32.5);
        monthlyTemperatures.put("February", 35.2);
        monthlyTemperatures.put("March", 45.8);
        monthlyTemperatures.put("April", 55.1);
        monthlyTemperatures.put("May", 65.0);
        monthlyTemperatures.put("June", 72.3);

        System.out.println("Monthly Temperatures: " + monthlyTemperatures);

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

        System.out.println("Coldest Month: " + coldestMonth + " with " + minTemp + "°F");
        System.out.println("Hottest Month: " + hottestMonth + " with " + maxTemp + "°F");
    }
}
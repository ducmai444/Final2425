package hus.oop.statistics;

import java.util.Random;

public class TestStatistics {
    private Statistics statistics;

    public TestStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public static void main(String[] args) {
        TestStatistics test = new TestStatistics(null);
        test.testMyArrayList();
        test.testMyLinkedList();
    }

    public void testMyArrayList() {
        System.out.println("Testing MyArrayList:");
        Random random = new Random();
        int length = random.nextInt(21) + 30; // Random number between 30 and 50
        
        MyArrayList list = new MyArrayList();
        for (int i = 0; i < length; i++) {
            list.add(random.nextDouble() * 19 + 1); // Random number between 1 and 20
        }
        
        statistics = new Statistics(list);
        
        System.out.println("Original data: " + list);
        System.out.println("Sorted data: " + list.sortIncreasing());
        System.out.println("Max: " + statistics.max());
        System.out.println("Min: " + statistics.min());
        System.out.println("Mean: " + statistics.mean());
        System.out.println("Variance: " + statistics.variance());
        System.out.println("Ranks: " + arrayToString(statistics.rank()));
        
        double searchValue = list.get(random.nextInt(length));
        System.out.println("Searching for " + searchValue + ": " + statistics.search(searchValue));
        System.out.println();
    }

    public void testMyLinkedList() {
        System.out.println("Testing MyLinkedList:");
        Random random = new Random();
        int length = random.nextInt(21) + 30; // Random number between 30 and 50
        
        MyLinkedList list = new MyLinkedList();
        for (int i = 0; i < length; i++) {
            list.add(random.nextDouble() * 19 + 1); // Random number between 1 and 20
        }
        
        statistics = new Statistics(list);
        
        System.out.println("Original data: " + list);
        System.out.println("Sorted data: " + list.sortIncreasing());
        System.out.println("Max: " + statistics.max());
        System.out.println("Min: " + statistics.min());
        System.out.println("Mean: " + statistics.mean());
        System.out.println("Variance: " + statistics.variance());
        System.out.println("Ranks: " + arrayToString(statistics.rank()));
        
        double searchValue = list.get(random.nextInt(length));
        System.out.println("Searching for " + searchValue + ": " + statistics.search(searchValue));
        System.out.println();
    }

    private String arrayToString(double[] array) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(array[i]);
        }
        sb.append("]");
        return sb.toString();
    }
}

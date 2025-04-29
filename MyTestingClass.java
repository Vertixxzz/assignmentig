import java.util.Random;

public class MyTestingClass {
    private int id;

    public MyTestingClass(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyTestingClass that = (MyTestingClass) o;
        return id == that.id;
    }

    public static void main(String[] args) {
        MyHashTable<MyTestingClass, Integer> table = new MyHashTable<>();
        Random rnd = new Random();
        for (int i = 0; i < 10000; i++) {
            table.put(new MyTestingClass(rnd.nextInt()), i);
        }
        int[] buckets = table.getBucketSizes();
        for (int i = 0; i < buckets.length; i++) {
            System.out.println("Bucket " + i + ": " + buckets[i]);
        }
    }
}

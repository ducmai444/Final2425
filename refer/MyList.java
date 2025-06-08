package refer;

public interface MyList extends MyIterable {
    void add(double value);

    int binarySearch(double value);

    void insert(double value, int index);

    void remove(int index);

    int size();

    MyList sortIncreasing();
}
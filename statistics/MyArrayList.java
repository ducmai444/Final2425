package hus.oop.statistics;

public class MyArrayList extends MyAbstractList {
    private static final int DEFAULT_CAPACITY = 16;
    private double[] data;
    private int size;

    /**
     * Khởi tạo dữ liệu mặc định.
     */
    public MyArrayList() {
        data = new double[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(double data) {
        if (size == this.data.length) {
            allocateMore();
        }
        this.data[size++] = data;
    }

    @Override
    public void insert(double data, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (size == this.data.length) {
            allocateMore();
        }
        for (int i = size; i > index; i--) {
            this.data[i] = this.data[i - 1];
        }
        this.data[index] = data;
        size++;
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
    }

    @Override
    public MyArrayList sortIncreasing() {
        MyArrayList sorted = new MyArrayList();
        for (int i = 0; i < size; i++) {
            sorted.add(data[i]);
        }
        for (int i = 0; i < sorted.size - 1; i++) {
            for (int j = 0; j < sorted.size - i - 1; j++) {
                if (sorted.data[j] > sorted.data[j + 1]) {
                    double temp = sorted.data[j];
                    sorted.data[j] = sorted.data[j + 1];
                    sorted.data[j + 1] = temp;
                }
            }
        }
        return sorted;
    }

    @Override
    public int binarySearch(double data) {
        int left = 0;
        int right = size - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (this.data[mid] == data) {
                return mid;
            }
            if (this.data[mid] < data) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    /**
     * Tạo iterator để có thể duyệt qua các phần tử của list.
     * 
     * @return
     */
    @Override
    public MyIterator iterator(int start) {
        return new MyArrayListIterator(this, start);
    }

    public double get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return data[index];
    }

    /**
     * Cấp phát gấp đôi chỗ cho danh sách khi cần thiết.
     */
    private void allocateMore() {
        double[] newData = new double[data.length * 2];
        System.arraycopy(data, 0, newData, 0, data.length);
        data = newData;
    }

    private class MyArrayListIterator implements MyIterator {
        /**
         * Vị trí hiện tại của iterator trong MyArrayList.
         */
        private int currentPosition;
        private MyArrayList list;

        /**
         * Khởi tạo dữ liệu cho iterator tại vị trí position của list.
         */
        public MyArrayListIterator(MyArrayList list, int position) {
            if (position < 0 || position > list.size) {
                throw new IndexOutOfBoundsException("Position: " + position + ", Size: " + list.size);
            }
            this.list = list;
            this.currentPosition = position;
        }

        @Override
        public boolean hasNext() {
            return currentPosition < list.size;
        }

        @Override
        public Number next() {
            if (!hasNext()) {
                throw new IllegalStateException("No more elements");
            }
            return list.data[currentPosition++];
        }

        @Override
        public void remove() {
            if (currentPosition <= 0) {
                throw new IllegalStateException("No element to remove");
            }
            list.remove(currentPosition - 1);
            currentPosition--;
        }
    }
}

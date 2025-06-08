package hus.oop.statistics;

public class MyLinkedList extends MyAbstractList {
    private MyNode top;

    /**
     * Khởi tạo dữ liệu mặc định.
     */
    public MyLinkedList() {
        top = null;
    }

    @Override
    public int size() {
        int count = 0;
        MyNode current = top;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    @Override
    public void add(double data) {
        MyNode newNode = new MyNode(data);
        if (top == null) {
            top = newNode;
        } else {
            MyNode current = top;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
            newNode.previous = current;
        }
    }

    @Override
    public void insert(double data, int index) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
        }
        MyNode newNode = new MyNode(data);
        if (index == 0) {
            newNode.next = top;
            if (top != null) {
                top.previous = newNode;
            }
            top = newNode;
        } else {
            MyNode current = getNodeByIndex(index - 1);
            newNode.next = current.next;
            newNode.previous = current;
            if (current.next != null) {
                current.next.previous = newNode;
            }
            current.next = newNode;
        }
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
        }
        if (index == 0) {
            top = top.next;
            if (top != null) {
                top.previous = null;
            }
        } else {
            MyNode current = getNodeByIndex(index);
            current.previous.next = current.next;
            if (current.next != null) {
                current.next.previous = current.previous;
            }
        }
    }

    @Override
    public MyLinkedList sortIncreasing() {
        MyLinkedList sorted = new MyLinkedList();
        MyNode current = top;
        while (current != null) {
            sorted.add(current.data);
            current = current.next;
        }

        // Bubble sort
        boolean swapped;
        do {
            swapped = false;
            MyNode currentSorted = sorted.top;
            while (currentSorted.next != null) {
                if (currentSorted.data > currentSorted.next.data) {
                    double temp = currentSorted.data;
                    currentSorted.data = currentSorted.next.data;
                    currentSorted.next.data = temp;
                    swapped = true;
                }
                currentSorted = currentSorted.next;
            }
        } while (swapped);

        return sorted;
    }

    @Override
    public int binarySearch(double data) {
        MyLinkedList sorted = sortIncreasing();
        int left = 0;
        int right = sorted.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            MyNode midNode = sorted.getNodeByIndex(mid);
            if (midNode.data == data) {
                return mid;
            }
            if (midNode.data < data) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    /**
     * Tạo iterator để cho phép duyệt qua các phần tử của list.
     * 
     * @return
     */
    @Override
    public MyIterator iterator(int start) {
        return new MyLinkedListIterator(this, start);
    }

    /**
     * Lấy node ở vị trí index.
     * 
     * @param index
     * @return
     */
    private MyNode getNodeByIndex(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
        }
        MyNode current = top;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    /**
     * Lấy giá trị tại vị trí index.
     * 
     * @param index
     * @return giá trị tại vị trí index
     */
    public double get(int index) {
        return getNodeByIndex(index).data;
    }
}

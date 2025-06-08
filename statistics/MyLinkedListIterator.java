package hus.oop.statistics;

public class MyLinkedListIterator implements MyIterator {
    private MyLinkedList list;
    private int currentPosition;

    public MyLinkedListIterator(MyLinkedList list, int position) {
        this.list = list;
        this.currentPosition = position;
    }

    @Override
    public boolean hasNext() {
        return currentPosition < list.size();
    }

    @Override
    public Number next() {
        if (!hasNext())
            throw new IllegalStateException("No more elements");
        return list.get(currentPosition++);
    }

    @Override
    public void remove() {
        if (currentPosition <= 0)
            throw new IllegalStateException("No element to remove");
        list.remove(currentPosition - 1);
        currentPosition--;
    }
}
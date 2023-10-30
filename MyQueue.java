package ass2;

import java.util.NoSuchElementException;

public class MyQueue<T> implements QueueInterface<T>{
    private MiniList<T> list = new MiniList<T>();

    @Override
    public void enqueue(T item) {
        list.addLast(item);
    }

    @Override
    public T dequeue() throws NoSuchElementException {
        if(isEmpty()){
            throw new NoSuchElementException("the list is empty");
        }
        return list.removeFirst();
    }

    @Override
    public T front() throws NoSuchElementException {
        if(isEmpty()){
            throw new NoSuchElementException("the list is empty");
        }
        return list.getFirst();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
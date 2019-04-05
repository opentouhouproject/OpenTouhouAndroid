package com.scarlet.io.event;

public abstract class EventQueue<T> {
    // Queue size and capacity.
    protected static final int DEFAULT_CAPACITY = 256;
    private int capacity;
    private int size;

    // Tracking indices.
    private int head;
    private int tail;

    // Array backing the queue.
    protected T[] data;

    /*
     * Constructor(s).
     */
    public EventQueue() {
        head = 0;
        tail = 0;

        //data = new T[DEFAULT_CAPACITY];
        capacity = DEFAULT_CAPACITY;
        size = 0;
    }

    public synchronized boolean isEmpty() {
        return size == 0;
    }

    public synchronized boolean isFull() {
        return size == capacity;
    }

    public synchronized T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Attempted to dequeue an empty event queue!");
        }

        // Remove the item from the array.
        T result = data[head];
        data[head] = null;

        // Update the indices and size.
        head = (head + 1) % capacity;
        size--;

        return result;
    }

    public synchronized void enqueue(T item) {
        if (isFull()) {
            throw new RuntimeException("Attempted to enqueue a full event queue!");
        }

        // Add the item to the array.
        data[tail] = item;

        // Update indices and size.
        tail = (tail + 1) % capacity;
        size++;
    }

    @Override
    public String toString() {
        return "Event Queue.";
    }
}
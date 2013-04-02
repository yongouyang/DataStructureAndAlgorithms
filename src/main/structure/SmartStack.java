package structure;

import java.util.Stack;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SmartStack<E extends Comparable<E>> {

    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
    private ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

    private Stack<E> mins = new Stack<E>();
    private Stack<E> maxs = new Stack<E>();
    private Stack<E> delegate = new Stack<E>();

    public E push(E item) {
        writeLock.lock();
        try {
            // stores the current min to the mins stack
            if (mins.isEmpty() || (!mins.isEmpty() && item.compareTo(mins.peek()) < 0)) {
                mins.push(item);
            } else {
                mins.push(mins.peek());
            }

            // stores the current max to the maxs stack
            if (maxs.isEmpty() || (!maxs.isEmpty() && item.compareTo(maxs.peek()) > 0)) {
                maxs.push(item);
            } else {
                maxs.push(maxs.peek());
            }

            return delegate.push(item);
        } finally {
            writeLock.unlock();
        }
    }

    public E pop() {
        writeLock.lock();
        try {
            E item = delegate.pop();
            // pop both mins and maxs stacks, such that peeking on them will tell us the current min or max
            mins.pop();
            maxs.pop();
            return item;
        } finally {
            writeLock.unlock();
        }
    }

    public E peek() {
        readLock.lock();
        try {
            return delegate.peek();
        } finally {
            readLock.unlock();
        }
    }

    public E getMin() {
        readLock.lock();
        try {
            return mins.peek();
        } finally {
            readLock.unlock();
        }
    }

    public E getMax() {
        readLock.lock();
        try {
            return maxs.peek();
        } finally {
            readLock.unlock();
        }
    }

    public boolean empty() {
        readLock.lock();
        try {
            return delegate.empty();
        } finally {
            readLock.unlock();
        }
    }

    public int search(E item) {
        readLock.lock();
        try {
            return delegate.search(item);
        } finally {
            readLock.unlock();
        }
    }

}
package boot.apps.helper;

import java.util.concurrent.DelayQueue;

public class Pool<T extends AbstractDelayedWrapper> {

    public final DelayQueue<T> pool = new DelayQueue<T>();

    /**
     * Retrieves an available element of type T, waiting if necessary
     *
     * @return
     * @throws InterruptedException
     */
    public T get() throws InterruptedException {
        T element = null;
        element = pool.take();
        put(element);
        return element;
    }

    public void put(T element) {
        element.updateTime();
        if (pool.contains(element)) {
            pool.remove(element);
        }
        pool.put(element);
    }

    public boolean remove(T element) {
        return pool.remove(element);
    }

    public int size() {
        return pool.size();
    }

    @Override
    public String toString() {
        return "DelayedPool [pool=" + pool + "]";
    }

}
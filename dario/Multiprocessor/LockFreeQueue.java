/*
 * LockFreeQueue.java
 *
 * Created on January 21, 2006, 3:25 PM
 *
 * From "Multiprocessor Synchronization and Concurrent Data Structures",
 * by Maurice Herlihy and Nir Shavit.
 * Copyright 2006 Elsevier Inc. All rights reserved.
 */

package mutex;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

/**
 * Two-thread lock-free queue
 * @author Maurice Herlihy
 */
class LockFreeQueue {
  int head = 0;   // next item to dequeue
  int tail = 0;   // next empty slot
  Object[] items; // queue contents

  final Lock lock = new ReentrantLock();
  final Condition notFull  = lock.newCondition(); 
  final Condition notEmpty = lock.newCondition(); 

  public LockFreeQueue(int capacity) {
    head = 0; tail = 0;
    items = new Object[capacity];
  }

  public void enq(Object x) {
    lock.lock();
    try {
      // spin while full
      while (tail - head == items.length) 
        notFull.await();
      items[tail % items.length] = x;
      tail++;
      notEmpty.signal();
    }
    catch(InterruptedException e) {
      throw new RuntimeException(e);
    }
    finally {
      lock.unlock();
    }
  }

  public Object deq() {
    lock.lock();
    try {
      // spin while empty
      while (tail == head)
        notEmpty.await();
      Object x = items[head % items.length];
      head++;
      notFull.signal();
      return x;
    }
    catch(InterruptedException e) {
      throw new RuntimeException(e);
    }
    finally {
      lock.unlock();
    }
  }
}

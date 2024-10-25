package com.luoxin.customclassloader.test_future;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TestFuture {
    ThreadPoolExecutor executor =
      new ThreadPoolExecutor(
          4,
          8,
          60,
          TimeUnit.SECONDS,
          new ArrayBlockingQueue<>(16),
          new ThreadPoolExecutor.AbortPolicy());
  public static void main(String[] args){
      TestFuture test = new TestFuture();
      Future<Integer> future = test.executor.submit(new Callable<Integer>() {
          @Override
          public Integer call() throws Exception {
              return 1;
          }
      });
//      future.get()
      AtomicInteger x = new AtomicInteger();
      x.getAndIncrement();
  }
}

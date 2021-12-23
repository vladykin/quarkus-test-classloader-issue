package com.acme;

import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.mutiny.Uni;
import java.util.concurrent.CompletableFuture;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

@Order(1)
@QuarkusTest
public class QTest {

  @Test
  void test() {
    CompletableFuture.runAsync(() -> {
      System.err.printf("Thread = %s, context classloader = %s%n",
          Thread.currentThread().getName(), Thread.currentThread().getContextClassLoader());

      Uni.createFrom().nullItem()
          .onItem().invoke(() -> {
            // just a callback to be decorated, no matter what code is here
          })
          .await().indefinitely();
    }).join();
  }
}

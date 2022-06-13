package java.net;

import java.util.Arrays;

/**
 * This is some comment
 */
public class MikeLogger {
  /**
   * another comment
   * @param data asdfa
   * @param args asdfasdfjas dfasdfj
   */
  public static void log(String data, Object...args) {
    if (shouldLog(Thread.currentThread().getName())) {
      StackTraceElement[] stack = Thread.currentThread().getStackTrace();

      StackTraceElement caller = getCaller(stack);
      String[] parts = caller.getClassName().split("\\.");
      String className = parts[parts.length - 1];

      System.out.println(String.format("[%s] %s.%s:%s %s",
          Thread.currentThread().getName(), className, caller.getMethodName(), caller.getLineNumber(),
          String.format(data, args)));
    }
  }

  /**
   * this is another comment
   * @param threadName
   * @return
   */
  private static boolean shouldLog(String threadName) {
    if (!Boolean.getBoolean("mike.logger.enabled")) {
      return false;
    }
    for (String prefix : Arrays.asList("main", "IPC")) {
      if (threadName.startsWith(prefix)) {
        return true;
      }
    }
    return false;
  }

  private static StackTraceElement getCaller(StackTraceElement[] stack) {
    for (StackTraceElement element : stack) {
      if (!element.getClassName().contains("MikeLogger") &&
          !element.getClassName().contains("Thread")) {
        return element;
      }
    }
    return stack[0];
  }
}

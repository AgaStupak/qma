package pl.com.bottega.qma.fun;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

class NonEmptyList<T> implements FunList<T> {

  private final T head;

  private final FunList<T> tail;

  public NonEmptyList(T head) {
    this(head, EmptyList.INSTANCE);
  }

  public NonEmptyList(T head, FunList<T> tail) {
    this.head = head;
    this.tail = tail;
  }

  @Override
  public FunList<T> add(T el) {
    return new NonEmptyList<>(head, tail.add(el));
  }

  @Override
  public FunList<T> remove(T el) {
    if(head.equals(el)) {
      return tail;
    }
    return new NonEmptyList<>(head, tail.remove(el));
  }

  @Override
  public boolean contains(T el) {
    return head.equals(el) || tail.contains(el);
  }

  @Override
  public int size() {
    return 1 + tail.size();
  }

  @Override
  public T get(int index) {
    return null;
  }

  @Override
  public FunList<T> forEach(Consumer<T> consumer) {
    return null;
  }

  @Override
  public <R> FunList<R> map(Function<T, R> mapper) {
    return null;
  }

  @Override
  public <U> U foldLeft(U unit, BiFunction<U, T, U> folder) {
    return null;
  }
}

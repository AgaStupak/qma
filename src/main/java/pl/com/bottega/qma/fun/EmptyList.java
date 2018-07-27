package pl.com.bottega.qma.fun;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

class EmptyList<T> implements FunList<T> {

  static final FunList INSTANCE = new EmptyList();

  private EmptyList() {}

  @Override
  public FunList<T> add(T el) {
    return new NonEmptyList<>(el);
  }

  @Override
  public FunList<T> remove(T el) {
    return this;
  }

  @Override
  public boolean contains(T el) {
    return false;
  }

  @Override
  public int size() {
    return 0;
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

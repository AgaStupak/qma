package pl.com.bottega.qma.fun;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class EmptyList<T> implements List<T> {

  static final EmptyList INSTANCE = new EmptyList();

  private EmptyList() {
  }

  @Override
  public List<T> add(T t) {
    return new NonEmptyList<>(t);
  }

  @Override
  public List<T> remove(T t) {
    return null;
  }

  @Override
  public T get(int index) {
    throw new ArrayIndexOutOfBoundsException();
  }

  @Override
  public int size() {
    return 0;
  }

  @Override
  public List<T> insertAt(int index, T newElement) {
    return null;
  }

  @Override
  public List<T> filter(Predicate<T> predicate) {
    return this;
  }

  @Override
  public List<T> addAll(T... t) {
    return null;
  }

  @Override
  public List<T> concat(List<T> otherList) {
    return otherList;
  }

  @Override
  public <R> List<R> map(Function<T, R> mapper) {
    return (List<R>) this;
  }

  @Override
  public Optional<T> reduce(BiFunction<T, T, T> reducer) {
    return Optional.empty();
  }

  @Override
  public <R> Optional<R> reduce(R initial, BiFunction<R, T, R> reducer) {
    return Optional.empty();
  }
}

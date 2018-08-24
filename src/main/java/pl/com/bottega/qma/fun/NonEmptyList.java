package pl.com.bottega.qma.fun;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class NonEmptyList<T> implements List<T> {

  private final T cons;
  private final List<T> tail;

  NonEmptyList(T cons) {
    this(cons, EmptyList.INSTANCE);
  }

  NonEmptyList(T cons, List<T> tail) {
    this.cons = cons;
    this.tail = tail;
  }

  @Override
  public List<T> add(T t) {
    return new NonEmptyList<>(cons, tail.add(t));
  }

  @Override
  public List<T> remove(T t) {
    return null;
  }

  @Override
  public T get(int index) {
    if (index == 0) {
      return cons;
    } else {
      return tail.get(index - 1);
    }
  }

  @Override
  public int size() {
    return 1 + tail.size();
  }

  @Override
  public List<T> insertAt(int index, T newElement) {
    return null;
  }

  @Override
  public List<T> filter(Predicate<T> predicate) {
    return null;
  }

  @Override
  public List<T> addAll(T... t) {
    return null;
  }

  @Override
  public List<T> concat(List<T> otherList) {
    return null;
  }

  @Override
  public <R> List<R> map(Function<T, R> mapper) {
    return null;
  }

  @Override
  public Optional<T> reduce(BiFunction<T, T, T> reducer) {
    var tailReduced = tail.reduce(reducer);
    if (tailReduced.isPresent())
      return Optional.of(reducer.apply(cons, tailReduced.get()));
    else
      return Optional.of(cons);
  }

  @Override
  public <R> Optional<R> reduce(R initial, BiFunction<R, T, R> reducer) {
    return Optional.empty();
  }
}

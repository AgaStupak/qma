package pl.com.bottega.qma.fun;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public interface List<T> {

  static <R> List<R> empty(Class<R> elementClass) {
    return EmptyList.INSTANCE;
  }

  List<T> add(T t);

  List<T> remove(T t);

  T get(int index);

  int size();

  List<T> insertAt(int index, T newElement);

  List<T> filter(Predicate<T> predicate);

  List<T> addAll(T... t);

  List<T> concat(List<T> otherList);

  <R> List<R> map(Function<T, R> mapper);

  Optional<T> reduce(BiFunction<T, T, T> reducer);

  <R> Optional<R> reduce(R initial, BiFunction<R, T, R> reducer);

}

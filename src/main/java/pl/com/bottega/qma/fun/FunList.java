package pl.com.bottega.qma.fun;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public interface FunList<T> {

  FunList<T> add(T el);

  FunList<T> remove(T el);

  boolean contains(T el);

  int size();

  T get(int index);

  FunList<T> forEach(Consumer<T> consumer);

  <R> FunList<R> map(Function<T, R> mapper);

  <U> U foldLeft(U unit, BiFunction<U, T, U> folder);

  static <R> FunList<R> empty(Class<R> klass) {
    return EmptyList.INSTANCE;
  }

}

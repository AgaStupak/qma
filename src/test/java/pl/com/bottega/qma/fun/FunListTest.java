package pl.com.bottega.qma.fun;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class FunListTest {

  @Test
  public void addsNewElementToList() {
    FunList<String> l = FunList.empty(String.class);

    l = l.add("test");

    assertThat(l.size()).isEqualTo(1);
    assertThat(l.contains("test")).isTrue();
  }

  @Test
  public void addsManyElements() {
    FunList<String> l = FunList.empty(String.class);

    l = l.add("1").add("2").add("3");

    assertThat(l.size()).isEqualTo(3);
    assertThat(l.contains("1")).isTrue();
    assertThat(l.contains("2")).isTrue();
    assertThat(l.contains("3")).isTrue();
  }

  @Test
  public void removesElementsFromEmptyList() {
    FunList<String> l = FunList.empty(String.class);

    FunList<String> removed = l.remove("1");

    assertThat(removed).isEqualTo(l);
  }

  @Test
  public void removesFirstElement() {
    FunList<String> l = FunList.empty(String.class).add("1").add("2");

    l = l.remove("1");

    assertThat(l.size()).isEqualTo(1);
    assertThat(l.contains("1")).isFalse();
  }

  @Test
  public void removesElementFromTheMiddle() {
    FunList<String> l = FunList.empty(String.class).add("1").add("2").add("3");

    l = l.remove("2");

    assertThat(l.size()).isEqualTo(2);
    assertThat(l.contains("2")).isFalse();
  }

}

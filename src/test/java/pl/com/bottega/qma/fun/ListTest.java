package pl.com.bottega.qma.fun;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class ListTest {

  @Test
  public void addsElementsToList() {
    //given
    var l = List.empty(String.class);

    // when
    var newList = l.add("1").add("2").add("3");

    // then
    assertThat(newList.size()).isEqualTo(3);
    assertThat(l.size()).isEqualTo(0);
  }

  @Test
  public void getsElementByIndex() {
    //given
    var l = List.empty(String.class);

    // when
    var newList = l.add("1").add("2").add("3");

    // then
    assertThat(newList.get(0)).isEqualTo("1");
    assertThat(newList.get(1)).isEqualTo("2");
    assertThat(newList.get(2)).isEqualTo("3");
  }

  @Test
  public void reducesElements() {
    //given
    var l = List.empty(String.class);

    // when
    var newList = l.add("1").add("2").add("3");
    Optional<String> reduced = newList.reduce((acc, s) -> acc.concat(s));

    // then
    assertThat(reduced.get()).isEqualTo("123");
  }

}

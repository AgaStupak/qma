package pl.com.bottega.qma.core.events;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class DefaultEventEngineTest {

  private final DefaultEventEngine defaultEventEngine = new DefaultEventEngine();

  @Test
  public void notifiesListener() {
    // given
    var listener = mock(EventListener.class);
    defaultEventEngine.subscribe(Long.class, listener);

    // when
    defaultEventEngine.publish(1L);

    // then
    verify(listener).onEvent(1L);
  }

  @Test
  public void notifiesManyListeners() {
    // given
    var longListener = mock(EventListener.class);
    var otherLongListener = mock(EventListener.class);
    defaultEventEngine.subscribe(Long.class, longListener);
    defaultEventEngine.subscribe(Long.class, otherLongListener);

    // when
    defaultEventEngine.publish(1L);

    // then
    verify(longListener).onEvent(1L);
    verify(otherLongListener).onEvent(1L);
  }

  @Test
  public void notifiesListenersBasedOnEventType() {
    var longListener = mock(EventListener.class);
    var stringListener = mock(EventListener.class);
    defaultEventEngine.subscribe(Long.class, longListener);
    defaultEventEngine.subscribe(String.class, stringListener);

    defaultEventEngine.publish(1L);
    defaultEventEngine.publish("test");

    verify(longListener).onEvent(1L);
    verify(stringListener).onEvent("test");
  }


}

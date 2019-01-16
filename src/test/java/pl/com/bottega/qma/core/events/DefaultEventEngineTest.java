package pl.com.bottega.qma.core.events;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class DefaultEventEngineTest {

  private final DefaultEventEngine defaultEventEngine = new DefaultEventEngine();

  @Test
  public void notifiesListener() {
    EventListener listener = mock(EventListener.class);
    defaultEventEngine.subscribe(Long.class, listener);

    defaultEventEngine.publish(1L);

    verify(listener).onEvent(1L);
  }

  @Test
  public void notifiesManyListeners() {
    EventListener longListener = mock(EventListener.class);
    EventListener otherLongListener = mock(EventListener.class);
    defaultEventEngine.subscribe(Long.class, longListener);
    defaultEventEngine.subscribe(Long.class, otherLongListener);

    defaultEventEngine.publish(1L);

    verify(longListener).onEvent(1L);
    verify(otherLongListener).onEvent(1L);
  }

  @Test
  public void notifiesListenersBasedOnEventType() {
    EventListener longListener = mock(EventListener.class);
    EventListener stringListener = mock(EventListener.class);
    defaultEventEngine.subscribe(Long.class, longListener);
    defaultEventEngine.subscribe(String.class, stringListener);

    defaultEventEngine.publish(1L);
    defaultEventEngine.publish("test");

    verify(longListener).onEvent(1L);
    verify(stringListener).onEvent("test");
  }


}

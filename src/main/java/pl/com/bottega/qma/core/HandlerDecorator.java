package pl.com.bottega.qma.core;

public abstract class HandlerDecorator<CommandT extends Command, ReturnT>
    implements Handler<CommandT, ReturnT> {

  protected final Handler<CommandT, ReturnT> decorated;

  public HandlerDecorator(Handler<CommandT, ReturnT> decorated) {
    this.decorated = decorated;
  }

}

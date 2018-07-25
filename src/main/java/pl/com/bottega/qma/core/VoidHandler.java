package pl.com.bottega.qma.core;

public abstract class VoidHandler<CommandT extends Command> implements Handler<CommandT, Void> {

  protected abstract void voidHandle(CommandT commandT);

  @Override
  public Void handle(CommandT commandT) {
    voidHandle(commandT);

    return null;
  }


}

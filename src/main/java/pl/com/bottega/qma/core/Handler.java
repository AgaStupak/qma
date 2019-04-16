package pl.com.bottega.qma.core;

public interface Handler<CommandT extends Command> {

  void handle(CommandT command);

}

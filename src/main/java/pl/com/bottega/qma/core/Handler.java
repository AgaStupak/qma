package pl.com.bottega.qma.core;

@FunctionalInterface
public interface Handler<CommandT extends Command, ReturnT> {

  ReturnT handle(CommandT commandT);

}

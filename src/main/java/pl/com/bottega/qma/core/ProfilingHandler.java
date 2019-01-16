package pl.com.bottega.qma.core;

class ProfilingHandler<CommandT extends Command, ReturnT> extends HandlerDecorator<CommandT, ReturnT> {

  protected ProfilingHandler(Handler<CommandT, ReturnT> decoratedHandler) {
    super(decoratedHandler);
  }

  @Override
  public ReturnT handle(CommandT command) {
    Profiler profiler = new Profiler(command, this);
    ReturnT retVal = decoratedHandler.handle(command);
    profiler.finished();
    return retVal;
  }
}

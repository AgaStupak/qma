package pl.com.bottega.qma.core;

public class SecuredHandler<CommandT extends Command, ReturnT>
    extends HandlerDecorator<CommandT, ReturnT> {

  private final SecurityManager securityManager;
  private final Handler<CommandT, ReturnT> baseHandler;

  public SecuredHandler(
      Handler<CommandT, ReturnT> decorated,
      SecurityManager securityManager,
      Handler<CommandT, ReturnT> baseHandler
      ) {
    super(decorated);
    this.securityManager = securityManager;
    this.baseHandler = baseHandler;
  }

  @Override
  public ReturnT handle(CommandT command) {
    securityManager.checkSecurity(command, baseHandler);
    return decorated.handle(command);
  }
}

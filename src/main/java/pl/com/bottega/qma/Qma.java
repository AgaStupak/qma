package pl.com.bottega.qma;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import pl.com.bottega.qma.confirmation.ConfirmationRepository;
import pl.com.bottega.qma.confirmation.DefaultDocflowFacade;
import pl.com.bottega.qma.confirmation.DocflowFacade;
import pl.com.bottega.qma.confirmation.InMemoryConfirmationRepository;
import pl.com.bottega.qma.confirmation.commands.ConfirmDocumentCommand;
import pl.com.bottega.qma.confirmation.commands.ConfirmDocumentOnBehalfCommand;
import pl.com.bottega.qma.confirmation.handlers.ConfirmDocumentHandler;
import pl.com.bottega.qma.core.CommandGateway;
import pl.com.bottega.qma.core.CommandLogger;
import pl.com.bottega.qma.core.SecurityManager;
import pl.com.bottega.qma.core.TxManager;
import pl.com.bottega.qma.core.events.DefaultEventEngine;
import pl.com.bottega.qma.core.validation.ValidationEngine;
import pl.com.bottega.qma.docflow.DocumentFactory;
import pl.com.bottega.qma.docflow.DocumentRepository;
import pl.com.bottega.qma.docflow.DocumentResource;
import pl.com.bottega.qma.docflow.InMemoryDocumentRepository;
import pl.com.bottega.qma.docflow.commands.ArchiveDocumentCommand;
import pl.com.bottega.qma.docflow.commands.CreateDocumentCommand;
import pl.com.bottega.qma.docflow.commands.EditDocumentCommand;
import pl.com.bottega.qma.docflow.commands.PublishDocumentCommand;
import pl.com.bottega.qma.docflow.commands.VerifyDocumentCommand;
import pl.com.bottega.qma.docflow.handlers.ArchiveDocumentHandler;
import pl.com.bottega.qma.docflow.handlers.CreateDocumentHandler;
import pl.com.bottega.qma.docflow.handlers.EditDocumentHandler;
import pl.com.bottega.qma.docflow.handlers.PublishDocumentHandler;
import pl.com.bottega.qma.docflow.handlers.VerifyDocumentHandler;
import pl.com.bottega.qma.docflow.numbergenerators.ISONumberGenerator;
import pl.com.bottega.qma.hr.HrFacade;
import pl.com.bottega.qma.hr.RemoteHrFacade;
import pl.com.bottega.qma.notifications.DocumentPublishedListener;

public class Qma extends Application<QmaConfiguration> {

    private CommandLogger commandLogger;
    private SecurityManager securityManager;
    private TxManager txManager;
    private ValidationEngine validationEngine;
    private DocumentRepository documentRepository;
    private DefaultEventEngine defaultEventEngine;
    private ConfirmationRepository confirmationRepository;
    private HrFacade hrFacade;
    private DocflowFacade docflowFacade;
    private ConfirmDocumentHandler confirmDocumentHandler;
    private CreateDocumentHandler createDocumentHandler;
    private EditDocumentHandler editDocumentHandler;
    private VerifyDocumentHandler verifyDocumentHandler;
    private PublishDocumentHandler publishDocumentHandler;
    private ArchiveDocumentHandler archiveDocumentHandler;
    private CommandGateway commandGateway;
    private DocumentPublishedListener documentPublishedListener;

    public static void main(String[] args) throws Exception {
        new Qma().run(args);
    }

    @Override
    public String getName() {
        return "qma";
    }

    private void instantiateComponents() {
        commandLogger = new CommandLogger();
        securityManager = new SecurityManager();
        txManager = new TxManager();
        validationEngine = new ValidationEngine();
        documentRepository = new InMemoryDocumentRepository();
        defaultEventEngine = new DefaultEventEngine();
        confirmationRepository = new InMemoryConfirmationRepository();
        hrFacade = new RemoteHrFacade();
        docflowFacade = new DefaultDocflowFacade(documentRepository, hrFacade);
        confirmDocumentHandler = new ConfirmDocumentHandler(confirmationRepository, docflowFacade);
        createDocumentHandler = new CreateDocumentHandler(documentRepository, new DocumentFactory(new ISONumberGenerator(), defaultEventEngine));
        editDocumentHandler = new EditDocumentHandler(documentRepository);
        verifyDocumentHandler = new VerifyDocumentHandler(documentRepository);
        publishDocumentHandler = new PublishDocumentHandler(documentRepository);
        archiveDocumentHandler = new ArchiveDocumentHandler(documentRepository);
        commandGateway = new CommandGateway(commandLogger, securityManager, txManager, validationEngine);
        commandGateway.registerHandler(CreateDocumentCommand.class, createDocumentHandler);
        commandGateway.registerHandler(EditDocumentCommand.class, editDocumentHandler);
        commandGateway.registerHandler(VerifyDocumentCommand.class, verifyDocumentHandler);
        commandGateway.registerHandler(PublishDocumentCommand.class, publishDocumentHandler);
        commandGateway.registerHandler(ArchiveDocumentCommand.class, archiveDocumentHandler);
        commandGateway.registerHandler(ConfirmDocumentCommand.class, confirmDocumentHandler::confirm);
        commandGateway.registerHandler(ConfirmDocumentOnBehalfCommand.class, confirmDocumentHandler::confirmOnBehalf);
        documentPublishedListener = new DocumentPublishedListener(defaultEventEngine);
    }

    @Override
    public void initialize(Bootstrap<QmaConfiguration> bootstrap) {

    }

    @Override
    public void run(QmaConfiguration qmaConfiguration, Environment environment) throws Exception {
        instantiateComponents();
        environment.jersey().register(new DocumentResource(commandGateway));
    }

}

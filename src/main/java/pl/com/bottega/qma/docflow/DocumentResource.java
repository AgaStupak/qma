package pl.com.bottega.qma.docflow;

import pl.com.bottega.qma.core.CommandGateway;
import pl.com.bottega.qma.docflow.commands.CreateDocumentCommand;
import pl.com.bottega.qma.docflow.commands.EditDocumentCommand;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/documents")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DocumentResource {

  private CommandGateway commandGateway;

  public DocumentResource(CommandGateway commandGateway) {
    this.commandGateway = commandGateway;
  }

  @POST
  public CreateDocumentResponse create(CreateDocumentCommand command) {
    String documentNumber = commandGateway.execute(command);
    CreateDocumentResponse response = new CreateDocumentResponse(documentNumber);
    return response;
  }

  @PUT
  @Path("/{number}")
  public Response edit(@PathParam("number") String number, EditDocumentCommand command) {
    command.documentNumber = number;
    commandGateway.execute(command);
    return Response.ok().build();
  }

  private class CreateDocumentResponse {

    public String number;

    public CreateDocumentResponse(String documentNumber) {
      number = documentNumber;
    }
  }
}

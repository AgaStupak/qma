package pl.com.bottega.qma.e2e;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.com.bottega.qma.core.CommandGateway;
import pl.com.bottega.qma.docflow.Document;
import pl.com.bottega.qma.docflow.DocumentRepository;
import pl.com.bottega.qma.docflow.DocumentStatus;
import pl.com.bottega.qma.docflow.commands.CreateDocumentCommand;
import pl.com.bottega.qma.docflow.commands.EditDocumentCommand;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class DocflowTest {

  @Autowired
  private CommandGateway commandGateway;

  @Autowired
  private DocumentRepository documentRepository;

  @Test
  public void createsDocument() {
    CreateDocumentCommand createDocumentCommand = new CreateDocumentCommand();
    createDocumentCommand.creatorId = 1L;

    String nr = commandGateway.execute(createDocumentCommand);

    Document document = documentRepository.get(nr);
    assertThat(document).isNotNull();
    assertThat(document.number()).isEqualTo(nr);
  }

  @Test
  public void editsDocument() {
    // given
    CreateDocumentCommand createDocumentCommand = new CreateDocumentCommand();
    createDocumentCommand.creatorId = 1L;
    String nr = commandGateway.execute(createDocumentCommand);

    //when
    EditDocumentCommand editDocumentCommand = new EditDocumentCommand();
    editDocumentCommand.editorId = 2L;
    editDocumentCommand.documentNumber = nr;
    editDocumentCommand.title = Optional.of("test");
    commandGateway.execute(editDocumentCommand);

    // then
    Document document = documentRepository.get(nr);
    assertThat(document.status()).isEqualTo(DocumentStatus.DRAFT);
    assertThat(document.title()).isEqualTo("test");
    assertThat(document.editorId()).isEqualTo(2L);
  }

}

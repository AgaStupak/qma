package pl.com.bottega.qma.docflow.adapters.db;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import pl.com.bottega.qma.docflow.domain.Document;

public interface SpringDataDocumentRepository extends ReactiveMongoRepository<Document, String> {

}

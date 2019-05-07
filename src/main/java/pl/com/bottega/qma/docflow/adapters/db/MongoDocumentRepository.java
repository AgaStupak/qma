package pl.com.bottega.qma.docflow.adapters.db;

import pl.com.bottega.qma.core.events.EventPublisher;
import pl.com.bottega.qma.docflow.Document;
import pl.com.bottega.qma.docflow.DocumentRepository;
import pl.com.bottega.qma.docflow.events.DocumentCreated;

import java.time.LocalDateTime;
import java.util.List;

public class MongoDocumentRepository implements DocumentRepository {

    private final SpringDataMongoDocumentRepository repository;
    private final EventPublisher eventPublisher;

    public MongoDocumentRepository(SpringDataMongoDocumentRepository repository, EventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Document get(String number) throws DocumentNotFoundException {
        DocumentMongoDto dto = repository.findByNumber(number);
        DocumentCreated documentCreated = new DocumentCreated(dto.number, dto.creatorId, dto.timestamp);
        return new Document(List.of(documentCreated), eventPublisher);
    }

    @Override
    public void put(Document document) {
        DocumentMongoDto dto = new DocumentMongoDto();
        dto.number = document.number();
        dto.creatorId = document.creatorId();
        dto.timestamp = LocalDateTime.now();
        this.repository.save(dto);
    }
}

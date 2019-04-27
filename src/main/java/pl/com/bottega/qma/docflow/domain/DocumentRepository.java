package pl.com.bottega.qma.docflow.domain;

import reactor.core.publisher.Mono;

public interface DocumentRepository {
    Mono<Void> save(Document document);

    Mono<Document> get(String documentNumber);
}

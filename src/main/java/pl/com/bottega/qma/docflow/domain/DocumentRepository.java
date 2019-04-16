package pl.com.bottega.qma.docflow.domain;

public interface DocumentRepository {
    void save(Document document);

    Document get(String documentNumber);
}

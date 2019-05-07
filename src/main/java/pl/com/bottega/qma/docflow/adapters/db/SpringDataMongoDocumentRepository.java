package pl.com.bottega.qma.docflow.adapters.db;

import org.springframework.data.repository.Repository;

public interface SpringDataMongoDocumentRepository extends Repository<DocumentMongoDto, String> {

    void save(DocumentMongoDto dto);

    DocumentMongoDto findByNumber(String number);
}

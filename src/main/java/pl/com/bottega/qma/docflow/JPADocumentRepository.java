package pl.com.bottega.qma.docflow;

import pl.com.bottega.qma.core.jpa.EntityManagerProvider;

public class JPADocumentRepository implements DocumentRepository {

    private final EntityManagerProvider provider;

    public JPADocumentRepository(EntityManagerProvider provider) {
        this.provider = provider;
    }

    @Override
    public Document get(String number) throws DocumentNotFoundException {
        Document document = provider.getEntityManager().find(Document.class, number);
        if(document == null) {
            throw new DocumentNotFoundException(number);
        }
        return document;
    }

    @Override
    public void put(Document document) {
        provider.getEntityManager().persist(document);
    }
}

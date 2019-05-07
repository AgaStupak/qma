package pl.com.bottega.qma.docflow.adapters.db;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
public class DocumentMongoDto {

    @Id
    public String number;

    public Long creatorId;
    public LocalDateTime timestamp;
}

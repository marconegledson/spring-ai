package br.com.example.springia.service;

import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.JsonMetadataGenerator;
import org.springframework.ai.reader.JsonReader;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlagService {

  private final VectorStore vectorStore;
  private final Resource resource;

  public void add() {
    //get json content
    JsonReader jsonReader = new JsonReader(resource, new MetadataGenerator(),
        "country",
        "flag_colors",
        "description",
        "created"
    );

    // create a list with json objets
    List<Document> documents = jsonReader.get();

    //save database
    vectorStore.add(documents);
  }

  public List<Document> find(String query) {
    return vectorStore.similaritySearch(SearchRequest
        .defaults()
        .withQuery(query)
        .withTopK(1) // quandos registros retornar
        .withSimilarityThreshold(0.8) // 0 ate 1: quao preciso será a busca no caso 80%
    );

  }

  public class MetadataGenerator implements JsonMetadataGenerator {

    @Override
    public Map<String, Object> generate(Map<String, Object> jsonMap) {
      return Map.of(
          "country", jsonMap.get("country"),
          "flagColors", jsonMap.get("flag_colors"),
          "description", jsonMap.get("description"),
          "created", jsonMap.get("country")
      );
    }
  }

}

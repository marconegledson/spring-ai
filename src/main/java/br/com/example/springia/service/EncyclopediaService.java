package br.com.example.springia.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EncyclopediaService {

  private final VectorStore vectorStore;

  public void add() {
    var documents = List.of(
        new Document("Nas noites tranquilas, as estrelas brilham intensamente no céu"),
        new Document("O vento sussurra segredos entre as folhas das árvores antigas"),
        new Document("A chuva suave acalma a terra sedenta em dias de verão"),
        new Document("As crianças brincam felizes sob o sol dourado da tarde"),
        new Document("O mar agitado dança ao ritmo das ondas quebrando na praia"),
        new Document("O amor verdadeiro transcende as fronteiras do tempo e do espaço"),
        new Document("Os pássaros cantam melodias alegres ao amanhecer da manhã")
    );

    //persiste os documentos
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

}

package br.com.example.springia.config;

import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

@Configuration
public class VectorStoreConfig {

  @Bean
  VectorStore vectorStore(EmbeddingClient embeddingClient) {
    return new SimpleVectorStore(embeddingClient);
  }

  @Bean
  Resource resource(ResourceLoader resourceLoader) {
    return resourceLoader.getResource("/Users/marconealmeida/Workspace/git/spring-ia/src/main/resources/data/flags.json");
  }

}

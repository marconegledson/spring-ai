package br.com.example.springia.config;

import lombok.SneakyThrows;
import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class VectorStoreConfig {

  @Bean
  VectorStore vectorStore(EmbeddingClient embeddingClient) {
    return new SimpleVectorStore(embeddingClient);
  }

  @Bean
  public RestTemplate restTemplate() {
    var template = new RestTemplate();
    template.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
    return template;
  }

  @Bean
  Resource resource(ResourceLoader resourceLoader) {
    return new ClassPathResource("data/flags.json");
  }

}

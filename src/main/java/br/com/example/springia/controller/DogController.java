package br.com.example.springia.controller;

import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.ai.parser.BeanOutputParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/dogs")
public class DogController {

  private final OpenAiChatClient openAiChatClient;


  //utilizando o seu proprio modelo de json
  @GetMapping("/famous")
  public DogDetail getListFamousDogs(
      @RequestParam(name = "pais", defaultValue = "Brasil") String pais) {

    BeanOutputParser<DogDetail> outputParser= new BeanOutputParser<>(DogDetail.class);

    var promptTemplate = new PromptTemplate("""
        Qual a raça de cão mais famosa no {pais}. A resposta deve contar esta informação:
        raca, quantidade e preço
        {format}
        """);

    promptTemplate.add("pais", pais);
    promptTemplate.add("format", outputParser.getFormat());
    promptTemplate.setOutputParser(outputParser);

    ChatResponse response =  openAiChatClient.call(promptTemplate.create());
    return outputParser.parse(response.getResult().getOutput().getContent());
  }

  public record DogDetail (
      String raca,
      Integer quantidade,
      BigDecimal preco
  ){}

}

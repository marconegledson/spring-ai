package br.com.example.springia.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/books")
public class BookController {

  private final OpenAiChatClient openAiChatClient;
  //private final VertexAiGeminiChatClient vertexAiGeminiChatClient;

  //retorno em string
  @GetMapping("/informations")
  public String informations(@RequestParam(name = "message",
      defaultValue = "Quais os livros mais vendidos nos últimos 5 anos?") String message) {
//    return vertexAiGeminiChatClient.call(message);
    return openAiChatClient.call(message);
  }

  //retorno em json no modelo chatresponse
  @GetMapping("/review")
  public ChatResponse review(@RequestParam(name = "message",
      defaultValue = "Me forneça um resumo sobre o livro 'O problema dos 3 corpos'") String message) {
    return openAiChatClient.call(new Prompt(message));
  }

}

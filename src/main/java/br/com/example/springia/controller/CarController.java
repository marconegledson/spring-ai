package br.com.example.springia.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/cars")
public class CarController {

  private final OpenAiChatClient openAiChatClient;

  //flux com string
  @GetMapping("/info-new-car")
  public Flux<String> getInfoNewCar(@RequestParam(name = "message",
      defaultValue =
          "Quanto de dinheiro eu devo economizar por mês para "
              + "comprar um carro zero quilômetro de 60 mil reais em 3 anos?") String message) {

    return openAiChatClient.stream(message);
  }

  //flux com chatresponse
  @GetMapping("/info-fees")
  public Flux<ChatResponse> getInfoFees(@RequestParam(name = "message",
      defaultValue =
          "Tenho um financiamento de carro que custa "
              + "R$ 10.000,00 eu pago parcelas de R$ 4.456,20 em 60x. "
              + "Quanto eu pago de juros?") String message) {

    return openAiChatClient.stream(new Prompt(message));
  }

  //uso de template
  @GetMapping("/info-installments")
  public String getInfoInstallments(
      @RequestParam(name = "valor", defaultValue = "100.000") String valor,
      @RequestParam(name = "taxa", defaultValue = "1,7") String taxa) {

    var promptTemplate = new PromptTemplate("""
        Qual o valor das parcelas de um carro com financiamento 
        de R$ {valor} pagamento uma taxa de juros de {taxa}?
        """);

    promptTemplate.add("valor", valor);
    promptTemplate.add("taxa", taxa);

    return openAiChatClient.call(promptTemplate.create()).getResult().getOutput().getContent();
  }

}

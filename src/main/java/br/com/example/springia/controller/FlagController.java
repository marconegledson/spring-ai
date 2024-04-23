package br.com.example.springia.controller;

import br.com.example.springia.service.FlagService;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/flags")
public class FlagController {

  private final FlagService service;

  @GetMapping
  public String getSimilarQuery(@RequestParam(name = "query", defaultValue = "Brazil") String query) {
    service.add();
    return service.find(query).get(0).getMetadata().toString(); // retorna um json invalido // sem os "
  }


}

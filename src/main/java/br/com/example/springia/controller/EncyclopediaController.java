package br.com.example.springia.controller;

import br.com.example.springia.service.EncyclopediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/encyclopedia")
public class EncyclopediaController {

  private final EncyclopediaService service;

  @GetMapping
  public String getSimilarQuery(@RequestParam(name = "query", defaultValue = "") String query) {
    service.add();
    return service.find(query).get(0).getContent();
  }

}

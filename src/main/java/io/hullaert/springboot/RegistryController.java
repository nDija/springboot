package io.hullaert.springboot;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin
public class RegistryController {

  private final FcmClient fcmClient;

  public RegistryController(FcmClient fcmClient) {
    this.fcmClient = fcmClient;
  }

  @PostMapping("/register")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public Mono<Void> register(@RequestBody Mono<String> token) {
    return token.doOnNext(t -> this.fcmClient.subscribe("chuck", t)).then();
  }

}

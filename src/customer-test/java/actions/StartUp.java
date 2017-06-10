package actions;

import static net.serenitybdd.screenplay.Tasks.instrumented;

import java.util.concurrent.TimeUnit;

import org.awaitility.Awaitility;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Performable;
import net.thucydides.core.annotations.Step;

import com.remonsinnema.awe.AweApplication;


public class StartUp implements Interaction {

  public static final String URL = "http://localhost:8080/";

  @Override
  @Step("{0} starts up the server")
  public <T extends Actor> void performAs(T actor) {
    Thread serverThread = new Thread(() -> AweApplication.main(new String[] { "--spring.profiles.active=test" }));
    serverThread.setDaemon(true);
    serverThread.start();
    waitForServer();
  }

  private void waitForServer() {
    RestTemplate template = new RestTemplate();
    Awaitility.await().atMost(60, TimeUnit.SECONDS).until(() -> isStarted(template));
  }

  private boolean isStarted(RestTemplate template) {
    try {
      ResponseEntity<String> response = template.getForEntity(URL, String.class);
      return response.getStatusCode().is2xxSuccessful();
    } catch (RestClientException e) {
      return false;
    }
  }

  public static Performable theServer() {
    return instrumented(StartUp.class);
  }

}

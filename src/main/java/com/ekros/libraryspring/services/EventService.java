package com.ekros.libraryspring.services;

import com.ekros.libraryspring.model.entity.Book;
import com.ekros.libraryspring.model.entity.Order;
import com.ekros.libraryspring.model.entity.User;
import com.ekros.libraryspring.model.event.BookData;
import com.ekros.libraryspring.model.event.EventData;
import com.ekros.libraryspring.model.event.EventMessage;
import com.ekros.libraryspring.model.event.UserData;
import com.ekros.libraryspring.model.event.properties.EventProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.UUID;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@Service
@EnableAsync
@Log4j
public class EventService {

  public static final String ORDER_MESSAGE_TYPE = "library-order-info";

  private final EventProperties eventProperties;

  private final ObjectMapper mapper = new ObjectMapper();

  public EventService(EventProperties eventProperties) {
    this.eventProperties = eventProperties;
  }

  @SneakyThrows
  public void sendEventAnalyticsData(EventMessage eventMessage) {
    HttpResponse<String> response = sendHttpRequest(mapper.writeValueAsString(eventMessage));
    log.info(
        "EventData response status: " + response.statusCode() + " EventId: " + response.body());

  }

  public void sendEventAnalyticsData(Order order) {
    EventMessage eventMessage = generateEvent(order);
    sendEventAnalyticsData(eventMessage);
  }

  @SneakyThrows
  private HttpResponse<String> sendHttpRequest(String data) {
    HttpClient client = HttpClient.newHttpClient();

    URI uri = URI.create(eventProperties.getHost() + eventProperties.getPath());

    HttpRequest request = HttpRequest.newBuilder(uri)
        .header("Content-Type", "application/json")
        .POST(HttpRequest.BodyPublishers.ofString(data))
        .build();
    return client.send(request, HttpResponse.BodyHandlers.ofString());
  }

  private EventMessage generateEvent(Order order) {

    User user = order.getUser();
    Book book = order.getBook();

    LocalDate currentDate = LocalDate.now();

    int userAge = Period.between(user.getBirthday().toLocalDate(), currentDate).getYears();

    UserData userData = UserData.builder().age(userAge).country(user.getCountry())
        .build();
    BookData bookData = BookData.builder().name(book.getName()).author(book.getAuthor())
        .year(book.getYear()).genres(Arrays.asList(book.getGenres().split(";"))).build();

    EventData eventData = EventData.builder().book(bookData).user(userData).build();

    return EventMessage.builder().id(ORDER_MESSAGE_TYPE).transactionId(UUID.randomUUID().toString())
        .time(Instant.now().toString()).data(eventData).build();
  }
}

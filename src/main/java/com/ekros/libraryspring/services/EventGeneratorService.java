package com.ekros.libraryspring.services;

import com.ekros.libraryspring.model.entity.Book;
import com.ekros.libraryspring.model.event.BookData;
import com.ekros.libraryspring.model.event.EventData;
import com.ekros.libraryspring.model.event.EventMessage;
import com.ekros.libraryspring.model.event.UserData;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EventGeneratorService {

  private static final List<Integer> AGE = Stream.iterate(18, x -> x + 1).limit(75)
      .collect(Collectors.toList());

  private static final List<String> COUNTRIES = List.of("USA", "Canada", "UK", "Germany", "France",
      "Italian", "Ukraine", "Sweden");

  private final long COUNT;

  private final BookService bookService;

  private final EventService eventService;

  public EventGeneratorService(BookService bookService, EventService eventService) {
    this.bookService = bookService;
    this.eventService = eventService;
    COUNT = bookService.count();
  }


  public void generateEvents(int size) {
    List<EventMessage> events = Stream.iterate(0, x -> x + 1).limit(size)
        .map(x -> generateMessage()).collect(
            Collectors.toList());
    log.info("Generated {} events", events.size());
    events.forEach(eventService::sendEventAnalyticsData);
  }

  private EventMessage generateMessage() {
    Book book = bookService.getBookById(rand(COUNT));

    BookData bookData = BookData.builder().name(book.getName()).author(book.getAuthor())
        .year(book.getYear()).genres(
            Arrays.asList(book.getGenres().split(";"))).build();

    UserData userData = UserData.builder().country(COUNTRIES.get(rand(COUNTRIES.size())))
        .age(AGE.get(rand(AGE.size()))).build();

    EventData eventData = EventData.builder().book(bookData).user(userData).build();

    return EventMessage.builder().id(EventService.ORDER_MESSAGE_TYPE)
        .transactionId(UUID.randomUUID().toString()).data(eventData).build();

  }

  private int rand(long to) {
    return (int) (Math.random() * to);
  }
}

package com.ticketmaster;

import com.ticketmaster.api.dto.TicketDto;
import com.ticketmaster.api.dto.UserDto;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TicketSystemClientTest {
    private TicketSystemClient subject;

    @Mock
    RestTemplate restTemplate;

    private static final LocalDateTime date1 = LocalDateTime.of(
            LocalDate.of(2019, 9, 11), LocalTime.of(12, 0, 0));
    private static final LocalDateTime date2 = LocalDateTime.of(
            LocalDate.of(2019, 9, 11), LocalTime.of(12, 30, 0));
    private static final LocalDateTime date3 = LocalDateTime.of(
            LocalDate.of(2019, 9, 11), LocalTime.of(13, 0, 0));
    private static final LocalDateTime date4 = LocalDateTime.of(
            LocalDate.of(2019, 9, 11), LocalTime.of(13, 30, 0));

    private static List<UserDto> userDtos;

    @BeforeClass
    public static void initUserDtos() {
        TicketDto ticket1 = new TicketDto(1L, "title1", "description for title1", date1);
        TicketDto ticket2 = new TicketDto(2L, "title2", "description for title2", date2);
        TicketDto ticket3 = new TicketDto(3L, "title3", "description for title3", date3);
        TicketDto ticket4 = new TicketDto(4L, "title4", "description for title4", date4);

        Set<TicketDto> tickets1 = Set.of(ticket1, ticket2);
        Set<TicketDto> tickets2 = Set.of(ticket3);
        Set<TicketDto> tickets3 = Set.of(ticket4);

        UserDto user1 = new UserDto(1L, "serhiilytka", "Serhii", "Lytka",
                "serhii@gmail.com", tickets1);
        UserDto user2 = new UserDto(2L, "marypublic", "Mary", "Public",
                "'mary@gmail.com'", tickets2);
        UserDto user3 = new UserDto(3L, "johndou", "John", "Dou",
                "'john@gmail.com'", tickets3);
        userDtos = List.of(user1, user2, user3);
    }

    @Before
    public void setUp() {
        subject = new TicketSystemClient(restTemplate);
    }

    @Test
    public void shouldReturnUserDtosList() {
        //given
        String template = "http://localhost:8080/api/users";
        UriBuilder builder = UriComponentsBuilder.fromPath(template);
        URI uri = builder.build();

        //when
        when(restTemplate.exchange(uri, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<UserDto>>() {}))
                .thenReturn(new ResponseEntity<>(userDtos, HttpStatus.OK));

        ResponseEntity<List<UserDto>> result = subject.getAllUsers();
        //then
        assertThat(userDtos).isEqualTo(result.getBody());
    }

    @Test
    public void shouldReturnUserDto_whenGet() {
        //given
        String username = "serhiilytka";

        //when
        String template = "http://localhost:8080/api/users/{username}";
        Map<String, String> params = new HashMap<>();
        params.put("username", username);

        UriBuilder builder = UriComponentsBuilder.fromPath(template);
        URI uri = builder.build(params);

        when(restTemplate.getForEntity(uri, UserDto.class))
                .thenReturn(new ResponseEntity<>(userDtos.get(0), HttpStatus.OK));
        ResponseEntity<UserDto> result = subject.getUser(username);

        //then
        assertEquals(userDtos.get(0), result.getBody());
    }

    @AfterClass
    public static void tearDown() {
        userDtos = null;
    }
}
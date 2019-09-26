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
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

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

    private static final String template = "http://localhost:8080/api/users/";

    @BeforeClass
    public static void initUserDtos() {
        TicketDto ticket1 = new TicketDto(1L, "title1", "description for title1", date1);
        TicketDto ticket2 = new TicketDto(2L, "title2", "description for title2", date2);
        TicketDto ticket3 = new TicketDto(3L, "title3", "description for title3", date3);
        TicketDto ticket4 = new TicketDto(4L, "title4", "description for title4", date4);

        Set<TicketDto> tickets1 = Set.of(ticket1, ticket2);
        Set<TicketDto> tickets2 = Set.of(ticket3);
        Set<TicketDto> tickets3 = Set.of(ticket4);

        UserDto user1 = new UserDto(1L, "serhiilytka", "1111", "Serhii", "Lytka",
                "serhii@gmail.com", tickets1);
        UserDto user2 = new UserDto(2L, "marypublic", "1111", "Mary", "Public",
                "'mary@gmail.com'", tickets2);
        UserDto user3 = new UserDto(3L, "johndou", "1111", "John", "Dou",
                "'john@gmail.com'", tickets3);
        userDtos = List.of(user1, user2, user3);
    }

    @Before
    public void setUp() {
        subject = new TicketSystemClient(restTemplate);
    }

    @Test
    public void shouldReturnUserDtosList_whenGetAllUsers() {
        //given
        UriBuilder builder = UriComponentsBuilder.fromPath(template);
        URI uri = builder.build();

        //when
        when(restTemplate.exchange(uri, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<UserDto>>() {
                }))
                .thenReturn(new ResponseEntity<>(userDtos, HttpStatus.OK));

        ResponseEntity<List<UserDto>> result = subject.getAllUsers();

        //then
        assertThat(userDtos).isEqualTo(result.getBody());
    }

    @Test
    public void shouldReturnUserDto_whenGetUser() {
        //given
        String username = "serhiilytka";

        //when
        Map<String, String> params = new HashMap<>();
        params.put("username", username);

        UriBuilder builder = UriComponentsBuilder.fromPath(template + "{username}");
        URI uri = builder.build(params);

        when(restTemplate.getForEntity(uri, UserDto.class))
                .thenReturn(new ResponseEntity<>(userDtos.get(0), HttpStatus.OK));
        ResponseEntity<UserDto> result = subject.getUser(username);

        //then
        assertEquals(userDtos.get(0), result.getBody());
    }

    @Test
    public void shouldReturnUserDto_whenAddUser() {
        //given
        UserDto newUser = new UserDto(8L, "newbie", "1111", "Newbie", "Pawhands",
                "newbiepawhands@gmail.com", null);
        //when
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<UserDto> httpEntity = new HttpEntity<>(newUser, httpHeaders);

        when(restTemplate.exchange(template, HttpMethod.POST, httpEntity, UserDto.class))
                .thenReturn(new ResponseEntity<>(newUser, HttpStatus.OK));

        ResponseEntity<UserDto> result = subject.addUser(newUser);
        //then
        assertEquals(newUser, result.getBody());
    }

    @Test
    public void shouldReturnUserDto_whenUpdateUser() {
        //given
        Long id = 8L;
        UserDto newUser = new UserDto(8L, "newbie", "1111", "Newbie", "Pawhands",
                "newbiepawhands@gmail.com", null);

        //when
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<UserDto> httpEntity = new HttpEntity<>(newUser, httpHeaders);

        when(restTemplate.exchange(template + id, HttpMethod.PUT, httpEntity, UserDto.class))
                .thenReturn(new ResponseEntity<>(newUser, HttpStatus.OK));

        ResponseEntity<UserDto> result = subject.updateUser(id, newUser);

        //then
        assertEquals(newUser, result.getBody());
    }

    @Test
    public void shouldReturnUserDto_whenDeleteUser() {
        //given
        Long id = 8L;

        //when
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<UserDto> httpEntity = new HttpEntity<>(httpHeaders);

        UserDto newUser = new UserDto(8L, "newbie", "1111", "Newbie", "Pawhands",
                "newbiepawhands@gmail.com", null);

        when(restTemplate.exchange(template + id, HttpMethod.DELETE, httpEntity, UserDto.class))
                .thenReturn(new ResponseEntity<>(newUser, HttpStatus.OK));

        ResponseEntity<UserDto> result = subject.deleteUser(id);

        //then
        assertEquals(newUser, result.getBody());
    }

    @AfterClass
    public static void tearDown() {
        userDtos = null;
    }
}
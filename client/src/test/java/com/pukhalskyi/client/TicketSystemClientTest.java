package com.pukhalskyi.client;

import com.pukhalskyi.api.dto.TicketDto;
import com.pukhalskyi.api.dto.UserDto;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
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

    private static final String TEMPLATE = "http://localhost:8080/api/users/";

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

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


        //when
        when(restTemplate.exchange(TEMPLATE, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<UserDto>>() {
                }))
                .thenReturn(new ResponseEntity<>(userDtos, HttpStatus.OK));

        List<UserDto> result = subject.getAllUsers();

        //then
        assertThat(userDtos).isEqualTo(result);
    }

    @Test
    public void givenNullUsername_whenGetUser_thenThrowNullPointerException() {
        //given
        String username = null;

        //when
        when(restTemplate.getForEntity(TEMPLATE + username, UserDto.class))
                .thenReturn(null);

        exceptionRule.expect(NullPointerException.class);
        UserDto result = subject.getUser(username);

        //then
        assertNull(result);
    }

    @Test
    public void givenEmptyUsername_whenGetUser_thenThrowNullPointerException() {
        //given
        String username = "";

        //when
        when(restTemplate.getForEntity(TEMPLATE + username, UserDto.class))
                .thenReturn(null);

        exceptionRule.expect(NullPointerException.class);
        UserDto result = subject.getUser(username);

        //then
        assertNull(result);
    }

    @Test
    public void givenValidUsername_whenGetUser_thenReturnUserDto() {
        //given
        String username = "serhiilytka";

        //when
        when(restTemplate.getForEntity(TEMPLATE + username, UserDto.class))
                .thenReturn(new ResponseEntity<>(userDtos.get(0), HttpStatus.OK));

        UserDto result = subject.getUser(username);

        //then
        assertEquals(userDtos.get(0), result);
    }

    @Test
    public void givenNullUserDto_whenAddUser_thenThrowNullPointerException() {
        //given
        UserDto userDto = null;

        //when
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<UserDto> httpEntity = new HttpEntity<>(userDto, httpHeaders);

        when(restTemplate.exchange(TEMPLATE, HttpMethod.POST, httpEntity, UserDto.class))
                .thenReturn(null);

        exceptionRule.expect(NullPointerException.class);
        UserDto result = subject.addUser(userDto);

        //then
        assertNull(result);
    }

    @Test
    public void givenValidUserDto_whenAddUser_thenReturnUserDto() {
        //given
        UserDto userDto = userDtos.get(0);

        //when
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<UserDto> httpEntity = new HttpEntity<>(userDto, httpHeaders);

        when(restTemplate.exchange(TEMPLATE, HttpMethod.POST, httpEntity, UserDto.class))
                .thenReturn(new ResponseEntity<>(userDto, HttpStatus.OK));

        UserDto result = subject.addUser(userDto);

        //then
        assertEquals(userDto, result);
    }

    @Test
    public void givenNullUserDto_whenUpdateUser_thenThrowNullPointerException() {
        //given
        Long id = null;
        UserDto userDto = null;

        //when
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<UserDto> httpEntity = new HttpEntity<>(userDto, httpHeaders);

        when(restTemplate.exchange(TEMPLATE + id, HttpMethod.PUT, httpEntity, UserDto.class))
                .thenReturn(null);

        exceptionRule.expect(NullPointerException.class);
        UserDto result = subject.updateUser(id, userDto);

        //then
        assertNull(result);
    }

    @Test
    public void givenValidUserDto_whenUpdateUser_thenReturnUserDto() {
        //given
        Long id = 0L;
        UserDto userDto = userDtos.get(0);

        //when
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<UserDto> httpEntity = new HttpEntity<>(userDto, httpHeaders);

        when(restTemplate.exchange(TEMPLATE + id, HttpMethod.PUT, httpEntity, UserDto.class))
                .thenReturn(new ResponseEntity<>(userDto, HttpStatus.OK));

        UserDto result = subject.updateUser(id, userDto);

        //then
        assertEquals(userDto, result);
    }

    @Test
    public void givenNullUserDto_whenDeleteUser_thenThrowNullPointerException() {
        //given
        Long id = null;

        //when
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<UserDto> httpEntity = new HttpEntity<>(httpHeaders);

        when(restTemplate.exchange(TEMPLATE + id, HttpMethod.DELETE, httpEntity, UserDto.class))
                .thenReturn(null);

        exceptionRule.expect(NullPointerException.class);
        UserDto result = subject.deleteUser(id);

        //then
        assertNull(result);
    }

    @Test
    public void givenValidUserDto_whenDeleteUser_thenReturnUserDto() {
        //given
        Long id = 0L;
        UserDto userDto = userDtos.get(0);

        //when
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<UserDto> httpEntity = new HttpEntity<>(httpHeaders);

        when(restTemplate.exchange(TEMPLATE + id, HttpMethod.DELETE, httpEntity, UserDto.class))
                .thenReturn(new ResponseEntity<>(userDto, HttpStatus.OK));

        UserDto result = subject.deleteUser(id);

        //then
        assertEquals(userDto, result);
    }


    @AfterClass
    public static void tearDown() {
        userDtos = null;
    }
}
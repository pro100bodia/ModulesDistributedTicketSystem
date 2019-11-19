package com.pukhalskyi.controller;

import com.pukhalskyi.api.dto.TicketDto;
import com.pukhalskyi.api.dto.UserDto;
import com.pukhalskyi.exceptions.NotFoundException;
import com.pukhalskyi.model.UserModel;
import com.pukhalskyi.users.UserController;
import com.pukhalskyi.users.UserService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    private static final LocalDateTime date1 = LocalDateTime.of(
            LocalDate.of(2019, 9, 11), LocalTime.of(12, 0, 0));
    private static final LocalDateTime date2 = LocalDateTime.of(
            LocalDate.of(2019, 9, 11), LocalTime.of(12, 30, 0));
    private static final LocalDateTime date3 = LocalDateTime.of(
            LocalDate.of(2019, 9, 11), LocalTime.of(13, 0, 0));
    private static final LocalDateTime date4 = LocalDateTime.of(
            LocalDate.of(2019, 9, 11), LocalTime.of(13, 30, 0));
    private static List<UserModel> userModels;
    private static List<UserDto> userDtos;
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    private UserController subject;
    @Mock
    private UserService userService;
    @Mock
    private ModelMapper modelMapper;

    @BeforeClass
    public static void initUserModels() {
//        TicketModel ticket1 = new TicketModel(1L, "title1", "description for title1", date1, null);
//        TicketModel ticket2 = new TicketModel(2L, "title2", "description for title2", date2, null);
//        TicketModel ticket3 = new TicketModel(3L, "title3", "description for title3", date3, null);
//        TicketModel ticket4 = new TicketModel(4L, "title4", "description for title4", date4, null);

//        Set<TicketModel> tickets1 = Set.of(ticket1, ticket2);
//        Set<TicketModel> tickets2 = Set.of(ticket3);
//        Set<TicketModel> tickets3 = Set.of(ticket4);

//        UserModel user1 = new UserModel(1L, "serhiilytka", "1111", "Serhii", "Lytka",
//                "serhii@gmail.com", Role.ROLE_ADMIN, tickets1);
//        UserModel user2 = new UserModel(2L, "marypublic", "1111", "Mary", "Public",
//                "'mary@gmail.com'", Role.ROLE_CASHIER, tickets2);
//        UserModel user3 = new UserModel(3L, "johndou", "1111", "John", "Dou",
//                "'john@gmail.com'", Role.ROLE_USER, tickets3);
//        userModels = List.of(user1, user2, user3);
    }

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
        this.subject = new UserController(userService, modelMapper);
    }

    @Test
    public void givenNoArgs_whenFindAll_thenReturnUsersArray() {
        //given

        //when
//        when(userService.findAll("h2")).thenReturn(userModels);

        java.lang.reflect.Type targetListType = new TypeToken<List<UserDto>>() {
        }.getType();
        when(modelMapper.map(userModels, targetListType)).thenReturn(userDtos);

//        ResponseEntity<List<UserDto>> result = subject.findAll("h2");

        //then
//        assertThat(userDtos).isEqualTo(result.getBody());
    }

    @Test
    public void givenNullUsername_whenGetUserByUsername_thenReturnNotFound() {
        //given

        //when
//        when(userService.getUserByUsername(DataType.H2, null)).thenThrow(NotFoundException.class);

        exceptionRule.expect(NotFoundException.class);
//        ResponseEntity<UserDto> responseEntity = subject.getUserByUsername(DataType.H2, null);

        //then
//        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
//        assertNull(responseEntity.getBody());
    }

    @Test
    public void givenEmptyUsername_whenGetUserByUsername_thenReturnNotFound() {
        //given
        String username = "";

        //when
//        when(userService.getUserByUsername(DataType.H2, username)).thenThrow(NotFoundException.class);

        exceptionRule.expect(NotFoundException.class);
//        ResponseEntity<UserDto> responseEntity = subject.getUserByUsername(DataType.H2, username);

        //then
//        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
//        assertNull(responseEntity.getBody());
    }

    @Test
    public void givenUsername_whenGetUserByUsername_thenReturnUserWithTickets() {
        //given
        String username = "serhiilytka";

        //when
        LocalDateTime date1 = LocalDateTime.of(
                LocalDate.of(2019, 9, 11), LocalTime.of(12, 0, 0));
        LocalDateTime date2 = LocalDateTime.of(
                LocalDate.of(2019, 9, 11), LocalTime.of(12, 30, 0));

//        TicketModel ticket1 = new TicketModel(
//                1L, "title1", "description for title1", date1, null);
//        TicketModel ticket2 = new TicketModel(
//                2L, "title2", "description for title2", date2, null);
//        Set<TicketModel> tickets = Set.of(ticket1, ticket2);

//        UserModel userModel = new UserModel(1L, "serhiilytka", "1111", "Serhii", "Lytka",
//                "serhii@gmail.com", Role.ROLE_ADMIN, tickets);

//        when(userService.getUserByUsername(DataType.H2, username)).thenReturn(userModel);

//        TicketDto ticketDto1 = new TicketDto(1L, "title1", "description for title1", date1);
//        TicketDto ticketDto2 = new TicketDto(2L, "title2", "description for title2", date2);
//        Set<TicketDto> ticketsDto = Set.of(ticketDto1, ticketDto2);

//        UserDto userDto = new UserDto(1L, "serhiilytka", "1111", "Serhii", "Lytka",
//                "serhii@gmail.com", ticketsDto);
//
//        when(modelMapper.map(userModel, UserDto.class)).thenReturn(userDto);
//
//        ResponseEntity<UserDto> responseEntity = subject.getUserByUsername(DataType.H2, username);
//
//        //then
//        assertEquals(userDto, responseEntity.getBody());
    }

    @Test
    public void givenNullUsername_whenSaveUserOrUpdateUser_thenReturnNotFound() {
        //given

        //when
//        when(userService.saveUser(DataType.H2, null)).thenThrow(NotFoundException.class);

        exceptionRule.expect(NotFoundException.class);
//        ResponseEntity<UserDto> responseEntity = subject.saveUser(DataType.H2, null);

        //then
//        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
//        assertNull(responseEntity.getBody());
    }

    @Test
    public void givenEmptyUsername_whenSaveUserOrUpdateUser_thenReturnNotFound() {
        //given
        UserDto nonameUser = new UserDto(8L, "", "1111", "", "", "", null);
//        UserModel nonameUserModel = new UserModel(
//                8L, "", "1111", "", "", "", Role.ROLE_USER, null);

        //when
//        when(modelMapper.map(nonameUser, UserModel.class)).thenReturn(nonameUserModel);
//        when(userService.saveUser(DataType.H2, nonameUserModel)).thenThrow(NotFoundException.class);

        exceptionRule.expect(NotFoundException.class);
//        ResponseEntity<UserDto> responseEntity = subject.saveUser(DataType.H2, nonameUser);

        //then
//        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
//        assertNull(responseEntity.getBody());
    }

    @Test
    public void givenValidUser_whenSaveUserOrUpdateUser_thenReturnUserWithTickets() {
        //given
        LocalDateTime date1 = LocalDateTime.of(
                LocalDate.of(2019, 9, 11), LocalTime.of(12, 0, 0));
        LocalDateTime date2 = LocalDateTime.of(
                LocalDate.of(2019, 9, 11), LocalTime.of(12, 30, 0));

//        TicketModel ticket1 = new TicketModel(
//                1L, "title1", "description for title1", date1, null);
//        TicketModel ticket2 = new TicketModel(
//                2L, "title2", "description for title2", date2, null);
//        Set<TicketModel> tickets = Set.of(ticket1, ticket2);
//
//        UserModel userModel = new UserModel(1L, "serhiilytka", "1111", "Serhii", "Lytka",
//                "serhii@gmail.com", Role.ROLE_USER, tickets);


//        TicketDto ticketDto1 = new TicketDto(1L, "title1", "description for title1", date1);
//        TicketDto ticketDto2 = new TicketDto(2L, "title2", "description for title2", date2);
//        Set<TicketDto> ticketsDto = Set.of(ticketDto1, ticketDto2);

//        UserDto userDto = new UserDto(1L, "serhiilytka", "1111", "Serhii", "Lytka",
//                "serhii@gmail.com", ticketsDto);

        //when
//        when(modelMapper.map(userDto, UserModel.class)).thenReturn(userModel);
//        when(userService.saveUser(DataType.H2, userModel)).thenReturn(userModel);
//        when(modelMapper.map(userModel, UserDto.class)).thenReturn(userDto);
//
//        ResponseEntity<UserDto> responseEntity = subject.saveUser(DataType.H2, userDto);

        //then
//        assertEquals(userDto, responseEntity.getBody());
    }
}
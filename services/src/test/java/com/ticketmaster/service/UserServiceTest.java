package com.ticketmaster.service;

import com.ticketmaster.exceptions.NotFoundException;
import com.ticketmaster.persistence.model_repository.UserModelRepository;
import com.ticketmaster.service.model.TicketModel;
import com.ticketmaster.service.model.UserModel;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();
    private UserService subject;
    @Mock
    private UserModelRepository userRepo;

    private static final LocalDateTime date1 = LocalDateTime.of(
            LocalDate.of(2019, 9, 11), LocalTime.of(12, 0, 0));
    private static final LocalDateTime date2 = LocalDateTime.of(
            LocalDate.of(2019, 9, 11), LocalTime.of(12, 30, 0));
    private static final LocalDateTime date3 = LocalDateTime.of(
            LocalDate.of(2019, 9, 11), LocalTime.of(13, 0, 0));
    private static final LocalDateTime date4 = LocalDateTime.of(
            LocalDate.of(2019, 9, 11), LocalTime.of(13, 30, 0));

    private static List<UserModel> userModels;

    @BeforeClass
    public static void initUserModels() {
        TicketModel ticket1 = new TicketModel(
                1L, "title1", "description for title1", date1, null);
        TicketModel ticket2 = new TicketModel(
                2L, "title2", "description for title2", date2, null);
        TicketModel ticket3 = new TicketModel(
                3L, "title3", "description for title3", date3, null);
        TicketModel ticket4 = new TicketModel(
                4L, "title4", "description for title4", date4, null);

        Set<TicketModel> tickets1 = Set.of(ticket1, ticket2);
        Set<TicketModel> tickets2 = Set.of(ticket3);
        Set<TicketModel> tickets3 = Set.of(ticket4);

        UserModel user1 = new UserModel(1L, "serhiilytka", "Serhii", "Lytka",
                "serhii@gmail.com", tickets1);
        UserModel user2 = new UserModel(2L, "marypublic", "Mary", "Public",
                "'mary@gmail.com'", tickets2);
        UserModel user3 = new UserModel(3L, "johndou", "John", "Dou",
                "'john@gmail.com'", tickets3);
        userModels = List.of(user1, user2, user3);
    }

    @Before
    public void setUp() throws Exception {
        subject = new UserService(userRepo);
    }

    @Test
    public void givenNoArgs_whenFindAll_thenReturnUserModels() {
        //given


        //when
        when(userRepo.findAll()).thenReturn(userModels);
        List<UserModel> result = subject.findAll();
        //then
        assertThat(userModels).isEqualTo(result);
    }

    @Test
    public void givenNullUsername_whenGetUserByUsername_thenThrowNotFoundException1() {
        //given
        String username = null;

        //when
        when(userRepo.findByUsername(username)).thenReturn(null);

        exception.expect(NotFoundException.class);
        exception.expectMessage("User not found");
        UserModel result = subject.getUserByUsername(username);
    }

    @Test
    public void givenEmptyUsername_whenGetUserByUsername_thenThrowNotFoundException1() {
        //given
        String username = "";

        //when
        when(userRepo.findByUsername(username)).thenReturn(null);

        exception.expect(NotFoundException.class);
        exception.expectMessage("User not found");
        UserModel result = subject.getUserByUsername(username);
    }

    @Test
    public void givenUsername_whenGetUserByUsername_thenThrowNotFoundException1() {
        //given
        String username = "serhiilytka";

        //when
        LocalDateTime date1 = LocalDateTime.of(
                LocalDate.of(2019, 9, 11), LocalTime.of(12, 0, 0));
        LocalDateTime date2 = LocalDateTime.of(
                LocalDate.of(2019, 9, 11), LocalTime.of(12, 30, 0));

        TicketModel ticket1 = new TicketModel(
                1L, "title1", "description for title1", date1, null);
        TicketModel ticket2 = new TicketModel(
                2L, "title2", "description for title2", date2, null);
        Set<TicketModel> tickets = Set.of(ticket1, ticket2);

        UserModel userModel = new UserModel(1L, "serhiilytka", "Serhii", "Lytka",
                "serhii@gmail.com", tickets);

        when(userRepo.findByUsername(username)).thenReturn(userModel);

        UserModel result = subject.getUserByUsername(username);

        assertEquals(userModel, result);
    }


}
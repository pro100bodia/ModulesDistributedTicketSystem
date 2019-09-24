package com.ticketmaster.persistence.model_repository;

import com.ticketmaster.persistence.entity.Role;
import com.ticketmaster.persistence.entity.Ticket;
import com.ticketmaster.persistence.entity.User;
import com.ticketmaster.persistence.repository.UserRepository;
import com.ticketmaster.service.model.TicketModel;
import com.ticketmaster.service.model.UserModel;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
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

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserModelRepositoryTest {
    private UserModelRepository subject;

    @Mock
    private UserRepository userRepo;

    @Mock
    private ModelMapper modelMapper;

    private static final LocalDateTime date1 = LocalDateTime.of(
            LocalDate.of(2019, 9, 11), LocalTime.of(12, 0, 0));
    private static final LocalDateTime date2 = LocalDateTime.of(
            LocalDate.of(2019, 9, 11), LocalTime.of(12, 30, 0));
    private static final LocalDateTime date3 = LocalDateTime.of(
            LocalDate.of(2019, 9, 11), LocalTime.of(13, 0, 0));
    private static final LocalDateTime date4 = LocalDateTime.of(
            LocalDate.of(2019, 9, 11), LocalTime.of(13, 30, 0));

    private static List<User> users;
    private static List<UserModel> userModels;

    @BeforeClass
    public static void initUsers() {
        Ticket ticket1 = new Ticket(1L, "title1", "description for title1", date1, null);
        Ticket ticket2 = new Ticket(2L, "title2", "description for title2", date2, null);
        Ticket ticket3 = new Ticket(3L, "title3", "description for title3", date3, null);
        Ticket ticket4 = new Ticket(4L, "title4", "description for title4", date4, null);

        Set<Ticket> tickets1 = Set.of(ticket1, ticket2);
        Set<Ticket> tickets2 = Set.of(ticket3);
        Set<Ticket> tickets3 = Set.of(ticket4);

        User user1 = new User(1L, "serhiilytka", "Serhii", "Lytka",
                "serhii@gmail.com", Role.ADMIN, tickets1);
        User user2 = new User(2L, "marypublic", "Mary", "Public",
                "'mary@gmail.com'", Role.CASHIER, tickets2);
        User user3 = new User(3L, "johndou", "John", "Dou",
                "'john@gmail.com'", Role.USER, tickets3);
        users = List.of(user1, user2, user3);
    }

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
        subject = new UserModelRepository(userRepo, modelMapper);
    }

    @Test
    public void givenNoArgs_whenFindAll_ThenReturnUserModels() {
        //given

        //when
        when(userRepo.findAll()).thenReturn(users);

        java.lang.reflect.Type targetListType = new TypeToken<List<UserModel>>() {
        }.getType();
        when(modelMapper.map(users, targetListType)).thenReturn(userModels);

        List<UserModel> result = subject.findAll();
        //then
        assertThat(userModels).isEqualTo(result);
    }

    @Test
    public void givenNullUsername_whenFindByUsername_thenReturnNull() {
        //given
        String username = null;

        //when
        UserModel result = subject.findByUsername(username);

        //then
        assertNull(result);
    }

    @Test
    public void givenEmptyUsername_whenFindByUsername_thenReturnNull() {
        //given
        String username = "";

        //when
        UserModel result = subject.findByUsername(username);

        //then
        assertNull(result);
    }

    @Test
    public void givenUsername_whenFindByUsername_thenReturnNull() {
        //given
        String username = "serhiilytka";

        //when
        LocalDateTime date1 = LocalDateTime.of(
                LocalDate.of(2019, 9, 11), LocalTime.of(12, 0, 0));
        LocalDateTime date2 = LocalDateTime.of(
                LocalDate.of(2019, 9, 11), LocalTime.of(12, 30, 0));

        Ticket ticket1 = new Ticket(1L, "title1", "description for title1", date1, null);
        Ticket ticket2 = new Ticket(2L, "title2", "description for title2", date2, null);
        Set<Ticket> tickets = Set.of(ticket1, ticket2);

        User user = new User(1L, "serhiilytka", "Serhii", "Lytka",
                "serhii@gmail.com", Role.ADMIN, tickets);

        when(userRepo.findByUsername(username)).thenReturn(user);

        TicketModel ticketModel1 = new TicketModel(
                1L, "title1", "description for title1", date1, null);
        TicketModel ticketModel2 = new TicketModel(
                2L, "title2", "description for title2", date2, null);
        Set<TicketModel> ticketsModel = Set.of(ticketModel1, ticketModel2);

        UserModel userModel = new UserModel(1L, "serhiilytka", "Serhii", "Lytka",
                "serhii@gmail.com", ticketsModel);

        when(modelMapper.map(user, UserModel.class)).thenReturn(userModel);

        UserModel result = subject.findByUsername(username);

        //then
        assertEquals(result, userModel);
    }


}
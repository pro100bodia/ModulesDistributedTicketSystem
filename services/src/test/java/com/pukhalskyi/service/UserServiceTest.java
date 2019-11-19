package com.pukhalskyi.service;

import com.pukhalskyi.model.UserModel;
import com.pukhalskyi.users.UserRepository;
import com.pukhalskyi.users.UserService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    private static final LocalDateTime date1 = LocalDateTime.of(
            LocalDate.of(2019, 9, 11), LocalTime.of(12, 0, 0));
    private static final LocalDateTime date2 = LocalDateTime.of(
            LocalDate.of(2019, 9, 11), LocalTime.of(12, 30, 0));
    private static final LocalDateTime date3 = LocalDateTime.of(
            LocalDate.of(2019, 9, 11), LocalTime.of(13, 0, 0));
    private static final LocalDateTime date4 = LocalDateTime.of(
            LocalDate.of(2019, 9, 11), LocalTime.of(13, 30, 0));
    private static List<UserModel> userModels;
    @Mock
    private Map<String, UserRepository> repositoryMap;
    @Mock
//    private DataTypeConverter dataTypeConverter;
    private UserService subject;

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

    @Before
//    public void setUp() {
//        subject = new UserService(repositoryMap, dataTypeConverter);
//    }

    @Ignore
    @Test
    public void shouldReturnUserModelsList_whenFindAll() {
        //given

        //when
//        UserJpaModelRepository repo = mock(UserJpaModelRepository.class);
//        when(repositoryMap.get(dataTypeConverter.convert(DataType.H2))).thenReturn(repo);

//        when(repo.findAll()).thenReturn(userModels);
//        List<UserModel> result = subject.findAll("h2");

        //then
//        assertThat(userModels).isEqualTo(result);
    }

    @Test
    public void shouldReturnUserModel_whenGetUserByUsername() {
        //given
//        DataType db = DataType.H2;
        String username = "serhiilytka";

        //when
//        UserJpaModelRepository repo = mock(UserJpaModelRepository.class);
//        when(repositoryMap.get(dataTypeConverter.convert(db))).thenReturn(repo);
//
//        when(repo.findByUsername(username)).thenReturn(userModels.get(0));
//        UserModel result = subject.getUserByUsername(db, username);
//
//        //then
//        assertEquals(userModels.get(0), result);
    }

    @Test
    public void shouldReturnUserModel_whenSaveUser() {
        //given
//        DataType db = DataType.H2;
//        UserModel userModel = userModels.get(0);
//
//        //when
//        UserJpaModelRepository repo = mock(UserJpaModelRepository.class);
//        when(repositoryMap.get(dataTypeConverter.convert(db))).thenReturn(repo);
//
//        when(repo.saveUser(userModel)).thenReturn(userModels.get(0));
//        UserModel result = subject.saveUser(db, userModel);
//
//        //then
//        assertEquals(userModels.get(0), result);
    }


}
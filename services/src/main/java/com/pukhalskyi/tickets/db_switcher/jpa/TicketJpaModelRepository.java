package com.pukhalskyi.tickets.db_switcher.jpa;

import com.pukhalskyi.entity.Ticket;
import com.pukhalskyi.model.TicketModel;
import com.pukhalskyi.tickets.db_switcher.ListTicketRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
import java.util.List;

@Repository

public class TicketJpaModelRepository implements ListTicketRepository {
    private final ModelMapper modelMapper;
    private com.pukhalskyi.tickets.db_switcher.jpa.TicketEntityRepository ticketRepo;

    public TicketJpaModelRepository(com.pukhalskyi.tickets.db_switcher.jpa.TicketEntityRepository ticketPaginationEntityRepository, ModelMapper modelMapper) {
        this.ticketRepo = ticketPaginationEntityRepository;
        this.modelMapper = modelMapper;
    }

    public List<TicketModel> findAll() {
        List<Ticket> userList = ticketRepo.findAll();

        Type targetListType = new TypeToken<List<TicketModel>>() {
        }.getType();

        return modelMapper.map(userList, targetListType);
    }


}

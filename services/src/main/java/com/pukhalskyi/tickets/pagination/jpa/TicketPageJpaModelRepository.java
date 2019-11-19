package com.pukhalskyi.tickets.pagination.jpa;

import com.pukhalskyi.entity.Ticket;
import com.pukhalskyi.model.TicketModel;
import com.pukhalskyi.tickets.pagination.TicketPageRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
import java.util.List;

@Repository
public class TicketPageJpaModelRepository implements TicketPageRepository {
    private final ModelMapper modelMapper;
    private TicketPageEntityRepository ticketPageEntityRepository;

    public TicketPageJpaModelRepository(TicketPageEntityRepository ticketPageEntityRepository, ModelMapper modelMapper) {
        this.ticketPageEntityRepository = ticketPageEntityRepository;
        this.modelMapper = modelMapper;
    }

    public Page<TicketModel> findAll(Pageable pageable) {
        Page<Ticket> userPage = ticketPageEntityRepository.findAll(pageable);
        List<Ticket> userList = userPage.getContent();

        Type targetListType = new TypeToken<List<TicketModel>>() {
        }.getType();

        List<TicketModel> ticketModels = modelMapper.map(userList, targetListType);

        return new PageImpl<>(ticketModels);
    }


}

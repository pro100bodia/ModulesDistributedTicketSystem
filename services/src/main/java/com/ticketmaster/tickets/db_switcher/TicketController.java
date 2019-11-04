package com.ticketmaster.tickets.db_switcher;

import com.ticketmaster.dto.TicketDto;
import com.ticketmaster.entity.DbType;
import com.ticketmaster.model.TicketModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping("/api/v1.1/tickets")
public class TicketController {
    private final ModelMapper modelMapper;
    private TicketService ticketService;

    public TicketController(TicketService ticketService, ModelMapper modelMapper) {
        this.ticketService = ticketService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<TicketDto>> findById(@RequestHeader DbType db) {
        List<TicketModel> ticketModels = ticketService.findAll(db);

        Type targetListType = new TypeToken<List<TicketDto>>() {
        }.getType();


        return new ResponseEntity<>((List<TicketDto>) modelMapper.map(ticketModels, targetListType),
                HttpStatus.OK);
    }
}

package com.ticketmaster.services.controller;

import com.ticketmaster.api.dto.TicketDto;
import com.ticketmaster.services.service.TicketService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/tickets")
public class TicketController {
    private TicketService ticketService;
    private final ModelMapper modelMapper;

    public TicketController(TicketService ticketService, ModelMapper modelMapper) {
        this.ticketService = ticketService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("{id}")
    public ResponseEntity<TicketDto> findById(@PathVariable Long id) {
        return new ResponseEntity<>(modelMapper.map(ticketService.findById(id), TicketDto.class),
                HttpStatus.OK);
    }
}

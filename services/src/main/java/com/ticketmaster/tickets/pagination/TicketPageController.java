package com.ticketmaster.tickets.pagination;

import com.ticketmaster.dto.TicketDto;
import com.ticketmaster.entity.DbType;
import com.ticketmaster.model.TicketModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;

@RestController
@RequestMapping("/api/v1.2/tickets")
public class TicketPageController {
    private final ModelMapper modelMapper;
    private TicketPageService ticketPageService;

    public TicketPageController(TicketPageService ticketPageService, ModelMapper modelMapper) {
        this.ticketPageService = ticketPageService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<Page<TicketDto>> findAll(@RequestHeader DbType db,
                                                   @RequestParam(required = false, defaultValue = "0") int page,
                                                   @RequestParam(required = false, defaultValue = "3") int size) {
        Page<TicketModel> ticketModels = ticketPageService.findAll(db, page, size);

        Type targetListType = new TypeToken<Page<TicketDto>>() {
        }.getType();

        return new ResponseEntity<>((Page<TicketDto>) modelMapper.map(ticketModels, targetListType),
                HttpStatus.OK);
    }
}

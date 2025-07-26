package com.sstinternaltools.sstinternal_tools.Issues.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sstinternaltools.sstinternal_tools.Issues.dto.ticket.TicketCreateDto;
import com.sstinternaltools.sstinternal_tools.Issues.dto.ticket.TicketResponseDto;
import com.sstinternaltools.sstinternal_tools.Issues.dto.ticket.TicketSummaryDto;
import com.sstinternaltools.sstinternal_tools.Issues.dto.ticket.TicketUpdateDto;
import com.sstinternaltools.sstinternal_tools.Issues.entity.CampusType;
import com.sstinternaltools.sstinternal_tools.Issues.entity.TicketPriority;
import com.sstinternaltools.sstinternal_tools.Issues.entity.TicketStatus;
import com.sstinternaltools.sstinternal_tools.Issues.service.ImageStorageService;
import com.sstinternaltools.sstinternal_tools.Issues.service.ImageValidator;
import com.sstinternaltools.sstinternal_tools.Issues.service.TicketService;
import com.sstinternaltools.sstinternal_tools.security.entity.UserPrincipal;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/issues")
@PreAuthorize("isAuthenticated()")
public class TicketController {

    private final TicketService ticketService;
    private final ImageStorageService imageStorageService;
    private final ImageValidator imageValidator;

    @Autowired
    public TicketController(TicketService ticketService, ImageStorageService imageStorageService, ImageValidator imageValidator) {
        this.ticketService = ticketService;
        this.imageStorageService = imageStorageService;
        this.imageValidator = imageValidator;
    }

    @PostMapping("/tickets")
    public ResponseEntity<TicketResponseDto> createTicket(@Valid @RequestBody TicketCreateDto ticketCreateDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null || !authentication.isAuthenticated() || 
            "anonymousUser".equals(authentication.getPrincipal())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Long userId = userPrincipal.getUser().getId();

        TicketResponseDto newTicket = ticketService.createTicket(ticketCreateDto, userId);
        return new ResponseEntity<>(newTicket, HttpStatus.CREATED);
    }
   

    @GetMapping("/tickets")
    public ResponseEntity<List<TicketSummaryDto>> getAllTickets() {
        List<TicketSummaryDto> tickets = ticketService.getAllTickets();
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @GetMapping("/tickets/{id}")
    public ResponseEntity<TicketResponseDto> getTicketById(@PathVariable Long id) {
        TicketResponseDto ticket = ticketService.getTicketById(id);
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @GetMapping("/tickets/user/{userId}")
    public ResponseEntity<List<TicketSummaryDto>> getTicketsByUserId(@PathVariable Long userId) {
        List<TicketSummaryDto> tickets = ticketService.getTicketsByUserId(userId);
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @GetMapping("/tickets/status/{status}")
    public ResponseEntity<List<TicketSummaryDto>> getTicketsByStatus(@PathVariable TicketStatus status) {
        List<TicketSummaryDto> tickets = ticketService.getTicketsByStatus(status);
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @GetMapping("/tickets/priority/{priority}")
    public ResponseEntity<List<TicketSummaryDto>> getTicketsByPriority(@PathVariable TicketPriority priority) {
        List<TicketSummaryDto> tickets = ticketService.getTicketsByPriority(priority);
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @GetMapping("/tickets/campus/{campus}")
    public ResponseEntity<List<TicketSummaryDto>> getTicketsByCampus(@PathVariable CampusType campus) {
        List<TicketSummaryDto> tickets = ticketService.getTicketsByCampus(campus);
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @PutMapping("/tickets/{id}")
    public ResponseEntity<TicketResponseDto> updateTicket(@PathVariable Long id, @Valid @RequestBody TicketUpdateDto ticketUpdateDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null || !authentication.isAuthenticated() || 
            "anonymousUser".equals(authentication.getPrincipal())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        
        TicketResponseDto updatedTicket = ticketService.updateTicket(id, ticketUpdateDto);
        return new ResponseEntity<>(updatedTicket, HttpStatus.OK);
    }

    @PatchMapping("/tickets/{id}/status")
    public ResponseEntity<TicketResponseDto> updateTicketStatus(@PathVariable Long id, @RequestParam TicketStatus status) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null || !authentication.isAuthenticated() || 
            "anonymousUser".equals(authentication.getPrincipal())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        
        TicketResponseDto updatedTicket = ticketService.updateTicketStatus(id, status);
        return new ResponseEntity<>(updatedTicket, HttpStatus.OK);
    }

    @DeleteMapping("/tickets/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null || !authentication.isAuthenticated() || 
            "anonymousUser".equals(authentication.getPrincipal())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        
        ticketService.deleteTicket(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/tickets/{id}/upvote")
    public ResponseEntity<TicketResponseDto> upvoteTicket(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null || !authentication.isAuthenticated() || 
            "anonymousUser".equals(authentication.getPrincipal())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        
        TicketResponseDto updatedTicket = ticketService.upvoteTicket(id);
        return new ResponseEntity<>(updatedTicket, HttpStatus.OK);
    }

    @PostMapping("/tickets/{id}/images")
    public ResponseEntity<List<String>> uploadTicketImages(@PathVariable Long id, @RequestParam("images") MultipartFile[] images) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null || !authentication.isAuthenticated() || 
            "anonymousUser".equals(authentication.getPrincipal())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        
        List<String> imageUrls = new ArrayList<>();
        for (MultipartFile image : images) {
            imageValidator.validate(image);
            String url = imageStorageService.storeImage(image);
            imageUrls.add(url);
        }
        ticketService.appendImageUrls(id, imageUrls);
        return new ResponseEntity<>(imageUrls, HttpStatus.OK);
    }


}
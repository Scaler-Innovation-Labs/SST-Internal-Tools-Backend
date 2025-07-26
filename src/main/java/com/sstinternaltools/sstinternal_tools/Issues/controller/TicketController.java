package com.sstinternaltools.sstinternal_tools.Issues.controller;

import com.sstinternaltools.sstinternal_tools.Issues.dto.ticket.TicketCreateDto;
import com.sstinternaltools.sstinternal_tools.Issues.dto.ticket.TicketResponseDto;
import com.sstinternaltools.sstinternal_tools.Issues.dto.ticket.TicketSummaryDto;
import com.sstinternaltools.sstinternal_tools.Issues.dto.ticket.TicketUpdateDto;
import com.sstinternaltools.sstinternal_tools.Issues.entity.CampusType;
import com.sstinternaltools.sstinternal_tools.Issues.entity.TicketPriority;
import com.sstinternaltools.sstinternal_tools.Issues.entity.TicketStatus;
import com.sstinternaltools.sstinternal_tools.Issues.service.TicketService;
import com.sstinternaltools.sstinternal_tools.security.entity.UserPrincipal;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.sstinternaltools.sstinternal_tools.Issues.service.ImageStorageService;
import com.sstinternaltools.sstinternal_tools.Issues.service.ImageValidator;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/issues")
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
        Long userId = extractUserIdFromAuthentication(authentication);

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
        TicketResponseDto updatedTicket = ticketService.updateTicket(id, ticketUpdateDto);
        return new ResponseEntity<>(updatedTicket, HttpStatus.OK);
    }

    @PatchMapping("/tickets/{id}/status")
    public ResponseEntity<TicketResponseDto> updateTicketStatus(@PathVariable Long id, @RequestParam TicketStatus status) {
        TicketResponseDto updatedTicket = ticketService.updateTicketStatus(id, status);
        return new ResponseEntity<>(updatedTicket, HttpStatus.OK);
    }

    @DeleteMapping("/tickets/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/tickets/{id}/upvote")
    public ResponseEntity<TicketResponseDto> upvoteTicket(@PathVariable Long id) {
        TicketResponseDto updatedTicket = ticketService.upvoteTicket(id);
        return new ResponseEntity<>(updatedTicket, HttpStatus.OK);
    }

    @PostMapping("/tickets/{id}/images")
    public ResponseEntity<List<String>> uploadTicketImages(@PathVariable Long id, @RequestParam("images") MultipartFile[] images) {
        List<String> imageUrls = new ArrayList<>();
        for (MultipartFile image : images) {
            imageValidator.validate(image);
            String url = imageStorageService.storeImage(image);
            imageUrls.add(url);
        }
        ticketService.appendImageUrls(id, imageUrls);
        return new ResponseEntity<>(imageUrls, HttpStatus.OK);
    }

    private Long extractUserIdFromAuthentication(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("User not authenticated or authentication is invalid.");
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserPrincipal) {
            UserPrincipal userPrincipal = (UserPrincipal) principal;
            if (userPrincipal.getUser() == null) {
                throw new IllegalStateException("User object within UserPrincipal is null.");
            }
            return userPrincipal.getUser().getId();
        } else {
            if (principal instanceof String && "anonymousUser".equals(principal)) {
                throw new IllegalStateException("Attempted to extract user ID from anonymousUser.");
            }
            throw new IllegalStateException("Authentication principal is not an instance of UserPrincipal. Actual type: " + principal.getClass().getName());
        }
    }
}
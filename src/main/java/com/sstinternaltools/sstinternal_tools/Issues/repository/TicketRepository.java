package com.sstinternaltools.sstinternal_tools.Issues.repository;
import com.sstinternaltools.sstinternal_tools.Issues.entity.Ticket;
import com.sstinternaltools.sstinternal_tools.Issues.entity.TicketPriority;
import com.sstinternaltools.sstinternal_tools.Issues.entity.TicketStatus;
import com.sstinternaltools.sstinternal_tools.Issues.entity.CampusType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {



    List<Ticket> findByPriority(TicketPriority priority);
    List<Ticket> findByCategory(String category);
    List<Ticket> findByUserId(Long id);
    List<Ticket> findByStatus(TicketStatus status);

    // Added correct methods
    List<Ticket> findByCampus(CampusType campus);
    List<Ticket> findByCreatedbyId(Long createdbyId); // Corrected parameter name for clarity
}
package com.sstinternaltools.sstinternal_tools.Issues.dto.ticket;
import com.sstinternaltools.sstinternal_tools.Issues.entity.CampusType;
import com.sstinternaltools.sstinternal_tools.Issues.entity.TicketPriority;
import com.sstinternaltools.sstinternal_tools.Issues.entity.TicketStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class TicketCreateDto {


    @NotBlank (message = "Title cannot be blank")
    private String title;
    @NotBlank (message = "Description cannot be blank")
    private String description;
    @NotBlank (message = "Priority cannot be blank")
    private TicketPriority priority;
    @NotBlank (message = "Status cannot be blank")
    private TicketStatus status = TicketStatus.PENDING;
    @NotNull(message = "Campus must be selected")
    private CampusType campus;
    private boolean isPrivate = false; // false = public, true = private


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TicketPriority getPriority() {
        return priority;
    }

    public void setPriority(TicketPriority priority) {
        this.priority = priority;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public CampusType getCampus() {
        return campus;
    }

    public void setCampus(CampusType campus) {
        this.campus = campus;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }
}

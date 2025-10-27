package com.sp.notificationservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "alert")
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @NotNull
    private String alert;
    @NotNull
    private String severity;
    @NotNull
    private String message;
    @NotNull
    private String relatedService;
    @NotNull
    private UUID relatedServiceId;
    private LocalDateTime triggeredAt;
    private boolean isResolved;
    private UUID resolverId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRelatedService() {
        return relatedService;
    }

    public void setRelatedService(String relatedService) {
        this.relatedService = relatedService;
    }

    public UUID getRelatedServiceId() {
        return relatedServiceId;
    }

    public void setRelatedServiceId(UUID relatedServiceId) {
        this.relatedServiceId = relatedServiceId;
    }

    public LocalDateTime getTriggeredAt() {
        return triggeredAt;
    }

    public void setTriggeredAt(LocalDateTime triggeredAt) {
        this.triggeredAt = triggeredAt;
    }

    public boolean isResolved() {
        return isResolved;
    }

    public void setResolved(boolean resolved) {
        isResolved = resolved;
    }

    public UUID getResolverId() {
        return resolverId;
    }

    public void setResolverId(UUID resolverId) {
        this.resolverId = resolverId;
    }
}

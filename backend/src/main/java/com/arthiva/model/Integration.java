package com.arthiva.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity @Table(name = "integrations")
  @Data @NoArgsConstructor @AllArgsConstructor @Builder
  public class Integration {
      @Id @GeneratedValue(strategy = GenerationType.UUID)
      private String id;
      @ManyToOne @JoinColumn(name = "company_id", nullable = false)
      private Company company;
      @Column(nullable = false) private String name; // QUICKBOOKS, NETSUITE, SAP, AVALARA, GOOGLE_DRIVE, BOX
  private boolean connected;
      private LocalDateTime connectedAt;
      @Column(columnDefinition = "TEXT") private String configJson;
      private String status; // ACTIVE, INACTIVE, ERROR
  private String lastSyncMessage;
      private LocalDateTime lastSyncAt;
      @Column(updatable = false) private LocalDateTime createdAt;
      private LocalDateTime updatedAt;
      @PrePersist protected void onCreate() { createdAt = updatedAt = LocalDateTime.now(); }
      @PreUpdate protected void onUpdate() { updatedAt = LocalDateTime.now(); }
  }

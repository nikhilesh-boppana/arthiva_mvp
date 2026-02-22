package com.arthiva.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity @Table(name = "opportunities")
  @Data @NoArgsConstructor @AllArgsConstructor @Builder
  public class Opportunity {
      @Id @GeneratedValue(strategy = GenerationType.UUID)
      private String id;
      @ManyToOne @JoinColumn(name = "company_id", nullable = false)
      private Company company;
      @Column(nullable = false) private String title;
      private String description;
      private String taxType; // SALES_TAX, VAT, EXCISE, INCOME
  @Column(nullable = false) private String status; // DISCOVERED, IN_REVIEW, FILED, RECOVERED, CLOSED
  private BigDecimal estimatedAmount;
      private BigDecimal recoveredAmount;
      private String jurisdiction;
      private LocalDate filingDeadline;
      private LocalDate discoveredDate;
      private LocalDate filedDate;
      private LocalDate recoveredDate;
      @ManyToOne @JoinColumn(name = "assigned_to")
      private User assignedTo;
      private String priority; // LOW, MEDIUM, HIGH, CRITICAL
  private String notes;
      @Column(updatable = false) private LocalDateTime createdAt;
      private LocalDateTime updatedAt;
      @PrePersist protected void onCreate() { createdAt = updatedAt = LocalDateTime.now(); if (discoveredDate == null) discoveredDate = LocalDate.now(); }
      @PreUpdate protected void onUpdate() { updatedAt = LocalDateTime.now(); }
  }

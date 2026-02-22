package com.arthiva.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity @Table(name = "invoices")
  @Data @NoArgsConstructor @AllArgsConstructor @Builder
  public class Invoice {
      @Id @GeneratedValue(strategy = GenerationType.UUID)
      private String id;
      @ManyToOne @JoinColumn(name = "company_id", nullable = false)
      private Company company;
      private String invoiceNumber;
      private String vendorName;
      private BigDecimal amount;
      private BigDecimal taxAmount;
      private String taxType;
      private LocalDate invoiceDate;
      private LocalDate dueDate;
      private String status; // PENDING, ELIGIBLE, INELIGIBLE, FILED, RECOVERED
  private String s3Key; // S3 file reference
  private String eligibilityNotes;
      @ManyToOne @JoinColumn(name = "opportunity_id")
      private Opportunity opportunity;
      private String importBatchId;
      @Column(updatable = false) private LocalDateTime createdAt;
      private LocalDateTime updatedAt;
      @PrePersist protected void onCreate() { createdAt = updatedAt = LocalDateTime.now(); }
      @PreUpdate protected void onUpdate() { updatedAt = LocalDateTime.now(); }
  }

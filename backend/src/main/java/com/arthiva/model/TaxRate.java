package com.arthiva.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity @Table(name = "tax_rates")
  @Data @NoArgsConstructor @AllArgsConstructor @Builder
  public class TaxRate {
      @Id @GeneratedValue(strategy = GenerationType.UUID)
      private String id;
      @ManyToOne @JoinColumn(name = "company_id", nullable = false)
      private Company company;
      @Column(nullable = false) private String jurisdiction;
      @Column(nullable = false) private String taxType;
      @Column(nullable = false) private BigDecimal rate;
      private LocalDate effectiveFrom;
      private LocalDate effectiveTo;
      private boolean active;
      @Column(updatable = false) private LocalDateTime createdAt;
      private LocalDateTime updatedAt;
      @PrePersist protected void onCreate() { createdAt = updatedAt = LocalDateTime.now(); active = true; }
      @PreUpdate protected void onUpdate() { updatedAt = LocalDateTime.now(); }
  }

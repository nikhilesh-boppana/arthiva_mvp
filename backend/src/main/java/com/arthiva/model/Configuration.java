package com.arthiva.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity @Table(name = "configurations")
  @Data @NoArgsConstructor @AllArgsConstructor @Builder
  public class Configuration {
      @Id @GeneratedValue(strategy = GenerationType.UUID)
      private String id;
      @ManyToOne @JoinColumn(name = "company_id", nullable = false)
      private Company company;
      @Column(nullable = false) private String configKey;
      @Column(columnDefinition = "TEXT") private String configValue;
      private String category; // FISCAL_YEAR, DOCUMENT_TEMPLATE, GENERAL
  private String description;
      @Column(updatable = false) private LocalDateTime createdAt;
      private LocalDateTime updatedAt;
      @PrePersist protected void onCreate() { createdAt = updatedAt = LocalDateTime.now(); }
      @PreUpdate protected void onUpdate() { updatedAt = LocalDateTime.now(); }
  }

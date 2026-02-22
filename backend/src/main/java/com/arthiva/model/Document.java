package com.arthiva.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity @Table(name = "documents")
  @Data @NoArgsConstructor @AllArgsConstructor @Builder
  public class Document {
      @Id @GeneratedValue(strategy = GenerationType.UUID)
      private String id;
      @ManyToOne @JoinColumn(name = "company_id", nullable = false)
      private Company company;
      @Column(nullable = false) private String fileName;
      private String fileType; // PDF, CSV, XLSX
  private String s3Key;
      private Long fileSize;
      private String category; // INVOICE, TAX_FORM, REPORT, TEMPLATE
  @ManyToOne @JoinColumn(name = "opportunity_id")
      private Opportunity opportunity;
      @ManyToOne @JoinColumn(name = "uploaded_by")
      private User uploadedBy;
      @Column(updatable = false) private LocalDateTime createdAt;
      @PrePersist protected void onCreate() { createdAt = LocalDateTime.now(); }
  }

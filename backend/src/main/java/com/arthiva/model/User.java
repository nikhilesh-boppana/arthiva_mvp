package com.arthiva.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity @Table(name = "users")
  @Data @NoArgsConstructor @AllArgsConstructor @Builder
  public class User {
      @Id @GeneratedValue(strategy = GenerationType.UUID)
      private String id;
      @Column(nullable = false, unique = true) private String email;
      @Column(nullable = false) private String passwordHash;
      private String fullName;
      private String role; // ADMIN, MANAGER, ANALYST
  @ManyToOne @JoinColumn(name = "company_id")
      private Company company;
      @Column(updatable = false) private LocalDateTime createdAt;
      private LocalDateTime updatedAt;
      @PrePersist protected void onCreate() { createdAt = updatedAt = LocalDateTime.now(); }
      @PreUpdate protected void onUpdate() { updatedAt = LocalDateTime.now(); }
  }

# Arthiva MVP

A Tax Recovery Pipeline Management platform built with Java Spring Boot + React + MySQL + AWS S3.

## Tech Stack

- **Backend**: Java 17, Spring Boot 3.2, Spring Data JPA, Spring Security + JWT
- **Frontend**: React 18, React Router v6, Recharts, TailwindCSS
- **Database**: MySQL 8
- **Storage**: AWS S3 (invoices, documents, CSV imports)

## Quick Start

### 1. Database
CREATE DATABASE arthiva_db;

### 2. Backend
cd backend && ./mvnw spring-boot:run

### 3. Frontend
cd frontend && npm install && npm start

### 4. Docker
docker-compose up --build

## Features
- Dashboard: KPI cards, pipeline chart, cash flow forecast
- Opportunities: Full lifecycle (Discovered > In Review > Filed > Recovered)
- Data/Invoices: CSV import via S3, eligibility review
- Configurations: Tax rates, fiscal year, document templates
- Integrations: QuickBooks, NetSuite, SAP, Avalara, Google Drive, Box

## API Base URL
http://localhost:8080/api

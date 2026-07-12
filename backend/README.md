# BlockSign Backend - Spring Boot Microservices

A blockchain-based document signing platform built with Spring Boot microservices architecture.

## Architecture

### Services

| Service | Port | Description |
|---------|------|-------------|
| config-server | 8888 | Spring Cloud Config Server |
| auth-service | 8086 | User authentication and JWT token management |
| api-gateway | 8080 | API Gateway with routing and authentication |
| document-service | 8081 | Document management and storage |
| signing-service | 8082 | Contract signing workflow |
| notification-service | 8083 | Email notifications |
| blockchain-service | 8084 | Ethereum blockchain integration |
| audit-service | 8085 | Audit trail and event logging |

### Infrastructure

- **PostgreSQL 16** - Database for all services
- **Kafka** - Event streaming for service communication
- **Zookeeper** - Kafka coordination

## Prerequisites

- Java 17 or 21
- Maven 3.8+
- Docker & Docker Compose
- PostgreSQL (if running locally without Docker)
- Kafka (if running locally without Docker)

## Quick Start with Docker

### 1. Clone and Navigate

```bash
cd backend
```

### 2. Build and Start All Services

```bash
docker-compose up -d
```

This will start:
- PostgreSQL with 4 databases
- Kafka and Zookeeper
- All 7 microservices

### 3. Verify Services

```bash
# Check all services are running
docker-compose ps

# View logs
docker-compose logs -f

# Check specific service logs
docker-compose logs -f document-service
```

### 4. Stop Services

```bash
docker-compose down
```

## Manual Development Setup

### 1. Start Infrastructure

```bash
# Start PostgreSQL and Kafka
docker-compose up -d postgres zookeeper kafka
```

### 2. Start Config Server

```bash
cd config-server
mvn spring-boot:run
```

### 3. Start Services (in order)

```bash
# Terminal 1
cd document-service
mvn spring-boot:run

# Terminal 2
cd signing-service
mvn spring-boot:run

# Terminal 3
cd notification-service
mvn spring-boot:run

# Terminal 4
cd blockchain-service
mvn spring-boot:run

# Terminal 5
cd audit-service
mvn spring-boot:run

# Terminal 6
cd auth-service
mvn spring-boot:run

# Terminal 7
cd api-gateway
mvn spring-boot:run
```

## Service Endpoints

### Auth Service (http://localhost:8086)

- `POST /api/auth/login` - User login
- `POST /api/auth/register` - User registration
- `POST /api/auth/logout` - User logout
- `GET /api/auth/me` - Get current user info

### API Gateway (http://localhost:8080)

- Routes all requests to appropriate services
- Handles CORS configuration
- JWT token validation
- `GET /api/documents/**` - Document routes
- `GET /api/sign/**` - Signing routes
- `GET /api/notifications/**` - Notification routes
- `GET /api/blockchain/**` - Blockchain routes
- `GET /api/audit/**` - Audit routes

### Document Service (http://localhost:8081)

- `GET /api/documents/presigned-url` - Generate S3 upload URL
- `POST /api/documents/register` - Register document
- `GET /api/documents` - List documents
- `GET /api/documents/{id}` - Get document details
- `DELETE /api/documents/{id}` - Delete document

### Signing Service (http://localhost:8082)

- `POST /api/sign/sign` - Sign document
- `POST /api/sign/decline` - Decline document
- `GET /api/sign/status/{contractId}` - Get signing status

### Blockchain Service (http://localhost:8084)

- `POST /api/blockchain/submit` - Submit hash to blockchain
- `GET /api/blockchain/verify/{contractId}` - Verify document hash
- `GET /api/blockchain/transaction/{contractId}` - Get blockchain record

### Audit Service (http://localhost:8085)

- `GET /api/audit/{contractId}` - Get audit trail

## Configuration

### Environment Variables

Create a `.env` file in the backend directory:

```env
# Database
POSTGRES_USER=postgres
POSTGRES_PASSWORD=postgres
POSTGRES_HOST=postgres
POSTGRES_PORT=5432

# Blockchain
INFURA_URL=https://sepolia.infura.io/v3/YOUR_KEY
BLOCKCHAIN_PRIVATE_KEY=your-private-key
BLOCKCHAIN_NETWORK=sepolia

# AWS S3
AWS_ACCESS_KEY_ID=your-access-key
AWS_SECRET_ACCESS_KEY=your-secret-key
AWS_REGION=us-east-1
S3_BUCKET_NAME=blocksign-documents

# Email
MAIL_HOST=smtp.gmail.com
MAIL_PORT=587
MAIL_USERNAME=your-email@gmail.com
MAIL_PASSWORD=your-app-password

# Security
JWT_SECRET=your-jwt-secret-key
ENCRYPT_KEY=your-encrypt-key
```

### Service-Specific Configuration

Each service has its own `application.yml` in `src/main/resources/`. Key configurations:

- **Server Port**: Each service runs on a different port
- **Database**: PostgreSQL connection settings
- **Kafka**: Bootstrap servers for event streaming
- **Actuator**: Health check endpoints enabled

## Development

### Building All Services

```bash
mvn clean install
```

### Building Individual Service

```bash
cd document-service
mvn clean package
```

### Running Tests

```bash
mvn test
```

### Code Style

The project uses Lombok for reducing boilerplate code. Ensure your IDE has the Lombok plugin installed.

## Production Deployment

### Using Docker Compose Production

```bash
docker-compose -f docker-compose.prod.yml up -d
```

### Environment Variables for Production

Set the following environment variables before running production:

- `POSTGRES_USER`, `POSTGRES_PASSWORD` - Database credentials
- `INFURA_URL` - Infura Ethereum endpoint
- `BLOCKCHAIN_PRIVATE_KEY` - Wallet private key
- `AWS_ACCESS_KEY_ID`, `AWS_SECRET_ACCESS_KEY` - AWS credentials
- `MAIL_USERNAME`, `MAIL_PASSWORD` - SMTP credentials
- `JWT_SECRET` - JWT signing secret
- `ENCRYPT_KEY` - Config server encryption key

### Recommended Production Infrastructure

- **Database**: Use managed PostgreSQL (Render, RDS, Neon)
- **Kafka**: Use managed Kafka (Confluent Cloud, MSK)
- **S3**: Use AWS S3 or equivalent
- **Blockchain**: Use Infura or Alchemy for Ethereum RPC

## Monitoring

### Health Checks

All services expose health endpoints:

- `http://localhost:8086/actuator/health` - Auth Service
- `http://localhost:8080/actuator/health` - API Gateway
- `http://localhost:8081/actuator/health` - Document Service
- `http://localhost:8082/actuator/health` - Signing Service
- `http://localhost:8084/actuator/health` - Blockchain Service
- `http://localhost:8085/actuator/health` - Audit Service

View all service health:

```bash
curl http://localhost:8080/actuator/health
```

## Troubleshooting

### Services Won't Start

1. Check PostgreSQL is running: `docker-compose ps postgres`
2. Check Kafka is running: `docker-compose ps kafka`
3. View service logs: `docker-compose logs <service-name>`

### Connection Refused Errors

- Ensure all services are up: `docker-compose ps`
- Check network connectivity between containers
- Verify port mappings aren't conflicting

### Database Connection Errors

- Verify PostgreSQL is accepting connections
- Check database credentials in environment variables
- Ensure databases exist: `blocksign_docs`, `blocksign_signing`, `blockchain_blockchain`, `blocksign_audit`

### Kafka Connection Errors

- Verify Kafka and Zookeeper are running
- Check bootstrap servers configuration
- Ensure topics are auto-created or manually create them

## API Documentation

Once services are running, you can access the API through the gateway at `http://localhost:8080`.

## License

Proprietary - BlockSign

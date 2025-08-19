# Microservices Assignment

This repository contains a **monorepo** with 4 Spring Boot microservices and a Postgres database, built as part of the assignment.  
Each microservice performs a specific task, and together they demonstrate inter-service communication, orchestration, and persistence.

---

## 📂 Project Structure

```
microservices-assignment/
├── docker-compose.yml        # Docker Compose setup for all services + Postgres
├── db/
│   └── init.sql              # Database schema & seed data for Postgres
├── service-1-orchestrator/   # Orchestrator service
│   ├── Dockerfile
│   ├── pom.xml
│   └── src/main/java/com/example/service_1/
│
├── service-2-hello/          # Service 2: returns "Hello"
│   ├── Dockerfile
│   ├── pom.xml
│   └── src/main/java/com/example/service_2/
│
├── service-3-name/           # Service 3: builds full name from request
│   ├── Dockerfile
│   ├── pom.xml
│   └── src/main/java/com/example/service_3/
│
├── service-4-character/        # Service 4: fetches class hierarchy from Postgres
│   ├── Dockerfile
│   ├── pom.xml
│   └── src/main/java/com/example/service_4/
└── README.md
```

---

## 🛠️ Services Overview

1. **Service-1 Orchestrator**
    - Endpoint: `/combine`
    - Orchestrates calls to **service-2** and **service-3**
    - Combines `"Hello"` + `"Full Name"`

2. **Service-2 Hello**
    - Endpoint: `/hello`
    - Returns `"Hello"`

3. **Service-3 Name**
    - Endpoint: `/fullname`
    - Accepts JSON `{ "name": "John", "surname": "Doe" }`
    - Returns `"John Doe"`

4. **Service-4 Character**
    - Endpoint: `/character`
    - Connects to **Postgres**
    - Returns predefined class hierarchy

---

## ▶️ Running the Project Locally

### 1. Clone the repository
```bash
git clone https://github.com/hiteshkmr75/microservices-assignment.git
cd microservices-assignment
```

### 2. Start Postgres locally (if you’re not using Docker)
```bash
docker run --name character-db -e POSTGRES_DB=characterdb -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgres:14-alpine
```

### 3. Build each service
From each service directory:
```bash
mvn clean package -DskipTests
```

### 4. Run services
```bash
cd service-2-hello && mvn spring-boot:run
cd service-3-name && mvn spring-boot:run
cd service-4-character && mvn spring-boot:run
cd service-1-orchestrator && mvn spring-boot:run
```

---

## 🐳 Running with Docker Compose

### 1. Build & start all services + Postgres
```bash
docker compose up --build
```

### 2. Access APIs
- Orchestrator: <http://localhost:8081/health>
- Hello: <http://localhost:8082/hello>
- Name: `POST http://localhost:8083/fullname`
- Character: <http://localhost:8084/character>

### Example requests
```bash
# Health check
curl http://localhost:8081/health

# Combine API
curl -X POST http://localhost:8081/combine   -H "Content-Type: application/json"   -d '{"name":"John","surname":"Doe"}'

# Hello service
curl http://localhost:8082/hello

# Name service
curl -X POST http://localhost:8083/fullname   -H "Content-Type: application/json"   -d '{"name":"John","surname":"Doe"}'

# Character service
curl http://localhost:8084/character
```

---

## ☁️ Deploying to AWS EKS

**Note**: AWS EKS deployment was not covered in this assignment due to the **expired AWS Free Tier access**.  
However, the steps to containerize, push to Amazon ECR, and deploy on EKS have been described conceptually in class and can be applied here if AWS access is available.

### 1. Build and push Docker images to ECR
```bash
# Authenticate Docker to ECR
aws ecr get-login-password --region <your-region> | docker login --username AWS --password-stdin <account-id>.dkr.ecr.<your-region>.amazonaws.com

# Build and tag images
docker build -t service-1-orchestrator ./service-1-orchestrator
docker tag service-1-orchestrator:latest <account-id>.dkr.ecr.<region>.amazonaws.com/service-1-orchestrator:latest

# Repeat for service-2, service-3, service-4
# Push images
docker push <account-id>.dkr.ecr.<region>.amazonaws.com/service-1-orchestrator:latest
```

### 2. Create an EKS cluster
```bash
eksctl create cluster --name microservices-cluster --region <your-region> --nodes 3
```

### 3. Apply Kubernetes manifests
Create a `k8s/` directory with:
- `deployment.yaml` for each service
- `service.yaml` for NodePort/LoadBalancer
- `postgres-deployment.yaml` + `postgres-service.yaml`

Apply manifests:
```bash
kubectl apply -f k8s/
```

### 4. Verify pods & services
```bash
kubectl get pods
kubectl get svc
```

### 5. Access APIs
Use the **LoadBalancer URL** or **NodePort** provided by Kubernetes.

---

## ✅ Summary

- You can run services **locally** via Maven or with **Docker Compose**
- DB schema is auto-loaded from `db/init.sql`
- Deployment ready for **AWS EKS** with ECR-hosted Docker images

---
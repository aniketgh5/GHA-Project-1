# LearnWithAniket Java Docker App

Welcome to my personal Java + Docker project.

This repository contains a simple Spring Boot web application created for learning, practicing, and demonstrating DevOps workflows such as Maven builds, Docker image creation, and GitHub Actions automation.

## About Me

Hi, I am Aniket.

I share DevOps, cloud, CI/CD, Docker, Kubernetes, and automation learning content through **LearnWithAniket**.

Connect with me on LinkedIn:

https://www.linkedin.com/in/aniket-devops/

## Project Overview

This application serves a small interactive web page from a Spring Boot backend. It is useful for testing:

- Java application builds with Maven
- Docker image creation
- GitHub Actions CI/CD pipelines
- Container deployment workflows
- Basic Spring Boot health checks

## Tech Stack

- Java 17
- Spring Boot
- Maven
- Docker
- GitHub Actions

## Run Locally

Build the application:

```bash
mvn clean package
```

Run the jar:

```bash
java -jar target/java-docker-app-1.0.0.jar
```

Open the application:

```text
http://localhost:8080
```

Check application health:

```bash
curl http://localhost:8080/actuator/health
```

## Run With Docker

Build the Docker image:

```bash
docker build -t learnwithaniket/java-docker-app .
```

Run the container:

```bash
docker run -p 8080:8080 learnwithaniket/java-docker-app
```

Open:

```text
http://localhost:8080
```

## GitHub Actions

This repository can be used to practice CI/CD pipelines with GitHub Actions, including:

- Building Java applications
- Running Maven checks
- Creating Docker images
- Pushing images to a container registry
- Preparing deployment workflows

## Author

**LearnWithAniket**

LinkedIn: https://www.linkedin.com/in/aniket-devops/

Keep learning. Keep building. Keep automating.

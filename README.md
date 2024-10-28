# SPRING-QUIZ

Project Overview
SPRINGQUIZ is a Spring Boot-based application designed to manage quizzes and questions with features for user authentication and authorization. The application offers a complete CRUD (Create, Read, Update, Delete) system for questions and quizzes, secured with role-based access control.

## Table of Contents

- [Technologies Used](#technologies-used)
- [Features](#features)
- [API Documentation](#api-documentation)
- [CI/CD Pipeline](#cicd-pipeline)
- [License](#license)

## Technologies Used
- Backend: Spring Boot, JPA, Hibernate, MySQL
- Security: Bcrypt for Password encoder,JWT for authentication and role-based authorization
- CI/CD: Jenkins, Docker, Render
- Other Tools: Aiven (database hosting), Ngrok (for testing webhook connections), GitHub (version control).
## Features
- User Authentication & Authorization: Secure login and access control using JWT, with role-based permissions for Admin and User roles.
  CRUD Operations:
- Questions: Full CRUD capabilities for creating, viewing, updating, and deleting questions.
- Answers: Link answers to specific questions.
- Quizzes: Organize questions into quizzes.
- Custom Exception Handling: Centralized error handling with custom code and messages for specific exceptions.
- Automated CI/CD: Uses Jenkins for continuous integration and deployment to Render.

## API Documentation
Swagger UI provides detailed API documentation and is available at:

https://spring-quiz-app.onrender.com/swagger-ui/index.html

## CI/CD Pipeline
The CI/CD pipeline is configured with Jenkins and integrates GitHub for automated deployment on Render. Below is a breakdown of the pipeline stages:

- Build: Maven is used to compile and build the project.
- Test: Unit tests are executed to ensure code quality.
- Dockerize: The application is containerized by creating a Docker image.
- Push Image: The Docker image is pushed to Docker Hub.
- Deploy: Render is triggered to pull the Docker image and deploy the latest version.

## Deployment
The application is deployed on Render, which automatically pulls updates from Docker Hub whenever a new image is pushed. Use Jenkins to automate the deployment process whenever changes are merged or pushed to the main branch.
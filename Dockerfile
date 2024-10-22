FROM openjdk:17

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]

FROM jenkins/jenkins:lts

# Cài đặt Docker CLI
USER root
RUN apt-get update && apt-get install -y docker.io

# Đặt lại quyền sở hữu cho thư mục Jenkins
RUN chown -R jenkins:jenkins /var/jenkins_home

USER jenkins
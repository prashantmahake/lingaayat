# FROM alpine/git
# WORKDIR /app
# RUN git clone https://github.com/spring-projects/spring-petclinic.git

# FROM maven:3.5-jdk-8-alpine
# WORKDIR /app
# COPY . /app
# RUN ls
# RUN mvn package -DskipTests

FROM openjdk:8-jre-alpine
WORKDIR /app
COPY ./target/we-0.0.1-SNAPSHOT.jar /app
RUN ls
RUN java -version
CMD ["java","-jar", "we-0.0.1-SNAPSHOT.jar"]
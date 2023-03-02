FROM eclipse-temurin:17-jdk-focal

RUN apt update && \
    apt install -y dos2unix && \
    apt clean
WORKDIR /app
COPY . /app/
RUN dos2unix mvnw
RUN chmod +x mvnw
RUN ./mvnw install
RUN ./mvnw compile
CMD ["./mvnw", "spring-boot:run"]
############################
# Stage 1 — Build
############################
FROM eclipse-temurin:21-jdk AS builder

WORKDIR /build

# Copia arquivos básicos primeiro (melhora cache)
COPY gradlew .
COPY gradle gradle
COPY build.gradle.kts settings.gradle.kts ./

# Garante permissão
RUN chmod +x gradlew

# Baixa dependências (cache)
RUN ./gradlew dependencies --no-daemon || true

# Copia o código fonte
COPY src src

# Build do jar Spring Boot
RUN ./gradlew clean bootJar --no-daemon


############################
# Stage 2 — Runtime
############################
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copia apenas o jar final
COPY --from=builder /build/build/libs/*.jar app.jar

# JVM otimizada para container
ENV JAVA_OPTS="-XX:+UseContainerSupport \
               -XX:MaxRAMPercentage=75 \
               -XX:+ExitOnOutOfMemoryError \
               -Duser.timezone=UTC"

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]

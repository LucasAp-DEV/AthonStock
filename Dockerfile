FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY target/System01.jar /app/System01.jar

RUN apk update && \
    apk add tzdata && \
    cp /usr/share/zoneinfo/America/Sao_Paulo /etc/localtime && \
    echo "America/Sao_Paulo" > /etc/timezone && \
    apk del tzdata

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "System01.jar"]
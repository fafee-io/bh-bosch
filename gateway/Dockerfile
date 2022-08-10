FROM eclipse-temurin:17-jdk-focal

ARG JAR_FILE=target/gateway*.jar
COPY ${JAR_FILE} app.jar
COPY entrypoint.sh .
VOLUME /tmp
RUN chmod +x /entrypoint.sh

CMD ["./entrypoint.sh"]

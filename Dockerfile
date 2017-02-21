FROM maven:3-jdk-8

WORKDIR /app

ADD pom.xml /app
RUN mvn verify clean --fail-never

ADD . /app
RUN mvn package

EXPOSE 8082
CMD ["sh", "/app/target/bin/webapp"]
FROM openjdk:8-jdk as ar4k-builder
RUN apt-get update -y && apt-get install curl gnupg bash sed grep coreutils wget -y
COPY . /ar4kAgent
WORKDIR /ar4kAgent
RUN chmod +x gradlew
RUN ./gradlew clean bootJar -Dorg.gradle.jvmargs="-Xms512M -Xmx4G" --info

FROM openjdk:8-jdk-alpine
RUN apk add --no-cache bash gawk sed grep bc coreutils wget binutils
COPY --from=ar4k-builder /ar4kAgent/build/libs/*.jar /ar4kAgent.jar
ENTRYPOINT ["java"]
CMD ["-XX:+UnlockExperimentalVMOptions","-Djava.net.preferIPv4Stack=true","-XX:+UseCGroupMemoryLimitForHeap","-XshowSettings:vm","-Djava.security.egd=file:/dev/./urandom","-jar","/ar4kAgent.jar"]

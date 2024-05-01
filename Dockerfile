FROM gradle:7.6.1-alpine as gradleimage
COPY . /home/gradle/source
WORKDIR /home/gradle/source
RUN gradle bootJar

FROM openjdk:17-alpine
COPY --from=gradleimage /home/gradle/source/build/libs/server-0.0.1-SNAPSHOT.jar server.jar

VOLUME /tmp
ENTRYPOINT sh -c "java \
-Dspring.profiles.active=base \
-Duser.timezone=Europe/Moscow \
-XX:+UseG1GC \
-Xmx512M \
-XX:MaxMetaspaceSize=256m \
-XX:ReservedCodeCacheSize=128m \
-XX:MaxDirectMemorySize=128m \
-XX:+PerfDisableSharedMem \
-XX:+HeapDumpOnOutOfMemoryError \
-XX:+ExitOnOutOfMemoryError \
-XX:+AlwaysActAsServerClassMachine \
-XX:+UseStringDeduplication \
-XX:+UnlockExperimentalVMOptions \
-XX:+PrintGCDetails \
-Xloggc:gc.log \
-Xdebug \
-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005 \
-Dcom.sun.management.jmxremote \
-Djava.rmi.server.hostname=$HOST_NAME \
-Dcom.sun.management.jmxremote.port=9010 \
-Dcom.sun.management.jmxremote.rmi.port=9010 \
-Dcom.sun.management.jmxremote.local.only=false \
-Dcom.sun.management.jmxremote.authenticate=false \
-Dcom.sun.management.jmxremote.ssl=false \
-jar \
server.jar"

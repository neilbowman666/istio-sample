FROM openjdk:11.0.2-jre-slim
COPY target/module-b-1.0.0.jar .
CMD /usr/bin/java -Xmx300m -Xms300m -XX:TieredStopAtLevel=1 -noverify -jar module-b-1.0.0.jar
EXPOSE 8000

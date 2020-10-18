FROM adoptopenjdk/openjdk11:alpine

RUN apk add --no-cache "curl>=7.64.0" "jq>=1.6" "tini>=0.18.0"

COPY target/bankstatementanalyser-*.jar /opt/bankstatementanalyser/bankstatementanalyser.jar
COPY entrypoint.sh /opt/bankstatementanalyser/

EXPOSE 8080

WORKDIR /opt/bankstatementanalyser

ENTRYPOINT ["/sbin/tini", "--", "/opt/bankstatementanalyser/entrypoint.sh"]
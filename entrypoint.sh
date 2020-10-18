#!/bin/sh

exec java -Dinst.type=bankstatementanalyser \
    -Dbankstatementanalyser.inst.id=$HOSTNAME \
    ${JVM_PARAMETERS:-} \
    ${JAVA_OPTS:-} \
    ${JAVA_OPTS_GC:-} \
    ${JAVA_OPTS_MEM:-} \
    ${JAVA_OPTS_DEBUG:-} \
    -XX:OnOutOfMemoryError='kill -9 %p' \
    -XX:+HeapDumpOnOutOfMemoryError \
    -XX:HeapDumpPath=/dumps/bankstatementanalyser.bin \
    -jar bankstatementanalyser.jar \
    $@
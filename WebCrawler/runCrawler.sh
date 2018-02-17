#!/bin/bash
CLASSPATH=.

CLASSPATH=$CLASSPATH:/Users/sts/.m2/repository/com/j2core/sts/webcrawler/microservice/crawler/1.0-SNAPSHOT/crawler-1.0-SNAPSHOT.jar
CLASSPATH=$CLASSPATH:/Users/sts/.m2/repository/com/j2core/sts/webcrawler/microservice/dao/1.0-SNAPSHOT/dao-1.0-SNAPSHOT.jar


CLASSPATH=$CLASSPATH:/Users/sts/.m2/repository/org/glassfish/jersey/core/jersey-client/2.25.1/jersey-client-2.25.1.jar
CLASSPATH=$CLASSPATH:/Users/sts/.m2/repository/javax/ws/rs/javax.ws.rs-api/2.0.1/javax.ws.rs-api-2.0.1.jar
CLASSPATH=$CLASSPATH:/Users/sts/.m2/repository/org/glassfish/jersey/core/jersey-common/2.25.1/jersey-common-2.25.1.jar
CLASSPATH=$CLASSPATH:/Users/sts/.m2/repository/javax/annotation/javax.annotation-api/1.2/javax.annotation-api-1.2.jar
CLASSPATH=$CLASSPATH:/Users/sts/.m2/repository/org/glassfish/jersey/bundles/repackaged/jersey-guava/2.25.1/jersey-guava-2.25.1.jar
CLASSPATH=$CLASSPATH:/Users/sts/.m2/repository/org/glassfish/hk2/osgi-resource-locator/1.0.1/osgi-resource-locator-1.0.1.jar
CLASSPATH=$CLASSPATH:/Users/sts/.m2/repository/org/glassfish/hk2/hk2-api/2.5.0-b32/hk2-api-2.5.0-b32.jar
CLASSPATH=$CLASSPATH:/Users/sts/.m2/repository/org/glassfish/hk2/hk2-utils/2.5.0-b32/hk2-utils-2.5.0-b32.jar
CLASSPATH=$CLASSPATH:/Users/sts/.m2/repository/org/glassfish/hk2/external/aopalliance-repackaged/2.5.0-b32/aopalliance-repackaged-2.5.0-b32.jar
CLASSPATH=$CLASSPATH:/Users/sts/.m2/repository/org/glassfish/hk2/external/javax.inject/2.5.0-b32/javax.inject-2.5.0-b32.jar
CLASSPATH=$CLASSPATH:/Users/sts/.m2/repository/org/glassfish/hk2/hk2-locator/2.5.0-b32/hk2-locator-2.5.0-b32.jar
CLASSPATH=$CLASSPATH:/Users/sts/.m2/repository/org/glassfish/jersey/media/jersey-media-json-jackson/2.25.1/jersey-media-json-jackson-2.25.1.jar
CLASSPATH=$CLASSPATH:/Users/sts/.m2/repository/org/glassfish/jersey/ext/jersey-entity-filtering/2.25.1/jersey-entity-filtering-2.25.1.jar
CLASSPATH=$CLASSPATH:/Users/sts/.m2/repository/com/fasterxml/jackson/jaxrs/jackson-jaxrs-base/2.8.4/jackson-jaxrs-base-2.8.4.jar
CLASSPATH=$CLASSPATH:/Users/sts/.m2/repository/com/fasterxml/jackson/core/jackson-core/2.8.4/jackson-core-2.8.4.jar
CLASSPATH=$CLASSPATH:/Users/sts/.m2/repository/com/fasterxml/jackson/core/jackson-databind/2.8.4/jackson-databind-2.8.4.jar
CLASSPATH=$CLASSPATH:/Users/sts/.m2/repository/com/fasterxml/jackson/jaxrs/jackson-jaxrs-json-provider/2.8.4/jackson-jaxrs-json-provider-2.8.4.jar
CLASSPATH=$CLASSPATH:/Users/sts/.m2/repository/com/fasterxml/jackson/module/jackson-module-jaxb-annotations/2.8.4/jackson-module-jaxb-annotations-2.8.4.jar
CLASSPATH=$CLASSPATH:/Users/sts/.m2/repository/com/fasterxml/jackson/core/jackson-annotations/2.8.4/jackson-annotations-2.8.4.jar
CLASSPATH=$CLASSPATH:/Users/sts/.m2/repository/javax/persistence/persistence-api/1.0.2/persistence-api-1.0.2.jar
CLASSPATH=$CLASSPATH:/Users/sts/.m2/repository/org/hibernate/common/hibernate-commons-annotations/5.0.1.Final/hibernate-commons-annotations-5.0.1.Final.jar
CLASSPATH=$CLASSPATH:/Users/sts/.m2/repository/org/jboss/logging/jboss-logging/3.3.0.Final/jboss-logging-3.3.0.Final.jar
CLASSPATH=$CLASSPATH:/Users/sts/.m2/repository/org/hibernate/javax/persistence/hibernate-jpa-2.1-api/1.0.0.Final/hibernate-jpa-2.1-api-1.0.0.Final.jar
CLASSPATH=$CLASSPATH:/Users/sts/.m2/repository/org/hibernate/hibernate-core/5.2.2.Final/hibernate-core-5.2.2.Final.jar
CLASSPATH=$CLASSPATH:/Users/sts/.m2/repository/org/javassist/javassist/3.20.0-GA/javassist-3.20.0-GA.jar
CLASSPATH=$CLASSPATH:/Users/sts/.m2/repository/antlr/antlr/2.7.7/antlr-2.7.7.jar
CLASSPATH=$CLASSPATH:/Users/sts/.m2/repository/org/apache/geronimo/specs/geronimo-jta_1.1_spec/1.1.1/geronimo-jta_1.1_spec-1.1.1.jar
CLASSPATH=$CLASSPATH:/Users/sts/.m2/repository/org/jboss/jandex/2.0.0.Final/jandex-2.0.0.Final.jar
CLASSPATH=$CLASSPATH:/Users/sts/.m2/repository/com/fasterxml/classmate/1.3.0/classmate-1.3.0.jar
CLASSPATH=$CLASSPATH:/Users/sts/.m2/repository/dom4j/dom4j/1.6.1/dom4j-1.6.1.jar
CLASSPATH=$CLASSPATH:/Users/sts/.m2/repository/javax/enterprise/cdi-api/1.1/cdi-api-1.1.jar
CLASSPATH=$CLASSPATH:/Users/sts/.m2/repository/javax/el/el-api/2.2/el-api-2.2.jar
CLASSPATH=$CLASSPATH:/Users/sts/.m2/repository/org/jboss/spec/javax/interceptor/jboss-interceptors-api_1.1_spec/1.0.0.Beta1/jboss-interceptors-api_1.1_spec-1.0.0.Beta1.jar
CLASSPATH=$CLASSPATH:/Users/sts/.m2/repository/javax/annotation/jsr250-api/1.0/jsr250-api-1.0.jar
CLASSPATH=$CLASSPATH:/Users/sts/.m2/repository/javax/inject/javax.inject/1/javax.inject-1.jar
CLASSPATH=$CLASSPATH:/Users/sts/.m2/repository/org/hibernate/hibernate-entitymanager/5.2.6.Final/hibernate-entitymanager-5.2.6.Final.jar
CLASSPATH=$CLASSPATH:/Users/sts/.m2/repository/net/bytebuddy/byte-buddy/1.5.4/byte-buddy-1.5.4.jar
CLASSPATH=$CLASSPATH:/Users/sts/.m2/repository/mysql/mysql-connector-java/5.1.39/mysql-connector-java-5.1.39.jar
CLASSPATH=$CLASSPATH:/Users/sts/.m2/repository/log4j/log4j/1.2.17/log4j-1.2.17.jar
CLASSPATH=$CLASSPATH:/Users/sts/.m2/repository/org/slf4j/slf4j-log4j12/1.7.21/slf4j-log4j12-1.7.21.jar
CLASSPATH=$CLASSPATH:/Users/sts/.m2/repository/org/slf4j/slf4j-api/1.7.21/slf4j-api-1.7.21.jar
CLASSPATH=$CLASSPATH:/Users/sts/.m2/repository/commons-codec/commons-codec/1.6/commons-codec-1.6.jar
CLASSPATH=$CLASSPATH:/Users/sts/.m2/repository/com/google/guava/guava/21.0/guava-21.0.jar
CLASSPATH=$CLASSPATH:/Users/sts/.m2/repository/org/jsoup/jsoup/1.8.3/jsoup-1.8.3.jar
CLASSPATH=$CLASSPATH:/Users/sts/.m2/repository/com/google/code/findbugs/findbugs/3.0.1/findbugs-3.0.1.jar
CLASSPATH=$CLASSPATH:/Users/sts/.m2/repository/net/jcip/jcip-annotations/1.0/jcip-annotations-1.0.jar
CLASSPATH=$CLASSPATH:/Users/sts/.m2/repository/com/google/code/findbugs/jsr305/2.0.1/jsr305-2.0.1.jar
CLASSPATH=$CLASSPATH:/Users/sts/.m2/repository/com/google/code/findbugs/bcel-findbugs/6.0/bcel-findbugs-6.0.jar
CLASSPATH=$CLASSPATH:/Users/sts/.m2/repository/com/google/code/findbugs/jFormatString/2.0.1/jFormatString-2.0.1.jar
CLASSPATH=$CLASSPATH:/Users/sts/.m2/repository/org/ow2/asm/asm-debug-all/5.0.2/asm-debug-all-5.0.2.jar
CLASSPATH=$CLASSPATH:/Users/sts/.m2/repository/org/ow2/asm/asm-commons/5.0.2/asm-commons-5.0.2.jar
CLASSPATH=$CLASSPATH:/Users/sts/.m2/repository/org/ow2/asm/asm-tree/5.0.2/asm-tree-5.0.2.jar
CLASSPATH=$CLASSPATH:/Users/sts/.m2/repository/org/ow2/asm/asm/5.0.2/asm-5.0.2.jar
CLASSPATH=$CLASSPATH:/Users/sts/.m2/repository/commons-lang/commons-lang/2.6/commons-lang-2.6.jar
CLASSPATH=$CLASSPATH:/Users/sts/.m2/repository/com/apple/AppleJavaExtensions/1.4/AppleJavaExtensions-1.4.jar
CLASSPATH=$CLASSPATH:/Users/sts/.m2/repository/jaxen/jaxen/1.1.6/jaxen-1.1.6.jar

export CLASSPATH

java com.j2core.sts.webcrawler.crawler.Application





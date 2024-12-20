<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE log4j:configuration PUBLIC "-//APACHE//DTD LOG4J 1.2//EN" "log4j.dtd">

<log4j:configuration xmlns:log4j="http://jakarta.apache.org/log4j/">

	<!-- Appenders -->
	<appender name="console" class="org.apache.log4j.ConsoleAppender">
		<param name="Target" value="System.out" />
		<layout class="org.apache.log4j.PatternLayout">
			<param name="ConversionPattern" value="[%-5p] %c - %m%n" />
		</layout>
	</appender>
	
	<appender name="fileAppender" class="org.apache.log4j.FileAppender">
		<param name="file" value="C:/workspace/log/myapp.log" />
		<param name="append" value="true" /><!-- 파일을 남기기 위해 찍는다 -->
		<layout class="org.apache.log4j.PatternLayout">
			<param name="ConversionPattern" value="[%-5p] %d{yyyy-MM-dd HH:mm:ss} %c - %m%n" />
		</layout>
	</appender>
	
	<!-- Root Logger -->
	<root>
		<priority value="info" />
		<!-- <priority value="error" /> --> <!-- 이걸 사용하고 싶지 않은 경우 error로 표기해주면 됨 -->
		<appender-ref ref="console" />
		<appender-ref ref="fileAppender" />
	</root>
	
</log4j:configuration>




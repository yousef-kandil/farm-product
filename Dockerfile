# المرحلة الأولى: بناء المشروع باستخدام Maven وجافا 17
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# المرحلة الثانية: تشغيل المشروع بأخف نسخة جافا ممكنة
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
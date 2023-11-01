# Imagenya java 8
FROM openjdk:8-jdk-alpine

# Workdir dibuat /app
WORKDIR /app

# Salin file .env ke dalam container
COPY .env /app/.env

# Salin file jar ke dalam container di lokasi `/app`
COPY target/my-app.jar /app/my-app.jar
COPY lib/*.jar /app/lib/

# Ekspose port 
EXPOSE 8081

# Jalankan aplikasi
CMD ["java", "-cp", "/app/lib/*:/app/my-app.jar", "org.example.Main"]

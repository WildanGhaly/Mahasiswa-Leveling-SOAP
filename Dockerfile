# Imagenya java 8
FROM openjdk:8-jdk-alpine

# Workdir dibuat /app
WORKDIR /app

# Salin file .env ke dalam container
COPY .env /app/.env

# Salin file jar ke dalam container di lokasi `/app`
COPY target/SOAP_Service-1.0-SNAPSHOT.jar /app/SOAP_Service-1.0-SNAPSHOT.jar
COPY lib/*.jar /app/lib/

# Ekspose port 
EXPOSE 8081

# Jalankan aplikasi
CMD ["java", "-cp", "/app/lib/*:/app/SOAP_Service-1.0-SNAPSHOT.jar", "org.example.Main"]

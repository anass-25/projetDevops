# Étape 1 : Utiliser une image de base avec Java 21
FROM eclipse-temurin:21-jdk-alpine

# Étape 2 : Configurer le répertoire de travail dans le conteneur
WORKDIR /app

# Étape 3 : Copier le fichier JAR de l'application dans le conteneur
COPY target/spring-data-redis-example-0.0.1-SNAPSHOT.jar app.jar

# Étape 4 : Exposer le port utilisé par l'application (par défaut 8080)
EXPOSE 9220

# Étape 5 : Démarrer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]

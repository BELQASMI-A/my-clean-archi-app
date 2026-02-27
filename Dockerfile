# Étape 1 : Construction (Build)
# Utilisation d'une image Maven avec Java 21 pour compiler le projet
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copie du fichier pom.xml et téléchargement des dépendances (cache Docker)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copie du code source et construction du JAR "fat"
COPY src ./src
RUN mvn package -DskipTests

# Étape 2 : Exécution (Runtime)
# Utilisation d'une image JRE légère pour l'exécution
FROM eclipse-temurin:21-jre
WORKDIR /app

# Récupération du JAR généré à l'étape précédente
# Le nom correspond à celui défini dans le pom.xml
COPY --from=build /app/target/my-java-app-1.0-SNAPSHOT.jar app.jar

# Commande de démarrage
ENTRYPOINT ["java", "-jar", "app.jar"]

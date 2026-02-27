pipeline {
    agent any

    tools {
        // Assurez-vous que 'maven-3' est configuré dans les outils globaux de Jenkins
        maven 'maven-3'
        jdk 'jdk-21'
    }

    environment {
        // Désactive les logs abusifs de Maven
        MAVEN_OPTS = "-Dmaven.test.failure.ignore=false"
    }

    stages {
        stage('Nettoyage & Préparation') {
            steps {
                echo 'Nettoyage du répertoire de travail...'
                bat 'mvn clean'
            }
        }

        stage('Compilation') {
            steps {
                echo 'Compilation du projet Clean Architecture...'
                bat 'mvn compile'
            }
        }

        stage('Tests Unitaires (Isolation)') {
            steps {
                echo 'Exécution des tests unitaires du Domaine et des Adapteurs...'
                // On lance les tests qui ne nécessitent pas de contexte Spring complet
                bat 'mvn test -Dtest=CreateUserUseCaseTest,AdminScriptingTest'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Tests d\'Intégration (Spring Boot & DB)') {
            steps {
                echo 'Exécution des tests d\'intégration (API Web & Persistance H2)...'
                bat 'mvn test -Dtest=UserRestControllerTest,SpringDataUserRepositoryTest'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Packaging') {
            steps {
                echo 'Création de l\'artéfact exécutable (Fat JAR)...'
                bat 'mvn package -DskipTests'
            }
            post {
                success {
                    echo 'Build terminé avec succès !'
                    archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
                }
            }
        }
    }

    post {
        failure {
            echo 'Le pipeline a échoué. Vérifiez les rapports de tests.'
        }
    }
}

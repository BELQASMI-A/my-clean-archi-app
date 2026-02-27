pipeline {
    agent any

    tools {
        maven 'maven-3'
        jdk 'jdk-21'
    }

    environment {
        MAVEN_OPTS = "-Dmaven.test.failure.ignore=false"
        // Nom du dépôt GitHub (À ADAPTER)
        GITHUB_REPO = "github.com/abelqasm/my-java-app.git"
    }

    stages {
        stage('Nettoyage & Préparation') {
            steps {
                bat 'mvn clean'
            }
        }

        stage('Compilation') {
            steps {
                bat 'mvn compile'
            }
        }

        stage('Tests Unitaires (Isolation)') {
            steps {
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
                bat 'mvn package -DskipTests'
            }
        }

        stage('Tag & Push GitHub') {
            when {
                branch 'master' // Ne tagge que si on est sur la branche master
            }
            steps {
                // Utilise le credentialsId 'github-creds' que vous venez de créer
                withCredentials([usernamePassword(credentialsId: 'github-creds', passwordVariable: 'GIT_PASSWORD', usernameVariable: 'GIT_USERNAME')]) {
                    echo "Tagging de la version stable build-${env.BUILD_NUMBER}..."
                    bat """
                        git tag -a build-${env.BUILD_NUMBER} -m "Build réussi par Jenkins"
                        git push https://%GIT_USERNAME%:%GIT_PASSWORD%@${env.GITHUB_REPO} build-${env.BUILD_NUMBER}
                    """
                }
            }
        }
    }

    post {
        success {
            echo 'Build et Push terminés avec succès !'
            archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
        }
        failure {
            echo 'Le pipeline a échoué. Aucune version ne sera taggée sur GitHub.'
        }
    }
}

pipeline {
    agent any

    tools {
        maven 'Maven3'
    }

    stages {

        stage('Clone Code') {
            steps {
                git 'https://github.com/your-username/airport-management.git'
            }
        }

        stage('Build Project') {
            steps {
                bat 'mvn clean install'
            }
        }

        stage('Run Tests') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Package JAR') {
            steps {
                bat 'mvn package'
            }
        }
    }
}

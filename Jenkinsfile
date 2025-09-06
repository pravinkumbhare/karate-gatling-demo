pipeline {
    agent any

//     tools {
//         maven 'Maven3'   // Make sure Maven is configured in Jenkins Global Tools
//         jdk 'JDK17'      // Use the JDK name you configured in Jenkins
//     }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'master',
                    url: 'https://github.com/pravinkumbhare/karate-gatling-demo.git'
            }
        }

        stage('Build & Unit Tests') {
            steps {
                bat 'mvn clean test'
            }
        }

        stage('Performance Tests') {
            steps {
                bat 'mvn gatling:test'
            }
        }

        stage('Archive Reports') {
            steps {
                archiveArtifacts artifacts: 'target/gatling/**', fingerprint: true
            }
        }
    }

    post {
        always {
            junit 'target/surefire-reports/*.xml' // publish JUnit reports (unit tests)
            echo 'Pipeline finished. Check Gatling report under target/gatling.'
        }
        failure {
            echo 'Build failed! Check Gatling report and logs.'
        }
    }
}

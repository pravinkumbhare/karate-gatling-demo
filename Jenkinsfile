pipeline {
    agent any

//     tools {
//         maven 'M3'    // Ensure Maven is configured in Jenkins (Global Tool Config)
//         jdk 'JDK11'   // Ensure JDK 11 (or higher) is configured in Jenkins
//     }

    environment {
        // You can pass baseUrl dynamically (e.g., QA / Staging)
        BASE_URL = "http://localhost:8081/"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'master',
                    url: 'https://github.com/pravinkumbhare/karate-gatling-demo.git'
            }
        }

        stage('Build & Unit Tests') {
            steps {
                bat 'mvn clean test -DbaseUrl=$BASE_URL'
            }
        }

        stage('Performance Tests') {
            steps {
                bat 'mvn gatling:test -DbaseUrl=$BASE_URL'
            }
        }

        stage('Archive Reports') {
            steps {
                archiveArtifacts artifacts: 'target/gatling/**/*', allowEmptyArchive: true
                publishHTML([[
                    reportDir: 'target/gatling',
                    reportFiles: 'index.html',
                    reportName: 'Gatling Report'
                ]])
            }
        }
    }

    post {
        always {
            junit 'target/surefire-reports/*.xml' // publish JUnit reports (unit tests)
            echo 'Pipeline finished. Check Gatling report under target/gatling.'        }
        failure {
            echo "Build failed! Check Gatling report and logs."
        }
        success {
            echo "Build successful! Gatling report published."
        }
    }
}

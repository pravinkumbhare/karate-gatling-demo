pipeline {
    agent any

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
                bat 'mvn gatling:test "-Dgatling.simulationClass=conduitApp.performance.mockUsersSimulation"'
            }
        }

        stage('Archive Reports') {
            steps {
                gatlingArchive()

//                 archiveArtifacts artifacts: 'target/gatling/**', fingerprint: true
//                 publishHTML(target: [
//                     reportDir: 'target/gatling',
//                     reportFiles: 'index.html',
//                     reportName: 'Gatling Report'
//                 ])
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

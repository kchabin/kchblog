pipeline {
    agent any

    environment {
        // JDK 17 설정 (Jenkins에 JDK17이 설치되어 있어야 함)
        JAVA_HOME = tool(name: 'JDK17', type: 'jdk')
        PATH = "${env.JAVA_HOME}/bin:${env.PATH}"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                // Gradle Wrapper로 빌드 (wrapper가 있어야 함)
                sh './gradlew clean build -x test'
            }
        }

        stage('Test') {
            steps {
                sh './gradlew test'
            }
            post {
                always {
                    junit '**/build/test-results/test/*.xml'  // 테스트 결과 보고
                }
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: '**/build/libs/*.jar', fingerprint: true
        }
        success {
            echo '빌드 성공!'
        }
        failure {
            echo '빌드 실패...'
        }
    }
}

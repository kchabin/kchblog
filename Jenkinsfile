pipeline {
    agent any

    environment {
        JAVA_HOME = tool(name: 'JDK17', type: 'jdk')
        PATH = "${env.JAVA_HOME}/bin:${env.PATH}"

        // Docker Hub 푸시용 변수
        DOCKER_IMAGE = "kchabin/kchblog:${env.BUILD_NUMBER}"
        DOCKER_HUB_CREDENTIALS = 'jenkins-dockerhub' // Jenkins에 등록된 자격 증명 ID
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh './gradlew clean build -x test'
            }
        }

        stage('Test') {
            steps {
                sh './gradlew test'
            }
            post {
                always {
                    junit '**/build/test-results/test/*.xml'
                }
            }
        }

        stage('Docker Build & Push') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', DOCKER_HUB_CREDENTIALS) {
                        def app = docker.build("${DOCKER_IMAGE}")
                        app.push()
                    }
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

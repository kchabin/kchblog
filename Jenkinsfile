pipeline {
    agent any

    environment {
        JAVA_HOME = tool(name: 'JDK17', type: 'jdk')
        PATH = "${env.JAVA_HOME}/bin:${env.PATH}"

        repository = "kchabin/kchblog"
        DOCKER_IMAGE = "kchabin/kchblog:${env.BUILD_NUMBER}"
        DOCKER_HUB_CREDENTIALS = 'jenkins-dockerhub'
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
                    try {
                        // Docker 컨텍스트를 기본값으로 설정
                        sh 'docker context use default || true'

                        // Docker 상태 확인
                        sh 'docker version'

                        // 방법 1: docker.withRegistry 사용 (기존 방식 개선)
                        docker.withRegistry('https://index.docker.io/v1/', DOCKER_HUB_CREDENTIALS) {
                            def app = docker.build("${DOCKER_IMAGE}")
                            app.push()
                            app.push("latest")  // latest 태그도 함께 푸시
                        }

                    } catch (Exception e) {
                        echo "Docker pipeline 방식 실패, shell 명령어 방식으로 시도합니다..."

                        // 방법 2: Shell 명령어 직접 사용 (대안)
                        withCredentials([usernamePassword(
                            credentialsId: DOCKER_HUB_CREDENTIALS,
                            usernameVariable: 'DOCKER_USER',
                            passwordVariable: 'DOCKER_PASS'
                        )]) {
                            sh '''
                                echo "Docker 컨텍스트 설정..."
                                docker context use default || echo "기본 컨텍스트 사용 중"

                                echo "Docker 이미지 빌드..."
                                docker build -t ${DOCKER_IMAGE} .
                                docker tag ${DOCKER_IMAGE} ${repository}:latest

                                echo "Docker Hub 로그인..."
                                echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin

                                echo "Docker 이미지 푸시..."
                                docker push ${DOCKER_IMAGE}
                                docker push ${repository}:latest

                                echo "로그아웃..."
                                docker logout
                            '''
                        }
                    }
                }
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: '**/build/libs/*.jar', fingerprint: true
            // Docker 이미지 정리 (선택사항)
            script {
                try {
                    sh "docker rmi ${DOCKER_IMAGE} || true"
                    sh "docker rmi ${repository}:latest || true"
                } catch (Exception e) {
                    echo "Docker 이미지 정리 중 오류 발생: ${e.getMessage()}"
                }
            }
        }
        success {
            echo '빌드 성공!'
        }
        failure {
            echo '빌드 실패...'
        }
    }
}
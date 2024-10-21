pipeline {
    agent any

    environment {
        RENDER_API_KEY = credentials('RENDER_API_KEY') // Sử dụng credentials cho API key của Render
        RENDER_SERVICE_ID = 'srv-csavl8ggph6c73a72eeg/deploys/dep-csavl8ogph6c73a72eig' // ID của dịch vụ trên Render
        DOCKER_HUB_CREDENTIALS = credentials('dockerhub-credential') // Credentials cho Docker Hub
        DOCKER_IMAGE_NAME = 'spring-quiz-app' // Tên Docker image
    }

    stages {
        stage('Checkout from Git') {
            steps {
                checkout scm // Lấy mã nguồn từ Git
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests' // Biên dịch ứng dụng với Maven, bỏ qua kiểm thử
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // Đăng nhập vào Docker Hub
                    withCredentials([usernamePassword(credentialsId: '${DOCKER_HUB_CREDENTIALS}', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                        sh '''
                            echo $DOCKER_PASSWORD | docker login -u $DOCKER_USERNAME --password-stdin
                        '''
                    }
                    // Xây dựng Docker image
                    sh "docker build -t ${DOCKER_IMAGE_NAME} ."

                    // Đẩy Docker image lên Docker Hub
                    sh "docker push ${DOCKER_IMAGE_NAME}"
                }
            }
        }

        stage('Deploy to Render') {
            steps {
                script {
                    // Gửi yêu cầu tới API của Render để triển khai
                    sh """
                    curl -X POST https://api.render.com/v1/services/${RENDER_SERVICE_ID}/deploys \
                    -H "Authorization: Bearer ${RENDER_API_KEY}" \
                    -H "Content-Type: application/json" \
                    -d '{"branch": "main"}'
                    """
                }
            }
        }

        stage('Post-Deploy Tests') {
            steps {
                script {
                    // Thực hiện kiểm thử sau khi triển khai
                    sh "curl -f http://your-app-url.com/health" // Thay đổi URL cho phù hợp
                }
            }
        }
    }

    post {
        success {
            echo 'Deployment successful!'
        }
        failure {
            echo 'Deployment failed!'
        }
    }
}
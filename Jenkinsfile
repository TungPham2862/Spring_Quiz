pipeline {
    agent any

    environment {
        RENDER_API_URL = "https://api.render.com/deploy/srv-csc9os8gph6c73bov4h0?key=td5OIBZfhCM" // Thay <SERVICE_ID> và <API_KEY> với thông tin của bạn
        GITHUB_USER = 'TungPham2862'
        GITHUB_TOKEN = credentials('github-access-token')
    }

    stages {
        stage('Checkout') {
            steps {
                // Lấy mã nguồn từ Git
                checkout scm
            }
        }

        stage('Build') {
            steps {
                // Biên dịch dự án bằng Maven
                powershell 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                // Chạy unit tests
                powershell 'mvn test'
            }
        }


        stage('Build and Commit') {
                    steps {
                        script {
                        withCredentials([gitUsernamePassword(credentialsId: 'github-access-token', gitToolName: 'Default')]) {
                        powershell '''
                             git add .
                             git commit -m "Deploy new version from Jenkins"
                             bat "git push -u origin deploy"
                        '''
                        }
                        }
                    }
                }

        stage('Deploy to Render') {
            steps {
                script {
                    // Gửi yêu cầu đến Render API để triển khai bằng curl
                    def response = powershell(script: """
                        curl -X POST "https://api.render.com/deploy/srv-csc9os8gph6c73bov4h0?key=td5OIBZfhCM" -H "Content-Type: application/json"
                    """, returnStdout: true).trim()

                    echo "Deployment response: ${response}"

                    // Kiểm tra phản hồi
                    if (response.contains("error")) { // Thay đổi điều kiện kiểm tra theo nội dung phản hồi thực tế
                        error "Deployment failed with response: ${response}"
                    } else {
                        echo 'Deployment succeeded!'
                    }
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}
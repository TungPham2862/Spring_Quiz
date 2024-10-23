pipeline {
    agent any

    environment {
        RENDER_API_URL = "https://api.render.com/deploy/srv-<srv-csavl8ggph6c73a72eeg>?key=<rnd_Jx3GEScTqvmvauQvIsqR5d9jexWF>" // Thay <SERVICE_ID> và <API_KEY> với thông tin của bạn
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
                sh 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                // Chạy unit tests
                sh 'mvn test'
            }
        }

        stage('Deploy to Render') {
            steps {
                script
                {
                     def response = httpRequest(url: RENDER_API_URL, httpMode: 'POST')
                     echo "Deployment response: ${response.content}"
                     if (response.status != 200) {
                     error "Deployment failed with status: ${response.status}"
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
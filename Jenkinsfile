pipeline {
    agent any

    environment {
        RENDER_API_URL = "https://api.render.com/deploy/srv-csd2bmt6l47c73flkhmg?key=JgNRdccrPf4" // Thay <SERVICE_ID> và <API_KEY> với thông tin của bạn
//         GITHUB_USER = 'TungPham2862'
//         GITHUB_TOKEN = credentials('github-access-token')
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Build Docker Image') {
                    steps {
                        script {
                            bat "docker build -t tungpham286/spring-quiz-app ."
                        }
                    }
                }

                stage('Push Docker Image') {
                    steps {
                        script {
                            withCredentials([usernamePassword(credentialsId: 'dockerhub-credential', passwordVariable: 'DOCKER_PASSWORD', usernameVariable: 'DOCKER_USERNAME')]) {
                                bat "docker login -u ${DOCKER_USERNAME} -p ${DOCKER_PASSWORD}"

                                bat "docker push tungpham286/spring-quiz-app"
                            }
                        }
                    }
                }

//         stage('Build and Commit') {
//                     steps {
//                         script {
//                         withCredentials([gitUsernamePassword(credentialsId: 'github-access-token', gitToolName: 'Default')]) {
//                         bat '''
//                             git pull --rebase origin deploy
//                             git add .
//                             git commit -m "Deploy new version from Jenkins"
//                             git push -u origin deploy
//                         '''
//
//
//                         }
//                         }
//                     }
//                 }

        stage('Deploy to Render') {
            steps {
                script {
                    def response = bat(script: """
                        curl -X POST "https://api.render.com/deploy/srv-csd2bmt6l47c73flkhmg?key=JgNRdccrPf4" -H "Content-Type: application/json"
                    """, returnStdout: true).trim()

                    echo "Deployment response: ${response}"

                    if (response.contains("error")) {
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
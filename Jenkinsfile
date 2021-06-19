/*
For linux machines:
pipeline{
    agent dell
    stages{
        stage('Build Jar') {
            agent {
                docker {
                    image 'maven:3-alpine'
                    args '-v /root/.m2:/root/.m2'
                }
                steps {
                    sh 'mvn clean package -DskipTests'
                }
            }
        }
        stage('Build Image') {
            steps {
                app = docker.build("charitrasharma/selenium-docker")
            }
        }
        stage('Push Image') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'docker') {
                        app.push("${BUILD_NUMBER}")
                        app.push("latest")
                    }
                }
            }
        }
    }
} */
// For windows/machines
pipeline {
    agent dell
    stages{
        stage('Build Jar') {
            steps {
                bat "mvn clean package -DskipTests"
            }
        }
        stage('Building image') {
            steps {
                bat "docker build -t='charitrasharma/selenium-docker' ."
            }
        }
        stage('Push Image'){
            steps {
                withCredentials([usernamePassword(credentials: 'docker', passwordVariable: 'pass', usernameVariable: 'user')])
                    bat "docker login --username=${user} --password=${pass}"
                    bat "docker push charitrasharma/selenium-docker:latest"
            }
        }
    }


}
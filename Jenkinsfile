pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                git credentialsId: 'b793b66d-de02-40e3-92e9-e88911c83e3c', url: 'https://github.com/mukeshbhange/BookStore_Application.git'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}

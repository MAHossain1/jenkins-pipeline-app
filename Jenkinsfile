def gv

pipeline {
    agent any

    // environment {
    //     NEW_VERSION = '2.1'
    // }

    tools {
        maven 'Maven'
    }

    parameters {
        // string(name: 'VERSION', defaultValue: '1.0', description: 'Please enter the version of the application')
        choice(name: 'VERSION', choices: ['1.1.0', '2.1.0', '2.2.0'], description: 'Please select the version of the application')
        booleanParam(name: 'ExecuteTests', defaultValue: true, description: 'Please select the flag')
    }

    stages {
        stage('Checkout') {
           steps {
                    script {
                        checkout scmGit(
                            branches: [[name: '*/main']], // Change if needed
                            userRemoteConfigs: [[url: 'https://github.com/MAHossain1/jenkins-pipeline-app.git']]
                        )
                    }
                }
        }

         stage('test') {
            when {
                expression {
                     params.ExecuteTests
                }
            }


            steps {
                script {
                    echo "testing the application version ${params.VERSION}"
                   
                }
            }
        }

        stage('Build jar') {
            steps {
                script {
                    echo "building the application"
                    sh 'mvn package'
                }
            }
        }
        stage('Building the docker image') {
            steps {
                script {
                    echo "building the docker image"
                    withCredentials([usernamePassword(credentialsId: 'docker_hub_repo', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                        sh 'docker build -t arman04/java-maven-app:jma-2.1 .'
                        sh "echo $PASSWORD | docker login -u $USERNAME --password-stdin"
                        sh 'docker push arman04/java-maven-app:jma-2.1'
                    }
                }
            }
        }
        stage('Deploy') {
            steps {
                script {
                    echo "deploying the application ${params.VERSION}"
                }
            }
        }
        
    }

    post {
        always {
            echo 'This will always run'
        }
        success {
            echo 'This will run only if successful'
        }
        failure {
            echo 'This will run only if failed'
        }
        unstable {
            echo 'This will run only if the run was marked as unstable'
        }
        changed {
            echo 'This will run only if the state of the Pipeline has changed'
            echo 'For example, if the Pipeline was previously failing but is now successful'
        }
    }

    
}

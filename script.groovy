
def buildJar() {
    echo "building the application..."
    sh 'mvn package -U'
} 

def buildImage() {
    echo "building the docker image"
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
        sh 'docker build -t arman04/java-maven-app:jma-3.1 .'
        sh "echo $PASSWORD | docker login -u $USERNAME --password-stdin"
        sh 'docker push arman04/java-maven-app:jma-3.1'
    }
} 

def deployApp() {
    echo 'deploying the application...'
} 

return this
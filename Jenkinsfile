pipeline{
    agent any
    environment {
        DOCKER_HUB = "itmabo"
    }
    parameters {
        string(name: 'Version', defaultValue: 'latest', description: 'Current Release Version')
        string(name: 'Domain', defaultValue: 'bees', description: 'Domain Name')
        string(name: 'Namespace', defaultValue: 'forest', description: 'Apply Namespace')
    }
    stages {
        stage('Build') {
            environment {
                IMAGE_NAME = "${env.DOCKER_HUB}/${params.Domain}:v${BUILD_NUMBER}"
            }
            steps{
                script {
                    if (params.Version != 'latest') {
                        IMAGE_NAME = "${env.DOCKER_HUB}/${params.Domain}:${params.Version}"
                    }
                }
                echo 'Stage @Build@ begin -> '
                sh """
                    mvn clean package -DskipTests -U
                    docker build -t ${IMAGE_NAME} .
                    docker push ${IMAGE_NAME}
                """
            }
        }
        stage('Test') {
            steps{
                echo 'Stage @Test@ begin -> '
                echo 'Skip Test Now'
            }
        }
        stage('Deploy') {
            environment {
				VERSION = "v${BUILD_NUMBER}"
            }
            steps{
                script {
                    if (params.Version != 'latest') {
                        VERSION = "${params.Version}"
                    }
                    DOMAIN = "${params.Domain}"
                    NAMESPACE = "${params.Namespace}"
                echo 'Stage @Deploy@ begin -> '
                sh """

					export VERSION=${VERSION}
					export DOMAIN=${DOMAIN}
					export NAMESPACE=${NAMESPACE}

                    envsubst < deploy/bees-deploy-template.yaml|kubectl apply -f -
                """
            }
        }
    }
}
pipeline{
    agent any
    environment {
        DOMAIN = "bees";
    }
    parameters {
        string(name: 'Version', defaultValue: 'latest', description: 'release version')
        string(name: 'Namespace', defaultValue: 'forest', description: 'Apply Namespace')
    }
    stages {
        stage('Build') {
            environment {
                DOCKER_HUB = "itmabo"
                IMAGE_NAME = "${env.DOCKER_HUB}/${env.DOMAIN}:v${BUILD_NUMBER}"
            }
            steps{
                script {
                    if (params.Version != 'latest') {
                        IMAGE_NAME = "${env.DOCKER_HUB}/${env.DOMAIN}:${params.Version}"
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
                    NAMESPACE = "${params.Namespace}"
                echo 'Stage @Deploy@ begin -> '
                sh """

					export VERSION=${VERSION}
					export DOMAIN=${env.DOMAIN}
					export NAMESPACE=${NAMESPACE}

                    envsubst < deploy/bees-deploy-template.yaml > deploy.yaml
                    echo "cat deploy.yaml --->>>"
                    cat deploy.yaml
                    echo "cat deploy.yaml ---<<<"
                    kubectl apply -f deploy.yaml
                """
                }
            }
        }
    }
}

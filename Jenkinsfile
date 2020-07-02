pipeline{
    agent any
    environment {
        DOCKER_HUB = "itmabo"
    }
    parameters {
        gitParameter name: 'Version2',
                     type: 'PT_BRANCH_TAG',
                     branchFilter: 'origin/(.*)',
                     defaultValue: 'master',
                     selectedValue: 'DEFAULT',
                     sortMode: 'DESCENDING_SMART',
                     description: 'Select your branch or tag.'
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

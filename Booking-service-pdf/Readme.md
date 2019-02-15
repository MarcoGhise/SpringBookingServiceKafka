docker build --file=Dockerfile --tag=pdf-service:latest --rm=true .

kubectl create -f kubernetes/pdf-service.yml
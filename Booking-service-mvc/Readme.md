docker build --file=Dockerfile --tag=mvc-service:latest --rm=true .

kubectl create -f kubernetes/mvc-service.yml
#!/bin/bash

set -e

echo "🚀 Iniciando deploy no Minikube..."

# ----------------------------
# 1. Subir o Minikube
# ----------------------------
echo "📦 Iniciando Minikube..."
minikube start --driver=docker

# ----------------------------
# 2. Usar Docker do Minikube
# ----------------------------
echo "🐳 Configurando Docker para usar o daemon do Minikube..."
eval $(minikube docker-env)

# ----------------------------
# 3. Build da imagem da aplicação
# ----------------------------
echo "🏗️ Buildando imagem da aplicação..."

docker build --no-cache -t tech-challenge:latest .

# ----------------------------
# 4. Aplicar manifestos Kubernetes
# ----------------------------
echo "📄 Aplicando manifestos Kubernetes..."

kubectl apply -f k8s/mysql.yaml
kubectl rollout status deployment/mysql
kubectl apply -f k8s/configmap.yaml
kubectl apply -f k8s/secret.yaml
kubectl apply -f k8s/deployment.yaml
kubectl apply -f k8s/service.yaml
kubectl apply -f k8s/hpa.yaml

# ----------------------------
# 5. Aguardar pods
# ----------------------------
echo "⏳ Aguardando pods ficarem prontos..."
kubectl wait \
  --for=condition=ready pod \
  --selector=app=tech-challenge \
  --timeout=180s

# ----------------------------
# 6. Status final
# ----------------------------
echo "✅ Deploy finalizado com sucesso!"

echo ""
echo "🔎 Recursos criados:"
kubectl get all

echo ""
echo "🌐 Para acessar a aplicação:"
echo "kubectl port-forward svc/tech-challenge-service 8080:80"

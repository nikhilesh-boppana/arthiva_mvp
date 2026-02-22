#!/bin/bash
set -e

# ============================================================
#  Arthiva MVP - Single startup script
#  Prerequisites: Java 17+, Node 18+, Docker, Maven
# ============================================================

ROOT_DIR="$(cd "$(dirname "$0")" && pwd)"
BACKEND_DIR="$ROOT_DIR/backend"
FRONTEND_DIR="$ROOT_DIR/frontend"

# Load .env if present
if [ -f "$ROOT_DIR/.env" ]; then
  echo "Loading environment from .env ..."
  export $(grep -v '^#' "$ROOT_DIR/.env" | xargs)
fi

# 1. Start MySQL container
echo ""
echo "==> Starting MySQL container ..."
if [ "$(docker ps -q -f name=arthiva_mysql)" ]; then
  echo "    MySQL already running."
else
  docker run -d \
    --name arthiva_mysql \
    -e MYSQL_ROOT_PASSWORD="${DB_PASSWORD:-arthiva123}" \
    -e MYSQL_DATABASE=arthiva_db \
    -p 3306:3306 \
    mysql:8.0
  echo "    Waiting for MySQL ..."
  until docker exec arthiva_mysql mysqladmin ping -h localhost --silent 2>/dev/null; do
    sleep 2
  done
  echo "    MySQL ready."
fi

# 2. Build React frontend
echo ""
echo "==> Building React frontend ..."
cd "$FRONTEND_DIR"
npm install --silent
npm run build

# 3. Copy React build into Spring Boot static folder
echo ""
echo "==> Copying frontend build into backend ..."
STATIC_DIR="$BACKEND_DIR/src/main/resources/static"
rm -rf "$STATIC_DIR"
mkdir -p "$STATIC_DIR"
cp -r "$FRONTEND_DIR/build/." "$STATIC_DIR/"

# 4. Build & start Spring Boot JAR
echo ""
echo "==> Building Spring Boot app ..."
cd "$BACKEND_DIR"
./mvnw clean package -DskipTests -q

echo ""
echo "==> Arthiva MVP running at http://localhost:8080"
echo "    Press Ctrl+C to stop."
java -jar target/arthiva-backend-1.0.0.jar

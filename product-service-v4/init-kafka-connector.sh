# 모든 컨테이너가 정상적으로 실행될 때까지 대기
echo "======================================"
echo "🚀 Starting Kafka Connect initialization..."
echo "======================================"

# 등록되어 있는 kafka-connect 제거
echo "🔄 Deleting MySQL source connector..."
echo "--------------------------------------"
curl -X DELETE http://localhost:8083/connectors/mysql-source-connector

echo "🔄 Deleting MySQL target connector..."
echo "--------------------------------------"
curl -X DELETE http://localhost:8083/connectors/mysql-target-connector

echo "✅ MySQL source connector delete completed!"

# MySQL Source Connector 등록
echo "🔄 Registering MySQL source connector..."
echo "--------------------------------------"
curl -X POST http://localhost:8083/connectors \
     -H "Accept:application/json" \
     -H "Content-Type: application/json" \
     -d @register-mysql-source.json \
     -w "\n✅ Request completed with status: %{http_code}\n"
echo "--------------------------------------"

echo "✅ MySQL source connector registration completed!"

# MySQL target Connector 등록
echo "🔄 Registering MySQL target connector..."
echo "--------------------------------------"
curl -X POST http://localhost:8083/connectors \
     -H "Accept:application/json" \
     -H "Content-Type: application/json" \
     -d @register-mysql-target.json \
     -w "\n✅ Request completed with status: %{http_code}\n"
echo "--------------------------------------"

echo "✅ MySQL source target registration completed!"

echo "======================================"
echo "🎉 Kafka Connect setup complete!"
echo "======================================"
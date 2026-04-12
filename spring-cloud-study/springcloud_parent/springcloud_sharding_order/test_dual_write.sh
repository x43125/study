#!/bin/bash

# 双写功能测试脚本

echo "======================================"
echo "双写功能测试脚本"
echo "======================================"

# 测试1：创建订单（应该写入老表）
echo ""
echo "测试1：创建订单（当前配置：只写老表）"
curl -X POST http://localhost:9010/order/create \
  -H "Content-Type: application/json" \
  -d '{"userId":2,"receiverName":"李四","receiverPhone":"13900139000","receiverAddress":"上海市浦东新区"}'

echo ""
echo ""

# 测试2：查询订单
echo "测试2：查询订单（当前配置：只读老表）"
echo "请输入要查询的订单号（使用测试1返回的订单号）："
read order_no
curl http://localhost:9010/order/$order_no

echo ""
echo ""

# 测试3：统计订单数量
echo "测试3：统计订单数量"
curl http://localhost:9010/order/count

echo ""
echo ""
echo "======================================"
echo "测试完成"
echo "======================================"
echo ""
echo "说明："
echo "- 当前配置：只写老表（write-mode: old）"
echo "- 如需启用双写：修改 application.yml 中的 datasource-router.write-mode 为 both"
echo "- 如需启用 ShardingSphere：启动时添加参数 --spring.profiles.active=sharding"
echo "- 双写时，数据会同时写入老表和新表（分片表）"
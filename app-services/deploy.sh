#!/bin/bash

echo -n Password:
read -s password

scp -p ./auth/build/libs/auth-0.0.1-SNAPSHOT.jar ./compras/build/libs/compras-0.0.1-SNAPSHOT.jar \
./discovery/build/libs/discovery-0.0.1-SNAPSHOT.jar ./entrega/build/libs/entrega-0.0.1-SNAPSHOT.jar \
./pagamento/build/libs/pagamento-0.0.1-SNAPSHOT.jar ./proxy/build/libs/proxy-0.0.1-SNAPSHOT.jar \
bruno@arch-services.eastus2.cloudapp.azure.com:/tmp

echo $password

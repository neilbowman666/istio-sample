#!/bin/bash
image_tag=okeyja/istio-sample-module-c:1.0.0
mvn package -D skipTests
docker build . -t $image_tag
docker push $image_tag
docker rmi $image_tag

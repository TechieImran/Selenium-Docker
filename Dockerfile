FROM bellsoft/liberica-openjre-alpine:21.0.4

#Install Curl jq
RUN apk add curl jq

#Workspace
WORKDIR /home/selenium-docker

#Add the required files
ADD target/docker-resources   ./
ADD runner.sh                 runner.sh

#ENVIRONMENT VARIABLES
#BROWSER
#HUB_HOST
#TEST_SUITE
#THREAD_COUNT

#Run the tests
# ENTRYPOINT java -cp "libs/*" \
#             -Dselenium.grid.enabled=true \
#             -Dselenium.grid.hubHost=${HUB_HOST} \
#             -Dbrowser=${BROWSER} \
#              org.testng.TestNG \
#              -threadcount ${THREAD_COUNT} \
#              test-suites/${TEST_SUITE}

#Fix for windows
RUN dos2unix runner.sh

#Start the runner.sh
ENTRYPOINT sh runner.sh

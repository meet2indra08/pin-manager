# pin-manager

This project is on Spring boot microservices 
Prequisite: Please install below application to run the project.
1) Java 8
2) Docker 
3) Redis on Docker

Note - Do not need to install database as this project is sample project used H2 DB.

Generate PIN:
Payload:
curl --location --request POST 'localhost:8080/api/v1/pin/generate' \
--header 'Content-Type: application/json' \
--data-raw '{
    "msisdn":"8324733091"
}'


Validate PIN:

curl --location --request POST 'localhost:8080/api/v1/pin/validate' \
--header 'Content-Type: application/json' \
--data-raw '{
    "msisdn":"8527733090",
    "pin":"8210"
}'

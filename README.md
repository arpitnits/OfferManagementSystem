LLD

Offer Managment System

Design a system for credit card offers.

API's


Create User

curl --location 'http://localhost:8000/create/user' \
--header 'Content-Type: application/json' \
--data '{
    "userName": "Arpit",
    "gender": "MALE",
    "dob": "1987-03-21"
}
'


Get Offers

curl --location 'http://localhost:8000/get/offers' \
--header 'Content-Type: application/json' \
--data '{
  "uuid": "7022fbd0-9cc8-430e-9e6e-403e2ad3db0b",
  "transaction": {
    "merchantId": "LADOOS",
    "merchantName": "merchantName_0d79dacb946e",
    "transactionDate": "2023-12-16",
    "city": "BLR",
    "txnAmount": 10.00
  }
}'

Create Offer(Fixed)

curl --location 'http://localhost:8000/create/offer' \
--header 'Content-Type: application/json' \
--data '{
  "offerAmount": 10,
  "customerEligibilty": {
    "minAge": 10,
    "gender": "MALE",
    "weekGMV": 50
  },
  "transactionEligibilty": {
    "minTransaction": 20,
    "eligibleCities": [
      "BLR"
    ],
    "eligibleMerchants": [
      "RAMESHWARAM_CAFE"
    ]
  },
  "type": "FIXED"
}'

Create Offer(Percentage)

curl --location 'http://localhost:8000/create/offer' \
--header 'Content-Type: application/json' \
--data '{
  "percentage": 10,
  "offerLimit": 100,
  "customerEligibilty": {
    "minAge": 0,
    "gender": "MALE",
    "weekGMV": 20
  },
  "transactionEligibilty": {
    "minTransaction": 0,
    "eligibleCities": [
      "BLR"
    ],
    "eligibleMerchants": [
      "RAMESHWARAM_CAFE"
    ]
  },
  "type": "PERCENTAGE"
}'

# Offer Managment System (Low Level Design)

**Design a system for credit card offers.**

1. ADMIN can create offers.
2. Offers can be fixed or percentage.
3. There can be multiple eligibility criteria: 
      1. Customer Eligibilty - Age, Gende, etc
      1. Transaction Eligibilty - Min txn Amount, txn City, Merchant
4. There can be different types of offers:
      1. Fixed Offer (FLAT 150 Off)
      1. Percentage (10% Off Upto 100)      
5. User can get all the valid offers by passing all user Info and txn details

*Bonus: Design sliding window offer like 1000 Rs spent in 3 days*


Create API's for the above functionality

----------------------------------------------------------



<details>
      <summary> Create User </summary>

```
curl --location 'http://localhost:8000/create/user' \
--header 'Content-Type: application/json' \
--data '{
    "userName": "Arpit",
    "gender": "MALE",
    "dob": "1987-03-21"
}
```
</details>



<details>
      <summary>Get Offers</summary>

```
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
```
</details>


<details>
      <summary>Create Offer(Fixed)</summary>

```
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
```
</details>


<details>
      <summary>Create Offer(Percentage)</summary>

```
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
```
</details>




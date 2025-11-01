# 🧪 API Automation Framework — REST Assured + Cucumber + Selenium

This project demonstrates how to **automate REST API testing** using  
**Java**, **REST Assured**, and **Cucumber (BDD)**, along with basic Selenium integration for UI validation.

---

## 🚀 Project Overview

This automation framework performs:

1. **GET Request**  
   - Endpoint: [`https://echo.free.beeceptor.com/sample-request?author=beeceptor`](https://echo.free.beeceptor.com/sample-request?author=beeceptor)  
   - Validates response fields:
     - `path`
     - `ip`
     - `headers`
   - Ensures all headers are present and valid.

2. **POST Request**  
   - Endpoint: [`http://echo.free.beeceptor.com/sample-request?author=beeceptor`](http://echo.free.beeceptor.com/sample-request?author=beeceptor)  
   - Validates:
     - Customer details  
     - Payment details  
     - Product and shipping details  
   - Uses the JSON payload below:

```json
{
  "order_id": "12345",
  "customer": {
    "name": "Jane Smith",
    "email": "janesmith@example.com",
    "phone": "1-987-654-3210",
    "address": {
      "street": "456 Oak Street",
      "city": "Metropolis",
      "state": "NY",
      "zipcode": "10001",
      "country": "USA"
    }
  },
  "items": [
    {"product_id": "A101", "name": "Wireless Headphones", "quantity": 1, "price": 79.99},
    {"product_id": "B202", "name": "Smartphone Case", "quantity": 2, "price": 15.99}
  ],
  "payment": {
    "method": "credit_card",
    "transaction_id": "txn_67890",
    "amount": 111.97,
    "currency": "USD"
  },
  "shipping": {
    "method": "standard",
    "cost": 5.99,
    "estimated_delivery": "2024-11-15"
  },
  "order_status": "processing",
  "created_at": "2024-11-07T12:00:00Z"
}

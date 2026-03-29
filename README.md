The application processes transaction data and returns:
->Monthly reward points per customer
->Total reward points per customer

Business Logic:
---------------
->2 points for every dollar spent above $100
->1 point for every dollar spent between $50 and $100
No points for spending below $50

Transaction Amount = $120

Points:
= (120 - 100) * 2 + (100 - 50) * 1
= 20 * 2 + 50 * 1
= 40 + 50 = 90 points

Project Structure:
------------------
com.retailer.reward

controller -> Handles REST API requests
service -> Business logic implementation
service -> Business logic implementation
model -> Transaction entity
dto -> Response objects
dto -> Response objects
util -> Reward calculation logic
exception → Global exception handling

Endpint:
GET: http://localhost:8080/api/rewards

Response:
[
    {
        "customerId": 101,
        "monthlyPoints": {
            "Feb": 90,
            "Dec": 110,
            "Jan": 25
        },
        "totalPoints": 225
    },
    {
        "customerId": 102,
        "monthlyPoints": {
            "Feb": 250,
            "Dec": 0
        },
        "totalPoints": 250
    }
]

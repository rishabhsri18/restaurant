# restaurant
Test Spring boot restaurant project with h2 database


Restaurant and delivery boy has predefined data in it

[
    {
        "id": 1,
        "itemMap": {
            "1": 8,
            "2": 16,
            "3": 12
        },
        "deliveryBoys": [
            {
                "id": 1,
                "deliveryBoyStatus": "ACTIVE",
                "deliveryTime": 0
            },
            {
                "id": 2,
                "deliveryBoyStatus": "ACTIVE",
                "deliveryTime": 0
            }
        ]
    },
    {
        "id": 2,
        "itemMap": {
            "4": 8,
            "5": 16,
            "6": 12
        },
        "deliveryBoys": [
            {
                "id": 3,
                "deliveryBoyStatus": "ACTIVE",
                "deliveryTime": 0
            },
            {
                "id": 4,
                "deliveryBoyStatus": "ACTIVE",
                "deliveryTime": 0
            },
            {
                "id": 5,
                "deliveryBoyStatus": "BUSY",
                "deliveryTime": 0
            },
            {
                "id": 6,
                "deliveryBoyStatus": "INACTIVE",
                "deliveryTime": 0
            }
        ]
    }
]

GET Restaurant data - curl --location --request GET 'localhost:8080/restaurant'
POST Order - curl --location --request POST 'localhost:8080/order?itemId=4&noOfItems=4'
GET Order Status - curl --location --request GET 'localhost:8080/orderStatus?orderId=3'
POST Change Order Status - curl --location --request POST 'localhost:8080/order/deliveryStatus?orderId=3&status=PENDING'
GET all Active delivery boy - curl --location --request GET 'localhost:8080/activeDeliveryBoy'
POST add delivery boy to order - curl --location --request POST 'localhost:8080/deliveryBoy/order?deliveryPersonId=1&orderId=3'
GET delivery boy status - curl --location --request GET 'localhost:8080/deliveryBoy/status?deliveryPersonId=1'

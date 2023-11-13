

# On Travel Bookings

1. Build the project using `mvn clean install`
2. Run using `mvn spring-boot:run` 
3. The web application is accessible via localhost:8080
4. You can also skip the steps above and run using your favorite IDE

# Running the application

Every time the application is start/restarted it will create an in memory database using H2,  
so you will have to re-create your Entities.

# Testing the flow #

You can use the file "Booking.postman_collection" and import it into your Postman with the complete APIs 
and payload examples or do the steps below:

Do the following steps before making a reservation:

1. Create a Property
2. Create one or more Guests 

After that, get the returned "id" from the Property and the "ids" from the Guests and do the following:

1. Create the reservation payload using "propertyId" and the list of guests with only the ids like the following payload:
	
	{
    "propertyId": 1,
    "startDate": "2023-12-11",
    "endDate": "2023-12-12",
    "observation": "Customers like the house clean",
    "guests": [
        {
            "id": 1
        },
        {
            "id": 2
        },
        {
            "id": 3
        }
    ]
}


# Payloads Examples for Creation (POST):

1 - Create a Property:

	{
    "name": "Mountain House",
    "address": "Tim Park Avenue, 34"
    }

2. Create a Guest:

	{
    "name": "John Guest",
    "identification": "DOC. N° 1"
    }

3. Create a Property Block:

	{
    "propertyId": 1,
    "startDate": "2023-12-03",
    "endDate": "2023-12-10",
    "reason": "Under construction"
}

4. Create a Reservation:

	{
    "propertyId": 1,
    "startDate": "2023-12-11",
    "endDate": "2023-12-12",
    "observation": "Customers like the house clean",
    "guests": [
        {
            "id": 1
        },
        {
            "id": 2
        },
        {
            "id": 3
        }
    ]
}


# Contact

In case you need any help, do not hesitate to email me at caiocesouza@gmail.com.







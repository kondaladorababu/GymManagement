
# Gym Management System API

This project is a simple Gym Management System implemented as a **RESTful API** that allows club owners to manage their classes, bookings, and member reservations. The API provides functionalities to create classes, book classes, and search for bookings.

---

## **Features**

### **1. Create Classes**
- **POST /api/classes**  
  Allows a club owner to create a class by providing the following details:
    - **name** (String)
    - **startDate** (LocalDate)
    - **endDate** (LocalDate)
    - **startTime** (LocalTime)
    - **duration** (Duration in minutes)
    - **capacity** (int)

- **GET /api/classes**  
  Returns a list of all available classes.

### **2. Book a Class**
- **POST /api/bookings**  
  Allows a member to book a class by providing the following details:
    - **memberName** (String)
    - **className** (String)
    - **participationDate** (LocalDate)

### **3. Search Bookings**
- **GET /api/bookings/member**  
  Search for bookings by **memberName**.
    - Query parameter: `memberName` (String)

- **GET /api/bookings/dateRange**  
  Search for bookings by **date range**.
    - Query parameters:
        - `startDate` (LocalDate)
        - `endDate` (LocalDate)

- **GET /api/bookings/nameAndDate**  
  Search for bookings by **memberName** and **date range**.
    - Query parameters:
        - `memberName` (String)
        - `startDate` (LocalDate)
        - `endDate` (LocalDate)

---

## **Swagger UI**

Swagger UI has been integrated into the project to visualize and interact with the API's endpoints. 
Once the application is running, you can access the Swagger documentation through:

- **URL**: `http://localhost:8080/swagger-ui.html`

Swagger UI will display the API endpoints and allow you to test them directly through the web interface.

---

## **Negative Scenarios Handled**
- **Create Classes API:**
    - Invalid date range (`endDate` before `startDate`)
    - Capacity less than 1
    - Class schedule conflict (same class on the same date)
- **Create Bookings API:**
    - Booking participation date in the past
    - Class not found
    - Exceeding class capacity
- **Search Bookings API:**
    - Invalid date range (startDate after endDate)

---

## **Tech Stack**
- **Java 17**
- **Spring Boot** (for building the RESTful API)
- **Spring Web** (for controllers)
- **Spring Boot DevTools** (for hot reloading)
- **Maven** (for dependency management)
- **Swagger** (for API documentation)

---

## **Installation**

### **1. Clone the repository**
```bash
git clone  "https://github.com/kondaladorababu/GymManagement.git"
cd GymManagement
```

### **2. Build the project**
Use Maven to build the project:
```bash
mvn clean install
```

### **3. Run the Application**
To run the Spring Boot application:
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`.

---

## **API Endpoints**

### **1. Create Class**

**POST** `/api/classes`
- Request Body Example:
```json
{
  "name": "Pilates",
  "startDate": "2025-02-01",
  "endDate": "2025-02-20",
  "startTime": "14:00",
  "duration": 60,
  "capacity": 10
}
```

- **Response:**
    - `201 Created`: Class created successfully
    - `400 Bad Request`: Validation error (e.g., capacity < 1, invalid date range)
    - `409 Conflict`: Class already exists with the same name and start date

### **2. Book a Class**

**POST** `/api/bookings`
- Request Body Example:
```json
{
  "memberName": "John Doe",
  "className": "Pilates",
  "participationDate": "2025-02-05"
}
```

- **Response:**
    - `201 Created`: Booking created successfully
    - `400 Bad Request`: Invalid date or missing fields
    - `404 Not Found`: Class not found
    - `409 Conflict`: Capacity exceeded

### **3. Search Bookings**

**GET** `/api/bookings/member`
- Query Parameters:
    - `memberName` (String)

Example request:
```bash
GET /api/bookings/member?memberName=John Doe
```

**GET** `/api/bookings/dateRange`
- Query Parameters:
    - `startDate` (LocalDate, format: yyyy-MM-dd)
    - `endDate` (LocalDate, format: yyyy-MM-dd)

Example request:
```bash
GET /api/bookings/dateRange?startDate=2025-02-01&endDate=2025-02-20
```

**GET** `/api/bookings/nameAndDate`
- Query Parameters:
    - `memberName` (String)
    - `startDate` (LocalDate, format: yyyy-MM-dd)
    - `endDate` (LocalDate, format: yyyy-MM-dd)

Example request:
```bash
GET /api/bookings/nameAndDate?memberName=John Doe&startDate=2025-02-01&endDate=2025-02-20
```

- **Response:**
    - `200 OK`: List of bookings matching the filters
    - `400 Bad Request`: Invalid date range (startDate > endDate)

---

## **Error Handling**

The API returns the following error responses:

- **400 Bad Request**: Invalid input data or missing required fields.
- **404 Not Found**: Resource not found (e.g., class does not exist).
- **409 Conflict**: Conflict with existing data (e.g., exceeding class capacity).
- **500 Internal Server Error**: Unexpected errors during processing.


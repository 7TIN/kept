# Kept - Interview Experience Sharing Platform

Kept is a backend service for a web application that allows users to share and browse interview experiences. It provides a platform for users to post their interview stories, including the company, position, and specific questions asked. The platform also has an AI-powered feature to generate answers to interview questions.


-----

## How It Works

The application is built with **Java** and the **Spring Boot** framework. It uses a **PostgreSQL** database to store data and **JSON Web Tokens (JWT)** for authentication.

### Features

  * **User Authentication**: Users can register and log in to the application. Authentication is handled using JWT.
  * **Company Profiles**: You can add and search for companies. The application can also provide a list of companies with a count of interview experiences associated with them.
  * **Interview Experiences**: Users can post their interview experiences, including details like the position, summary, and interview date. These experiences can be filtered by keyword, experience type, position, and company.
  * **AI-Powered Answers**: The application integrates with an AI service to generate answers to interview questions.

-----

## API Endpoints

The backend provides the following RESTful APIs:

| Method | Endpoint | Description |
| --- | --- | --- |
| **POST** | `/auth/register` | Register a new user. |
| **POST** | `/auth/login` | Log in a user and receive a JWT. |
| **GET** | `/api/companies` | Get a list of all companies. |
| **POST** | `/api/companies` | Add a new company. |
| **GET** | `/api/companies/by-count`| Get companies with their experience counts. |
| **GET** | `/api/experiences/recent` | Get recent interview experiences with filters. |
| **POST** | `/api/experiences` | Add a new interview experience. |
| **GET** | `/api/experiences/company/{id}` | Get experiences for a specific company. |
| **POST** | `/api/ai/generate-answer` | Generate an answer to a question using AI. |
| **GET** | `/users` | Get a list of all users. |
| **GET** | `/users/{id}` | Get a specific user by their ID. |

-----

## How to Fork and Run

To get a local copy up and running, follow these simple steps.

### Prerequisites

  * **Java Development Kit (JDK) 21** or later
  * **Maven**
  * **PostgreSQL**

### Installation

1.  **Fork the repository**
2.  **Clone the repository**
    ```sh
    git clone https://github.com/your_username/kept.git
    ```
3.  **Navigate to the project directory**
    ```sh
    cd kept
    ```
4.  **Install dependencies**
    ```sh
    mvn install
    ```
5.  **Run the application**
    ```sh
    mvn spring-boot:run
    ```

-----

## Connect with Frontend

### Frontend

  * **React**
      * [GitHub Repository](https://github.com/7TIN/kept-client)
      * [Live Demo](https://kept-six.vercel.app/)
  * **Angular**
      * [GitHub Repository](https://github.com/7TIN/kept-client-angular)
      * [Live Demo](https://kept-eight.vercel.app)

Once the backend is running locally, you can connect it with either the React or Angular frontend.

1.  **Clone the frontend repository** of your choice (React or Angular).
2.  **Follow the setup instructions** in the frontend repository's README file.
3.  **Update the API endpoint**. In the frontend's configuration, you will need to change the API endpoint to point to your local backend server, which is `http://localhost:8080`.

-----

## Environment Setup

### Development Environment

You will need to set up the `application-dev.properties` file in the `src/main/resources` directory. This file is ignored by Git, so you will need to create it yourself.

Create a file named `application-dev.properties` in `src/main/resources` and add the following content. Replace the placeholder values with your own.

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password

jwt.secret=your_jwt_secret
together.api.key=your_together_api_key
cors.allowed.origin=http://localhost:3000
```

  * `spring.datasource.url`: The connection URL for your PostgreSQL database.
  * `spring.datasource.username`: The username for your database.
  * `spring.datasource.password`: The password for your database.
  * `jwt.secret`: A secret key for signing JWTs. You can generate one online.
  * `together.api.key`: Your API key for the Together API.
  * `cors.allowed.origin`: The origin from which you will be making requests to the backend. This is typically your frontend application's URL.

### Production Environment

For a production environment, you will use the `application-prod.properties` file. These properties are set using environment variables.

```properties
spring.datasource.url=${JDBC_DATABASE_URL}
spring.datasource.username=${JDBC_DATABASE_USERNAME}
spring.datasource.password=${JDBC_DATABASE_PASSWORD}

jwt.secret=${jwt_secret}
together.api.key=${together_api_key}
cors.allowed.origin=${CLIENT_URL_PROD}
```

  * `JDBC_DATABASE_URL`: The connection URL for your production PostgreSQL database.
  * `JDBC_DATABASE_USERNAME`: The username for your production database.
  * `JDBC_DATABASE_PASSWORD`: The password for your production database.
  * `jwt_secret`: The secret key for signing JWTs in production.
  * `together_api_key`: Your API key for the Together API in production.
  * `CLIENT_URL_PROD`: The URL of your deployed frontend application.

-----

## License

This project is open source and available under the MIT License. See the [LICENSE](https://www.google.com/search?q=https://github.com/git/git-scm.com/blob/main/MIT-LICENSE.txt) file for more info.
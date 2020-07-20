# GidiLibrary

GidiLibrary is an API for books where you (as a librarian) can add, delete, update, get and lend books to users.
GidiLibrary uses an in memory database, this implies that everytime you start or restart the server,
you have an empty database to work with and as a matter of fact, you have to populate this one with data
before you begin to use it.

A user typically goes to borrow a book and the lender issues the book if some conditions are satisfied - like 
the availability of the book, the user status(whether the user is registered or not).
The user needs to be registered by the lender before the user can begin to borrow books.
The movements of books and details can be tracked in the BOOK_TRANSACTION table which has a unidirectional 
one-to-one relationship with the BOOK table.
The application is secured with form-based authentication since it is a basic rest api.

## Dependencies:
- Spring web
- Spring security
- Spring Data jpa
- H2 in-memory database
- Swagger
- JUnit 



To run the application with docker without cloning the repository you do
  `docker run -p 5000:8080 -t smartpro/gidilibrary`
   
   This pulls the docker image and runs the application using localhost:5000
   as the default host and port.

Now try localhost:5000/api/v1
If you see a welcome message and an emoji this shows the application is up an running

If you prefer to build the docker image yourself then you have to clone the
repository and on the terminal navigate to the project and run the following command:

  mvn clean package
  
  This creates a jar file in the target directory of the project.

   docker build -t smartpro/gidilibrary . 

   This builds the docker image with all its dependencies.

   `docker run -p 5000:8080 -t smartpro/gidilibrary`
   
   As described earlier, this pulls the image and runs the application
 

Full documentation for interacting with the APIs is available on the endpoints below
- `/swagger-ui.html`
- `/v2/api-docs`

An example of the full route to interact with the api is given below:

`http://localhost:5000/api/v1/books`

To perform most of the api operations, you need to have the role of a LIBRARIAN. 
Log in as a librarian with the credentials below:

- username: `john`
- password: `password`


Happy Booking!

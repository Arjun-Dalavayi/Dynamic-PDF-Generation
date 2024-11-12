Dynamic PDF Creation with Spring Boot and iText
This Spring Boot application provides a REST API to dynamically generate and download PDFs based on user input. If the requested data already exists in the MongoDB database, the application will fetch the PDF from the database and serve it for download. If the data does not exist, the PDF is generated using iText Java Template Engine, stored in the database, and made available for download.

Features
Generate and Store PDF: The PDF is generated based on user input using iText Java Template Engine and stored in MongoDB.
Fetch from Database: If the PDF already exists in the database, it is retrieved and served for download.
Single Endpoint: Both PDF generation, storage, and download are handled by a single endpoint.
Database Integration: Stores generated PDFs in MongoDB to avoid redundant generation.

Technologies Used
Java (JDK 11 or higher)
Spring Boot (for REST API and backend logic)
iText (for PDF generation using Java Template Engine)
Maven (for dependency management)
MongoDB (for storing PDFs)

Requirements
Java 11 or higher
Maven 3.6+
Spring Boot 2.6+
MongoDB (for storing PDFs)
iText (for generating PDFs)
Required dependencies can be installed via Maven

Setup
1. Clone the repository:
bash
git clone https://github.com/your-username/dynamic-pdf-creation.git
cd dynamic-pdf-creation

3. Install dependencies:
bash
mvn clean install

5. Configure MongoDB connection:
Configure your MongoDB connection in application.properties (or application.yml).
properties
spring.data.mongodb.uri=mongodb://localhost:27017/pdf_db
Make sure you have a MongoDB collection to store PDFs.

4. Run the application:
bash
mvn spring-boot:run
Once the application is running, the API will be available at http://localhost:8080.

API Endpoint
Generate and Download PDF
Endpoint: /api/generate
Method: POST
Description: Accepts a JSON object with data, checks if a PDF with that data already exists in the MongoDB database. If it exists, the PDF is served for download; otherwise, a new PDF is generated using iText, stored in the database, and then downloaded.

Request Body:
json
{
   "seller": "seller Name",
   "sellerGstin": "Gst",
   "sellerAddress": "Seller Address",
   "buyer": "buyer name",
   "buyerGstin": "Gst",
   "buyerAddress": "Buyer Address",
   "items": [
       {
           "name": "Product name",
           "quantity": "Product quantity",
           "rate": Product rate,
           "amount": Product amount
       }
       
   ]
}

Response: The PDF is either served directly from the database or generated and served for immediate download.
Example Use Case
Send a POST request to /api/generate with the data you want in the PDF.
The server checks if the PDF with the provided data already exists in the MongoDB database.
If the PDF exists, it will be retrieved from the database and served for download.
If the PDF does not exist, the server will generate the PDF using iText, store it in the database, and serve it for download.

Example Request
Request:
json
{
   "seller": "XYZ Pvt. Ltd.",
   "sellerGstin": "29AABBCCDD121ZD",
   "sellerAddress": "New Delhi, India",
   "buyer": "Vedant Computers",
   "buyerGstin": "29AABBCCDD131ZD",
   "buyerAddress": "New Delhi, India",
   "items": [
       {
           "name": "Product 1",
           "quantity": "12 Nos",
           "rate": 123.00,
           "amount": 1476.00
       }
       
   ]
}

Response:
If the PDF does not exist:
The server generates and stores the PDF in MongoDB using iText, then serves it for download.
And returns PDF generated Path
Example:
PDF generated at: C:\Users\ADMIN\Desktop\pdfGeneration\<pdf Name>.pdf

If the PDF already exists:
The server retrieves it from MongoDB and serves it for download.
And returns PDF generated Path
Example:
PDF generated at: C:\Users\ADMIN\Desktop\pdfGeneration\<pdf Name>.pdf------its from DB--------

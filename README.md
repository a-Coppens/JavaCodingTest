# JavaCodingTest

## AWS URL
1. The application is currently hosted on AWS' Fargate through ECR at http://13.238.253.210:8080/generate
2. The application requires a body to return anything, this can be done through an api tool such as Postman - https://www.postman.com/
3. Use a get request and hitting the above mentioned endpoint with a body formatted like below 

![image](https://user-images.githubusercontent.com/45225697/128113269-1db41053-5387-4eec-8dc0-be22886e1eac.png)

4 . The API should send a response body such as the one below

![image](https://user-images.githubusercontent.com/45225697/128113363-0e26efa6-0f9e-4953-a7e8-fc7fb2591176.png)


## Deployment
1. Run Program from CodingTestApplication.java Start Point (Backend currently runs on localhost:8080)
2. Using an API testing tool such as Postman send JSON objects as a GET request formatted like below through the body to the endpoint: http://localhost:8080/generate ![image]![image](https://user-images.githubusercontent.com/45225697/127952515-809ad75c-b4c6-4c3e-8d52-ab6bc1966767.png)
3. Verify Results <br />![image](https://user-images.githubusercontent.com/45225697/127952367-ed0b18aa-2b39-4ad1-8e8a-388ec14c4544.png)

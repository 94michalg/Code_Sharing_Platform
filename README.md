# Code_Sharing_Platform
## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Features](#features)
* [How to use](#how-to-use)
* [Inspiration](#inspiration)

## General info
Platform makes it easier to share your code with others. It has 2 parts with the same functionality: server-side API, which use REST API requests and simple HTML webpages. Code snippets are stored in H2 database. Freemarker has been used to generate HTML websites.
## Technologies
* Gradle
* Spring
* Freemarker
* H2 database
## Features
* Share your code with others
*	Every code has its own UUID
*	Code snippets can be public or private
*	Check latest 10 public codes
*	Simply restrict number of views or time limit of your code
## How to use
To share a piece of code you can visit /code/new, fill the form and press "Submit" button or send a JSON with necessary information via POST request to /api/code/new. JSON needs to contain "code" key, but "views" and "time" keys aren't obligatory. If you choose to apply any restrictions to the code it becomes private and can be accessed only by it's UUID. As a result of posting the code you will receive it's UUID. 
![POST example](/images/post.JPG)
You can check 10 latest public code snippets by sending GET request to /api/code/latest or by simply browsing /code/latest website.
To view a private code you have to send GET request to /api/code/UUID or browse for /code/UUID. 
![GET example](/images/get.JPG)
## Inspiration
Project has been made as a part of Jetbrain Academy course.

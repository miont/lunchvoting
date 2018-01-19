Restaurant voting system
===============================

###REST API brief description
application deployed in application context `lunchvoting`

***Get all users***

GET /api/admin/users 

***Get user with id = :id***

GET /api/admin/users/:id 

***Create user***

POST /api/admin/users

***Update user with id = :id***

PUT /api/admin/users/:id 

***Delete user with id = :id***

DELETE /api/admin/users/:id

***Get authorized user***

GET /api/profile/

***Update authorized user***

PUT /api/profile/

***Delete authorized user***

DELETE /api/profile/

***Get all restaurants***

GET /api/restaurants/

***Get restaurant with id = :id***

GET /api/restaurants/:id 

***Create restaurant***

POST /api/admin/restaurants/

***Update restaurant with id = :id***

PUT /api/admin/restaurants/:id 

***Delete restaurant with id = :id***

DELETE /api/admin/restaurants/:id

***Get all dishes for restaurant with id = :restaurant_id***

GET /api/restaurants/:restaurant_id/dishes/

***Get dish with id = :id for restaurant with id = :restaurant_id***

GET /api/restaurants/:restaurant_id/dishes/:id 

***Create dish for restaurant with id = :restaurant_id***

POST /api/admin/restaurants/:restaurant_id/dishes/

***Update dish with id = :id for restaurant with id = :restaurant_id***

PUT /api/admin/restaurants/:restaurant_id/dishes/:id 

***Delete dish with id = :id for restaurant with id = :restaurant_id***

DELETE /api/admin/restaurants/:restaurant_id/dishes/:id 

***Create vote (data send as json)***

POST /api/votes

***Create vote for restaurant with id = :id***

POST /api/restaurants/:id/votes

***Get votes for restaurant with id = :id***

GET /api/restaurants/:id/votes

***Get votes for current user***

GET /api/profile/votes


###REST API detail description

**Show User**
----
  Returns json data about a single user.

* **URL**

  /api/admin/users/:id

* **Method:**

  `GET`
  
*  **URL Params**

   **Required:**
 
   `id=[long]`

* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `{"id":1,"username":"bigboss","email":"realchuck@gmail.com","firstName":"Chuck","lastName":"Norris","roles":["ADMIN","USER"]}`
 
* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
    **Content:** ``

  OR

  * **Code:** 401 UNAUTHORIZED <br />
    **Content:** ``

* **Sample Call:**

  ```
    `curl -s http://localhost:8080/lunchvoting/api/admin/users/2 --user bigboss:12345`
  ```
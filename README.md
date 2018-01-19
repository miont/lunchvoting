Restaurant voting system
===============================

### REST API brief description
application deployed in application context `lunchvoting`

***Get all users***

GET /api/admin/users

 `curl -s http://localhost:8080/lunchvoting/api/admin/users --user bigboss:12345`

***Get user with id = :id***

GET /api/admin/users/:id 

`curl -s http://localhost:8080/lunchvoting/api/admin/users/2 --user bigboss:12345`

***Create user***

POST /api/admin/users

`curl -s -X POST -d '{"username":"bobross", "password":"password","email":"bob@gmail.com","firstName":"Bob","lastName":"Ross","roles":["USER"]}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/lunchvoting/api/admin/users/ --user bigboss:12345`

***Update user with id = :id***

PUT /api/admin/users/:id
 
`curl -s -X PUT -d '{"username":"casualnerd","password":"12345","email":"tommy@gmail.com","firstName":"John","lastName":"Cena","roles":["USER"]}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/lunchvoting/api/admin/users/2 --user bigboss:12345` 

***Delete user with id = :id***

DELETE /api/admin/users/:id

`curl -s -X DELETE http://localhost:8080/lunchvoting/api/admin/users/2 --user bigboss:12345`

***Get authorized user***

GET /api/profile/

`curl -s http://localhost:8080/lunchvoting/api/profile/ --user casualnerd:54321`

***Update authorized user***

PUT /api/profile/

***Delete authorized user***

DELETE /api/profile/

***Get all restaurants***

GET /api/restaurants/

`curl -s http://localhost:8080/lunchvoting/api/restaurants`

***Get restaurant with id = :id***

GET /api/restaurants/:id
 
`curl -s http://localhost:8080/lunchvoting/api/restaurants/1`

***Create restaurant***

POST /api/admin/restaurants/

`curl -s -X POST -d '{"name":"Gabriel Kreuther","address":"41 W 42nd St, Grace Building, New York City","url":"www.gknyc.com"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/lunchvoting/api/admin/restaurants --user bigboss:12345`

***Update restaurant with id = :id***

PUT /api/admin/restaurants/:id 

`curl -s -X PUT -d '{"name":"Burger King","address":"Moscow, Red Square","url":"http://www.kingburger.com"}' -H 'Content-Type: application/json' http://localhost:8080/lunchvoting/api/admin/restaurants/1 --user bigboss:12345`

***Delete restaurant with id = :id***

DELETE /api/admin/restaurants/:id

`curl -s -X DELETE http://localhost:8080/lunchvoting/api/admin/restaurants/1 --user bigboss:12345`

***Get all dishes for restaurant with id = :restaurant_id***

GET /api/restaurants/:restaurant_id/dishes/

`curl -s http://localhost:8080/lunchvoting/api/restaurants/2/dishes`

***Get dish with id = :id for restaurant with id = :restaurant_id***

GET /api/restaurants/:restaurant_id/dishes/:id 

`curl -s http://localhost:8080/lunchvoting/api/restaurants/1/dishes/2`

***Create dish for restaurant with id = :restaurant_id***

POST /api/admin/restaurants/:restaurant_id/dishes/

`curl -s -X POST -d '{"name":"burger","price":"777","date":"2018-01-19"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/lunchvoting/api/admin/restaurants/2/dishes --user bigboss:12345`

***Update dish with id = :id for restaurant with id = :restaurant_id***

PUT /api/admin/restaurants/:restaurant_id/dishes/:id 

`curl -s -X PUT -d '{"id":"1","name":"salad","price":"1000","date":"2018-01-19"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/lunchvoting/api/admin/restaurants/1/dishes/1 --user bigboss:12345`

***Delete dish with id = :id for restaurant with id = :restaurant_id***

DELETE /api/admin/restaurants/:restaurant_id/dishes/:id 

`curl -s -X DELETE http://localhost:8080/lunchvoting/api/admin/restaurants/2/dishes/8 --user bigboss:12345`

***Create vote (data send as json)***

POST /api/votes

`curl -s -X POST -d '{"restaurantId":"1", "date":"2018-01-19", "time":"16:00:00"}' -H 'Content-Type:application/json' http://localhost:8080/lunchvoting/api/votes/ --user justuser:qwerty`

***Create vote for restaurant with id = :id***

POST /api/restaurants/:id/votes

***Get votes for restaurant with id = :id***

GET /api/admin/restaurants/:id/votes

`curl -s http://localhost:8080/lunchvoting/api/admin/restaurants/1/votes --user bigboss:12345`

***Get votes for user with id = :id***

GET /api/admin/users/:id/votes

`curl -s http://localhost:8080/lunchvoting/api/admin/users/2/votes --user bigboss:12345`

***Get votes for current user***

GET /api/profile/votes

`curl -s http://localhost:8080/lunchvoting/api/profile/votes --user casualnerd:54321`
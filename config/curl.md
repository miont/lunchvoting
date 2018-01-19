### Test REST API (application deployed in application context `lunchvoting`)
disable csrf in spring-security.xml to test with curl

#### get All users
`curl -s http://localhost:8080/lunchvoting/api/admin/users --user bigboss:12345`

#### get user 2
`curl -s http://localhost:8080/lunchvoting/api/admin/users/2 --user bigboss:12345`

#### create user
`curl -s -X POST -d '{"username":"bobross", "password":"password","email":"bob@gmail.com","firstName":"Bob","lastName":"Ross","roles":["USER"]}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/lunchvoting/api/admin/users/ --user bigboss:12345`

#### update user
`curl -s -X PUT -d '{"username":"casualnerd","password":"12345","email":"tommy@gmail.com","firstName":"John","lastName":"Cena","roles":["USER"]}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/lunchvoting/api/admin/users/2 --user bigboss:12345`

#### delete user
`curl -s -X DELETE http://localhost:8080/lunchvoting/api/admin/users/2 --user bigboss:12345`

#### get user data from profile
`curl -s http://localhost:8080/lunchvoting/api/profile/ --user casualnerd:54321`

#### get All restaurants
`curl -s http://localhost:8080/lunchvoting/api/restaurants`

#### get restaurant 1
`curl -s http://localhost:8080/lunchvoting/api/restaurants/1`

#### create restaurant
`curl -s -X POST -d '{"name":"Gabriel Kreuther","address":"41 W 42nd St, Grace Building, New York City","url":"www.gknyc.com"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/lunchvoting/api/admin/restaurants --user bigboss:12345`

#### update restaurant
`curl -s -X PUT -d '{"name":"Burger King","address":"Moscow, Red Square","url":"http://www.kingburger.com"}' -H 'Content-Type: application/json' http://localhost:8080/lunchvoting/api/admin/restaurants/1 --user bigboss:12345`

#### delete restaurant
`curl -s -X DELETE http://localhost:8080/lunchvoting/api/admin/restaurants/1 --user bigboss:12345`

#### get restaurants
`curl -s http://localhost:8080/lunchvoting/api/restaurants`

#### get All dishes for restaurant 2
`curl -s http://localhost:8080/lunchvoting/api/restaurants/2/dishes`

#### get dish 2 for restaurant 1
`curl -s http://localhost:8080/lunchvoting/api/restaurants/1/dishes/2`

#### create dish for restaurant 2
`curl -s -X POST -d '{"name":"burger","price":"777","date":"2018-01-19"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/lunchvoting/api/admin/restaurants/2/dishes --user bigboss:12345`

#### update dish 1 for restaurant 1
`curl -s -X PUT -d '{"id":"1","name":"salad","price":"1000","date":"2018-01-19"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/lunchvoting/api/admin/restaurants/1/dishes/1 --user bigboss:12345`

#### delete dish 8 for restaurant 2
`curl -s -X DELETE http://localhost:8080/lunchvoting/api/admin/restaurants/2/dishes/8 --user bigboss:12345`

#### create vote for current user
`curl -s -X POST -d '{"restaurantId":"1", "date":"2018-01-19", "time":"16:00:00"}' -H 'Content-Type:application/json' http://localhost:8080/lunchvoting/api/votes/ --user justuser:qwerty`

#### get all votes for restaurant 1
`curl -s http://localhost:8080/lunchvoting/api/admin/restaurants/1/votes --user bigboss:12345`

#### get all votes for user 2
`curl -s http://localhost:8080/lunchvoting/api/admin/users/2/votes --user bigboss:12345`

#### get all votes for profile
`curl -s http://localhost:8080/lunchvoting/api/profile/votes --user casualnerd:54321`
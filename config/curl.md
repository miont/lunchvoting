### Test REST API (application deployed in application context `lunchvoting`)
disable csrf in spring-security.xml to test with curl

#### get All users
`curl -s http://localhost:8080/lunchvoting/api/admin/users --user bigboss:12345`

#### get user 2
`curl -s http://localhost:8080/lunchvoting/api/admin/users/2 --user bigboss:12345`

#### get user data from profile
`curl -s http://localhost:8080/lunchvoting/api/profile/ --user casualnerd:54321`

#### get All restaurants
`curl -s http://localhost:8080/lunchvoting/api/restaurants`

#### get restaurant 1
`curl -s http://localhost:8080/lunchvoting/api/restaurants/1`

#### create restaurant
`curl -s -X POST -d '{"name":"Gabriel Kreuther","address":"41 W 42nd St, Grace Building, New York City","url":"www.gknyc.com"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/lunchvoting/api/admin/restaurants --user bigboss:12345`

#### update restaurant
`curl -s -X PUT -d '{"id":2,"name":"J B Alberto's Pizza","address":"633 N Saint Clair St, Chicago","url":"http://www.thecapitalgrille.com"}' -H 'Content-Type: application/json' http://localhost:8080/lunchvoting/api/admin/restaurants/1 --user bigboss:12345`

#### delete restaurant
`curl -s -X DELETE http://localhost:8080/lunchvoting/api/admin/restaurants/1 --user bigboss:12345`
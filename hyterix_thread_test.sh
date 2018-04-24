#!/bin/bash
CURL='/usr/bin/curl'
RVMHTTP="http://localhost:8080/getBookDetailsBasedOnId"
CURLARGS="-X POST"

# you can store the result in a variable
for i in 1 2 3 4 5
do
  curl -H "Content-Type: application/json" -X POST -d '{"bookId":"B1"}' $RVMHTTP  >> curl.log
#  sleep 0.5
done

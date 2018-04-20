#!/bin/bash
CURL='/usr/bin/curl'
RVMHTTP="localhost:3030/recommended-withdelay"
CURLARGS="-X GET"

# you can store the result in a variable
for i in 1 2 3 4 5
do
  $CURL $CURLARGS $RVMHTTP >> curl.log
#  sleep 0.5
done

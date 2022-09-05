#!/bin/sh
docker run -u gradle --name check-app -p 80:8080 -d -v "$PWD":/home/gradle/project -w /home/gradle/project gradle:7.5-jdk11 gradle bootRun
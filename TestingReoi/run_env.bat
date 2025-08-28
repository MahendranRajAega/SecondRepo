@echo off
SET ENV=%1

REM Set port dynamically
IF "%ENV%"=="dev" SET PORT=1990
IF "%ENV%"=="sit" SET PORT=1991
IF "%ENV%"=="prod" SET PORT=1992

REM Start Spring Boot in detached mode
start "" java -jar target/myapp.jar --spring.profiles.active=%ENV% > app-%ENV%.log 2>&1
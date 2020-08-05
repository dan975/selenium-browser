#!/bin/bash

#Go to repo root
cd ..

#Update Allure report history, execute test suite and serve Allure report.
#Remove old test run results.
rm -r ./allure-results/*
#Execute test suite.
mvn clean test -Dspring.profiles.active=$1,$2 -Dcucumber.filter.tags="@$1 and @$2"
#Copy history from Allure report to Allure test run results.
cp -R ./allure-report/history/ ./allure-results/history/
#Generate Allure report for last test run.
allure generate allure-results --clean -o allure-report
#Serve Allure report. Opens new browser tab, window in default browser with report.
allure serve allure-results
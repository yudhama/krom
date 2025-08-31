# Mobile & API Automation Testing Framework

A comprehensive BDD-based automation testing framework for Android UI and API testing using Cucumber, Appium, REST Assured, and TestNG.

## 📋 Table of Contents

- [Project Overview](#project-overview)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Installation & Setup](#installation--setup)
- [Configuration](#configuration)
- [Running Tests](#running-tests)
- [Test Reports](#test-reports)
- [Features](#features)
- [Contributing](#contributing)

## 🎯 Project Overview

This framework provides:
- **Android UI Testing**: Automated testing of mobile applications using Appium
- **API Testing**: RESTful API testing using REST Assured
- **BDD Approach**: Behavior-driven development with Cucumber
- **Parallel Execution**: Support for concurrent test execution
- **Comprehensive Reporting**: HTML reports with screenshots and detailed logs
- **Cross-platform Support**: Works on Windows, macOS, and Linux

## 📁 Project Structure krom

## How to run
1. Clone the repository
2. Install dependencies
3. Set up Appium server
4. Configure devices/emulators
5. Run tests -> `mvn test` 
|| cd into `api-tests` and `mvn clean test -Dcucumber.filter.tags="@api"` 
|| cd into `android-ui-tests` and `mvn clean test -Dcucumber.filter.tags="@login"` 
|| cd into `android-ui-tests` and `mvn clean test -Dcucumber.filter.tags="@cart"` 
|| cd into `android-ui-tests` and `mvn clean test -Dcucumber.filter.tags="@products"` 
6. View test results
7. Analyze test logs

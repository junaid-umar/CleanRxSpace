
[![Android Build](https://github.com/junaid-umar/CleanRxSpace/actions/workflows/android_ci.yml/badge.svg)](https://github.com/junaid-umar/CleanRxSpace/actions/workflows/android_ci.yml)

# Welcome to CleanRxSpace App

It's an app built using the MVVM, Repository Pattern and Clean Architecture. It uses the spaceflightnewsapi Api to search blogs and view details(PENDING).


## Architecture
App is developed with Clean Architecture to maintain sepration of concern.

### Domain Layer

This layer contains pure business logic independent of platform and independent of other layers. This layer contains:

  1.  Business Models
  2.  Data Source Interfaces
  3.  Repositories
  4.  Use Cases

### Data Layer

This layer contains data sources implementation provided by Domain layer. Each layer has its own model, if project size increases each layer may have their own representation and communication model


## Presentation Layer
This layer contains android specific code. it handles user interactions and actions.


## Test
This applications is covered unit and integration test. 

## CI
This applications has continuous integration using GitHub actions


## Libraries
  1.  Material Design - UI design
  2.  AndroidX - ViewModel, LiveData
  3.  RxJava
  4.  Hilt -  Dependency Injection
  5.  Navigation Component - User navigation
  6.  Glide - Loading Images
  7.  Room - Database Storage
  8.  Retrofit - API Calls
  9.  Junit, mockito, MockWebServer, hilt Testing, Truth 


## Backlog

UI Test and UI improvement 

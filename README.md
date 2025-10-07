# Readme


## Opportunity

Testing: Unit Test, Integration Test, End2End Test<br>
CI/CD: pipelines integration<br>
UI/UX: Components, DarkMode, Atomic Design, <br>
Practice: Kotlin corutines, hillt DI, Modularization <br>


## main feature
Nasa Project based on api https://api.nasa.gov/<br> 
show image of day<br>
show close astheroids<br>

## thechnology

Kotlin<br>
Compose<br>
hillt<br>
retrofit<br>

## Architecture

feature based architecture<br>
**Clean architecture:** Data, Domain, Presentation<br>
MVVM<br>
Modularization<br>


### 🧩 Feature-Based Clean Architecture

```
App
│
├── features
│   ├── search
│   │   ├── presentation
│   │   │   ├── view
│   │   │   └── viewmodel
│   │   │
│   │   ├── domain
│   │   │   ├── entities
│   │   │   ├── usecases
│   │   │   └── repository (interface)
│   │   │
│   │   └── data
│   │       ├── repositoryimpl
│   │       ├── datasource
│   │       │   ├── remote
│   │       │   └── local
│   │       └── mappers
│   │
│   └── profile
│       ├── presentation
│       ├── domain
│       └── data
│
└── core
    ├── network
    ├── storage
    ├── common
    └── utils
```






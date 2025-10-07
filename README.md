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

## Current thechnology

Kotlin<br>
Compose<br>
hillt<br>
retrofit<br>

## Architecture

feature based architecture<br>
**Clean architecture:** Data, Domain, Presentation<br>
MVVM<br>
Modularization<br>
🧱 Feature-Based Clean Architecture

📱 App
│
├── Feature: feature 1
│   ├── Presentation
│   │   ├── View
│   │   └── ViewModel
│   │
│   ├── Domain
│   │   ├── Entities
│   │   ├── UseCases
│   │   └── Repository (Interface)
│   │
│   └── Data
│       ├── RepositoryImpl
│       ├── DataSource
│       │   ├── Remote
│       │   └── Local
│       └── Mappers
│
├── Feature: Feature 2
│   ├── Presentation
│   ├── Domain
│   └── Data
│
└── Core
    ├── Network
    ├── Storage
    ├── Common
    └── Utils




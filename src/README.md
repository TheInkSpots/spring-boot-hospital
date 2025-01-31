```mvn spring-boot: run```


prms/  
├── src/  
│   └── main/  
│       ├── java/  
│       │   └── com/  
│       │       └── example/  
│       │           └── prms/  
│       │               ├── config/  
│       │               │   └── SecurityConfig.java  
│       │               ├── controller/  
│       │               │   ├── PatientController.java  
│       │               │   └── AuditLogController.java  
│       │               ├── entity/  
│       │               │   ├── Patient.java  
│       │               │   ├── User.java  
│       │               │   └── Role.java  
│       │               ├── repository/  
│       │               │   ├── PatientRepository.java  
│       │               │   ├── UserRepository.java  
│       │               │   └── AuditLogRepository.java  
│       │               ├── service/  
│       │               │   ├── PatientService.java  
│       │               │   └── ReminderService.java  
│       │               ├── aspect/  
│       │               │   └── AuditAspect.java  
│       │               └── PrmsApplication.java  
│       └── resources/  
│           ├── application.properties  
│           └── data.sql (optional)  
├── pom.xml  
└── Dockerfile (optional)

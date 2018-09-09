# Spring Data JPA and Hibernate bulk insert techniques

## Overview

This projects uses a range of techniques to try and find the best way to bulk insert records into a MySQL database using Spring Data JPA.

## Approaches

### 1 - Flush and Clear AutoID with Hibernate batching

In this technique the ID for the to be persisted entity is generated using the AUTO generation strategy.

```@GeneratedValue(strategy = GenerationType.AUTO)```

The EntityManager Flush and Clear technique taken from https://frightanic.com/software-development/jpa-batch-inserts/ is then used to insert the records and Hibernate batching is enabled using the following configuration properties
                                                                                                                                                         
```
spring.datasource.url=jdbc:mysql://localhost:3306/test_db?createdatabaseifnotexist=true&rewriteBatchedStatements=true&useSSL=false
spring.jpa.properties.hibernate.jdbc.batch_size=100
```

### 2 - Flush and Clear ManualId with Hibernate batching

In this technique the ID for the to be persisted entity is generated in the code by incrementing a integer for each entity that is created using a custom IdGenerator class. The entity class also implements the Persistable interface and returns true for the isNew method to ensure Hibernate views the record as a new enitiy rather than an update.

The EntityManager Flush and Clear technique taken from https://frightanic.com/software-development/jpa-batch-inserts/ is then used to insert the records and Hibernate batching is enabled using the following configuration properties
                                                                                                                                                         
```
spring.datasource.url=jdbc:mysql://localhost:3306/test_db?createdatabaseifnotexist=true&rewriteBatchedStatements=true&useSSL=false
spring.jpa.properties.hibernate.jdbc.batch_size=100
```

### 3 - JPARepository Auto ID

In this technique the ID for the to be persisted entity is generated using the AUTO generation strategy.

```@GeneratedValue(strategy = GenerationType.AUTO)```

A Spring JPARepositoty is then used to insert the records.

### 4 - JPARepository Auto ID with Hibernate batching

In this technique the ID for the to be persisted entity is generated using the AUTO generation strategy.

```@GeneratedValue(strategy = GenerationType.AUTO)```

A Spring JPARepository is then used to insert the records and Hibernate batching is enabled using the following configuration properties

```
spring.datasource.url=jdbc:mysql://localhost:3306/test_db?createdatabaseifnotexist=true&rewriteBatchedStatements=true&useSSL=false
spring.jpa.properties.hibernate.jdbc.batch_size=100
```

### 5 - JPARepository Manual ID

In this technique the ID for the to be persisted entity is generated in the code by incrementing a integer for each entity that is created using a custom IdGenerator class. The entity class also implements the Persistable interface and returns true for the isNew method to ensure Hibernate views the record as a new enitiy rather than an update.

A Spring JPARepository is then used to insert the records.

### 6 - JPARepository Manual ID with Hibernate batching

In this technique the ID for the to be persisted entity is generated in the code by incrementing a integer for each entity that is created using a custom IdGenerator class. The entity class also implements the Persistable interface and returns true for the isNew method to ensure Hibernate views the record as a new enitiy rather than an update.

A Spring JPARepository is then used to insert the records and Hibernate batching is enabled using the following configuration properties

```
spring.datasource.url=jdbc:mysql://localhost:3306/test_db?createdatabaseifnotexist=true&rewriteBatchedStatements=true&useSSL=false
spring.jpa.properties.hibernate.jdbc.batch_size=100
```

## Results

The tests results outlined below were run a laptop with a i7-8550U CPU and 8GB of RAM, to reduce the impact of rouge results the tests were run with 100 iterations of loading 10,000 records with each approach. The approaches are listed below in order of time taken with the quickest average time first

| Average Time | Approach |
| 272ms| 5 - JPARepository Manual ID with Hibernate batching |
| 288ms| 2 - Flush and Clear ManualId with Hibernate batching |
|1497ms| 6 - JPARepository Manual ID |
|1989ms| 1 - Flush and Clear AutoID with Hibernate batching |
|2542ms| 3 - JPARepository Auto ID |
|2986ms| 4 - JPARepository Auto ID with Hibernate batching |

The results above show the massive impact of hibernate batching and the way you generate id's on the time taken to bulk insert records into a MySQL database.
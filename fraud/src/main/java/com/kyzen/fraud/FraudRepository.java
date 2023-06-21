package com.kyzen.fraud;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FraudRepository extends MongoRepository<Fraud, String> {

   Optional<Fraud> findByCustomerId(long customerId);

   @Query("{'isFraudster':true}")
   List<Fraud> allFraudsters();

   @Query("{'customerId': ?0, 'isFraudster' : ?1")
   List<Fraud> idWithState(long customerId, boolean isFraudster);
   //number: {$gt:100, $lt:200}, year: {$nin:[2021,2022]}
}

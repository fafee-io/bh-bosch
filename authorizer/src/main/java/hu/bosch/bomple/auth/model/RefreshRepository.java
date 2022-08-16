package hu.bosch.bomple.auth.model;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RefreshRepository extends MongoRepository<RefreshEntity, String> {

    RefreshEntity findByUserIdAndGeneration(Long userId, String generation);

}

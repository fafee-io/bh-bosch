package hu.bosch.bomple.auth.model;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface RefreshRepository extends MongoRepository<RefreshEntity, String> {

    RefreshEntity findByUserIdAndGeneration(Long userId, String generation);

    List<RefreshEntity> findByTokenStartsWith(String prefix);

    @Query("{'userId': ?0, 'generation': ?1}")
    RefreshEntity findForRefresh(Long userId, String generation);

    @Query(value = "{'token': { $regex: ?0 }}", fields = "{ 'sensitiveInformation': 0 }")
    List<RefreshEntity> findNonSensitive(String tkRegex);

}

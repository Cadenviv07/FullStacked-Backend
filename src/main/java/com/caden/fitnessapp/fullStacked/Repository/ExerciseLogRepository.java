import org.springframework.data.mongodb.repository.MongoRepository;
import java.time.LocalDate;
import java.util.List;

public interface ExerciseLogRepository extends MongoRepository<ExerciseLog, String> {
    
    List<ExerciseLog> findByUserIdAndDate(String userId, LocalDate date);
}
package meeting.palnner.repository;

import meeting.palnner.entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long>{
    @Query(
            value = "select type from meeting  ",
            nativeQuery = true
    )
    List<String> getMeetingByalll();
	
}

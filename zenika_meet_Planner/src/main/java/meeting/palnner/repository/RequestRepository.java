package meeting.palnner.repository;
import java.util.List;

import meeting.palnner.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
	List<Request> findRequestsByRoomsId(Long meetingId);
	@Query(
			value = "select ending_hour as ending_hour,starting_hour as starting_hour,nbr_persons as nbr_persons ,meeting_id as meeting_id,date as date from request",
			nativeQuery = true
	)
	List<String> getAllrequest();
}

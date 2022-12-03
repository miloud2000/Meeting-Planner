package meeting.palnner.repository;
import java.util.List;

import meeting.palnner.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long>{

	
	List<Equipment> findEquipmentsByMeetingsId(Long meetingId);
}
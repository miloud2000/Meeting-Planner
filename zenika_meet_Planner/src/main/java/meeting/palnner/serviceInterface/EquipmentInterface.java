package meeting.palnner.serviceInterface;
import java.util.List;
import java.util.Optional;

import meeting.palnner.entity.Equipment;

public interface EquipmentInterface {
	void addEquipment(Equipment equipment);
	Optional<Equipment> findById(Long id);
	List<Equipment> findEquipmentsByMeetingsId(Long meetingId);
}

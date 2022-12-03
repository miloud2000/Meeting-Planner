package meeting.palnner.serviceImpl;
import java.util.List;
import java.util.Optional;

import meeting.palnner.entity.Equipment;
import meeting.palnner.repository.EquipmentRepository;
import meeting.palnner.serviceInterface.EquipmentInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipmentServiceImpl implements EquipmentInterface {
	@Autowired
    EquipmentRepository equipmentRespository;
	
	

	@Override
	public void addEquipment(Equipment equipment) {
		Equipment newEquipment = new Equipment();
		newEquipment.setName(equipment.getName());
		equipmentRespository.save(newEquipment);
		
	}



	@Override
	public Optional<Equipment> findById(Long id) {
		return equipmentRespository.findById(id);
	}



	@Override
	public List<Equipment> findEquipmentsByMeetingsId(Long meetingId) {
		return equipmentRespository.findEquipmentsByMeetingsId(meetingId);
	}
}

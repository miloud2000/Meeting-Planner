package meeting.palnner.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import meeting.palnner.entity.Room;
import meeting.palnner.repository.RoomRespository;
import meeting.palnner.serviceInterface.RoomInterface;

@Service
public class RoomServiceImpl implements RoomInterface {
	
	@Autowired
    RoomRespository roomRespository;
	
	

	@Override
	public void addRoom(Room room) {
		
		roomRespository.save(room);
		
	}



	@Override
	public List<Room> findRoomsByEquipmentsId(Long equipmentId) {
		return roomRespository.findRoomsByEquipmentsId(equipmentId);
	}



	@Override
	public List<Room> getAllRooms() {
		return roomRespository.findAll();
	}

  @Override
  public List<String> allrooms() {
	  return roomRespository.getAllrooms();
  };

	@Override
	public Optional<Room> findById(Long id) {
		return roomRespository.findById(id);
	}


}

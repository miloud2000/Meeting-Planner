package meeting.palnner.serviceInterface;

import java.util.List;
import java.util.Optional;

import meeting.palnner.entity.Request;
import meeting.palnner.entity.Room;


public interface RoomInterface {
	
	void addRoom(Room room);
	List<Room> findRoomsByEquipmentsId(Long equipmentId);
	List<Room> getAllRooms() ;
	Optional<Room> findById(Long id);


	List<String> allrooms();
}

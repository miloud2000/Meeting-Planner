package meeting.palnner.repository;

import java.util.List;

import meeting.palnner.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRespository extends JpaRepository<Room, Long> {
	List<Room> findRoomsByEquipmentsId(Long equipmentId);
	@Query(
			value = "select name ,number_places   from room",
			nativeQuery = true
	)
	List<String> getAllrooms();
	
}

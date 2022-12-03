package meeting.palnner.controller;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import meeting.palnner.entity.Equipment;
import meeting.palnner.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import meeting.palnner.serviceInterface.EquipmentInterface;
import meeting.palnner.serviceInterface.RoomInterface;

@RestController
public class RoomController {

	
    @Autowired
    RoomInterface roomService;
 
    @Autowired
    EquipmentInterface equipmentInterface;
  
	

    @GetMapping("/allrooms")
    public List<String> Allroom(){
      return  roomService.allrooms();
    }

    @PostMapping("/addroom")
    public String addRoom(@RequestBody Room room){
    	roomService.addRoom(room);
        return "Room Added successfully";
    }
    
    
    @PostMapping("/add_equipment_to_room")
    public String SaveEquipmentToRoom(@RequestBody Map<String, Long> map){
    	
    	//Find the room already stored in database
    	Optional < Room > roomToFind = roomService.findById(map.get("roomid"));
    	
    	//Find the equipment already stored in database 
    	Optional <Equipment> equipmentfToFind = equipmentInterface.findById(map.get("equipmentid"));
    	Equipment equipmentFound = new Equipment();
    	
    	// if meeting found
    	Room roomFound = new Room();
    	
    	if (roomToFind.isPresent()) {
            //pass the value
    		roomFound= roomToFind.get();
    		// if equipment found :
    		
    		if(equipmentfToFind.isPresent()) {
    			equipmentFound= equipmentfToFind.get();
    			roomFound.getEquipments().add(equipmentFound);
    			roomService.addRoom(roomFound);
    		}
    		else {
    			 System.out.printf("No equipment found");
    		}
    		
    		
			
        } else {
            System.out.printf("No Room found");
        }
		
        return "Added successfully";
    }

}

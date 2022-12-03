package meeting.palnner.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import meeting.palnner.entity.Equipment;
import meeting.palnner.entity.Meeting;
import meeting.palnner.serviceInterface.EquipmentInterface;
import meeting.palnner.serviceInterface.MeetingInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeetingController {
	
	@Autowired
	MeetingInterface meetingService;
	
	@Autowired
	EquipmentInterface equipmentInterface;
	
	
	@GetMapping("/all_meeting_type")
	public List<String> allMeeting(){
		return meetingService.allmeeting();
	}
    @PostMapping("/add_meeting_type")
    public String addMeeting(@RequestBody Meeting meeting){
    	meetingService.addMeeting(meeting);
         return "Meeting Type Added successfully";
    }
    
    
    @PostMapping("/Add_equipment_to_meeting_type")
    public String SaveEquipmentToMeeting(@RequestBody Map<String, Long> map){
    	
    	//Find the meeting already stored in database 
    	Optional < Meeting > meetinfToFind = meetingService.findById(map.get("meetingid"));
    	
    	//Find the equipment already stored in database 
    	Optional <Equipment> equipmentfToFind = equipmentInterface.findById(map.get("equipmentid"));
    	Equipment equipmentFound = new Equipment();
    	
    	// if meeting found
    	Meeting meetingFound = new Meeting();
    	
    	if (meetinfToFind.isPresent()) {
    		meetingFound= meetinfToFind.get();
    		// if equipment found :
    		
    		if(equipmentfToFind.isPresent()) {
    			equipmentFound= equipmentfToFind.get();
    			meetingFound.getEquipments().add(equipmentFound);
    			meetingService.addMeeting(meetingFound);
    		}
    		else {
    			 System.out.printf("No equipment found");
    		}
    		
    		
			
        } else {
            System.out.printf("No meeting found");
        }
		
        return "Added successfully";
    }
}

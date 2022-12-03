package meeting.palnner.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import meeting.palnner.entity.*;
import meeting.palnner.serviceInterface.EquipmentInterface;
import meeting.palnner.serviceInterface.MeetingInterface;
import meeting.palnner.serviceInterface.RequestInterface;
import meeting.palnner.serviceInterface.RoomInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {
	@Autowired
	RequestInterface requestInterface;

	@Autowired
	MeetingInterface meetingService;
	
	@Autowired
	EquipmentInterface equipmentInterface;

	@Autowired
	RoomInterface roomInterface;

	@GetMapping("/AllRequest")
	public List<String> allrequest(){
		return requestInterface.getallR();

	}
	@PostMapping("/save-request")
	public String SaveRequest(@RequestBody RequestTosave requestTosave){
		// get the meeting id from the request
		Long meetingid = requestTosave.getMeetingid();

		// find the meeting object by id
		Optional <Meeting> meetingToFind = meetingService.findById(meetingid);

		// if the meeting found
		Meeting meetingFound = new Meeting();
		if (meetingToFind.isPresent()) {
			meetingFound= meetingToFind.get();

			Request requestToSave =Request.builder()
					.date(requestTosave.getDate())
					.meeting(meetingFound)
					.startingHour(requestTosave.getStartingHour())
					// 2+Startinghour plus two Hours for cleaning
					.endingHour(requestTosave.getStartingHour()+2)
					.nbrPersons(requestTosave.getNbrPersons())
					.build();

			// save the request
			requestInterface.addRequest(requestToSave);


		} else {
			return  "sorry No meeting found with this ID type :"+ meetingid;
		}

		return "Saved successufully";
	}

	@GetMapping("/Suggested_room")
    public String Suggested_room(@RequestBody Request request){
		Request request1 = new Request();
		// to check if the request already exist
		Optional < Request > Request_db = requestInterface.findById(request.getId());
		Meeting meetingType = new Meeting();

		//check if the request  exists
		if (Request_db.isPresent()) {
			request1 = Request_db.get();
			
			//meeting type
			meetingType = Request_db.get().getMeeting();
			
        } else {
			return "No request found";
        }
		
		// equipment needed for meeting type
		List<Equipment> equipmentsNeeded = equipmentInterface.findEquipmentsByMeetingsId(meetingType.getId());
		List<Room> RoomsWithEquipments = new ArrayList<>();
		
		// find the rooms that have equipment for the meeting
		if (!equipmentsNeeded.isEmpty()) {
			
			for(Equipment equipment : equipmentsNeeded) {
				
				// search for the rooms by equipment id 
				List<Room> roomsNeeded = roomInterface.findRoomsByEquipmentsId(equipment.getId());
				
				for(Room room :roomsNeeded) {
					//Only  rooms with  70%*nbrPlace  >= number_of_persons
					if((room.getNbrplaces()*70/100)>= request1.getNbrPersons()){
						RoomsWithEquipments.add(room);
					}
				}
				
				
			}
		}
		else {
			// if there is no needed equipment, we will store all rooms that have 70% capacity >= nbr of person
			List<Room> roomsNeeded = roomInterface.getAllRooms();
			for(Room r :roomsNeeded) {
				// only keep rooms with ==> 70% capacity > nbr of person
				if((r.getNbrplaces()*70/100)>= request1.getNbrPersons()){
					RoomsWithEquipments.add(r);
				}
			}
			
		}
		
		
		
		

		//frequency of  based on equipement in the room
		Map<Long, Long> idFrequency = RoomsWithEquipments.stream().collect(Collectors.groupingBy(Room::getId,Collectors.counting()));
		Set<Entry<Long, Long>> set = idFrequency.entrySet();
		List<Entry<Long, Long>> list = new ArrayList<Entry<Long, Long>>(set);
		boolean freeRoom =true;
		// check availability for each room
		for (Entry<Long, Long> map : list) {
			
			// get the room id
		    Long id = map.getKey();
		    
		    // find the room already stored in the DB
		    Room ChosenRoom = new Room();
			Optional < Room > TheChosenRoom = roomInterface.findById(id);
			
			if (TheChosenRoom.isPresent()) {
				ChosenRoom = TheChosenRoom.get();
				
	        } else {

				return "No room found with id: "+ id;
	        }
			
			// check if there is reservations in this room at the same time with request
		    List<Request> requestWithSameRoom = requestInterface.FindRequestByRoomId(id);
		   
		    // if there is reservations :
		    if(!requestWithSameRoom.isEmpty()) {
		    	// for each reservation :
		    	 for (Request Requestwithsameroom : requestWithSameRoom)
		       {
		    		// if the room isn't available in this time, we will increment count  
					if((Requestwithsameroom.getDate().equals(request1.getDate()) &&
							Requestwithsameroom.getStartingHour()==request1.getStartingHour())
							 ) {
						freeRoom=false;
						
					}
			
				
		       }

			   if(!freeRoom) {
				    // if the count isn't equal to 0 , that means this room isn't available in this time so we will go to check the other room
			    	System.out.println("room is not free for this planing "+id);
			    	continue;
			   }
			   else {
				   // if this room is available so we will use it for the reservation
				   System.out.println("The best room for this request is the one with id : "+id);
				   ChosenRoom.getRequests().add(request1);
				   roomInterface.addRoom(ChosenRoom);
				   return "The best room for this request is : "+ ChosenRoom.getName() ;
			   }
		    	
		    }
		   
	  
		    else {
		    	// if this room is available so we will use it for the reservation
		    	System.out.println("The best room for this request is the one with id"+id);
		    	
		    	
		    	//save the chosen room with the request
		    	ChosenRoom.getRequests().add(request1);
		    	roomInterface.addRoom(ChosenRoom);
		    	return "The best room for this request is : "+ ChosenRoom.getName()  ;
		    	
		    }
		    
		   
		   
		}
		
         return "there is no rooms available";
    }
}

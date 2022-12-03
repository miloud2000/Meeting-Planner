package com.example.demo;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.transaction.Transactional;
import meeting.palnner.entity.Equipment;
import meeting.palnner.entity.Meeting;
import meeting.palnner.entity.Room;
import meeting.palnner.repository.EquipmentRepository;
import meeting.palnner.repository.MeetingRepository;
import meeting.palnner.repository.RequestRepository;
import meeting.palnner.repository.RoomRespository;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager
@Transactional
public class TDD {
	//@Autowired
	//private TestEntityManager entityManager;
	
	@Autowired
	MeetingRepository meetingRepository;
	
	@Autowired
	EquipmentRepository equipmentRepository;
	
	@Autowired
	RoomRespository roomRepository;
	
	@Autowired
	RequestRepository requestRepository;
	
	@Autowired
	private TestEntityManager entityManager;

	@Test
	@DisplayName("Save_meeting Test")
	public void Test_save_a_meeting() {
		
		Meeting NewMeetingType = Meeting.builder().type("VC").build();
		Meeting meeting = meetingRepository.save(NewMeetingType);
		assertThat(meeting).hasFieldOrPropertyWithValue("type", "VC");
	}
	///
	@Test
	@DisplayName("Save an equipment Test")
	public void Test_saving_equipment() {
		
		Equipment newEquipment = Equipment.builder().name("Camera").build();
		Equipment equipment = equipmentRepository.save(newEquipment);
		assertThat(equipment).hasFieldOrPropertyWithValue("name", "Camera");
	}

	@Test
	@DisplayName("Saving Room Test")
	public void Test_saving_room() {
		
		Room newRoom =  Room.builder().name("E3004").nbrplaces(23).id(22L).build();
		// return room saved
		Room room = roomRepository.save(newRoom);
		assertThat(room).hasFieldOrPropertyWithValue("name", "E3004");
	}

	@Test
	@DisplayName("Find Room By equipment Id")
	public void Test_find_rooms_by_equipments_id() {
		

		Equipment newEquipment = Equipment.builder().name("Ecran").build();
		entityManager.persist(newEquipment);
		//create room
		Room newRoom = Room.builder().name("E002").nbrplaces(20).build();
		Set<Equipment> equipments =  new HashSet<>();
		equipments.add(newEquipment);
		newRoom.setEquipments(equipments);
		entityManager.persist(newRoom);
		
		// we find the room by the equipment id
		List<Room> roomsFound = roomRepository.findRoomsByEquipmentsId(newEquipment.getId());
		assertThat(roomsFound.get(0)).isEqualTo(newRoom);
	}

		@Test
		@DisplayName("Find room")
		public void find_room() {
			
             // creae room1 and room2
			Room room1 =  Room.builder().name("E001").nbrplaces(20).build();
			entityManager.persist(room1);
			Room room2 = Room.builder().name("E002").nbrplaces(3).build();
			entityManager.persist(room2);
			// Create a list that contain room1 and room2
			List<Room> rooms = new ArrayList<Room>();
			rooms.add(room1);
			rooms.add(room2);
			//get all room from db
			List<Room> roomdb = roomRepository.findAll();
			// check if two room reference   are the same
			assertThat(roomdb).isEqualTo(rooms);
		}
	

}

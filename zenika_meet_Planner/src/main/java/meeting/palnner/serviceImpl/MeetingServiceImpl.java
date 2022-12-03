package meeting.palnner.serviceImpl;
import java.util.List;
import java.util.Optional;

import meeting.palnner.entity.Meeting;
import meeting.palnner.repository.MeetingRepository;
import meeting.palnner.serviceInterface.MeetingInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeetingServiceImpl  implements MeetingInterface {
	@Autowired
    MeetingRepository meetingRespository;
	
	

	@Override
	public void addMeeting(Meeting meeting) {
		
		meetingRespository.save(meeting);
		
	}
	@Override
	public List<String> allmeeting(){
		return meetingRespository.getMeetingByalll();

	}



	@Override
	public Optional<Meeting> findById(Long id) {
		return meetingRespository.findById(id);
	}
}

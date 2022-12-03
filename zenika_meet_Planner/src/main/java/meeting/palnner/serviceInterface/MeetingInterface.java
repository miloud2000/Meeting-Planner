package meeting.palnner.serviceInterface;

import java.util.List;
import java.util.Optional;

import meeting.palnner.entity.Meeting;

public interface MeetingInterface {
	void addMeeting(Meeting meeting);
	Optional<Meeting> findById(Long id);

    List<String> allmeeting();
}

package meeting.palnner.serviceInterface;
import java.util.List;
import java.util.Optional;

import meeting.palnner.entity.Request;

public interface RequestInterface {
	void addRequest(Request request);
	Optional<Request> findById(Long id);
	
	List<Request> FindRequestByRoomId(Long id);

    List<String> getallR();
}

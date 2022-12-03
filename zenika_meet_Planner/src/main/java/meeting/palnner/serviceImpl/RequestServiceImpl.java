package meeting.palnner.serviceImpl;
import java.util.List;
import java.util.Optional;

import meeting.palnner.entity.Request;
import meeting.palnner.repository.RequestRepository;
import meeting.palnner.serviceInterface.RequestInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestServiceImpl implements RequestInterface {
	@Autowired
    RequestRepository requestRespository;
	


	@Override
	public Optional<Request> findById(Long id) {
		return requestRespository.findById(id);
	}

 @Override
 public List<String> getallR(){
		return requestRespository.getAllrequest();
 };

	@Override
	public void addRequest(Request request) {
	
		requestRespository.save(request);	
	}



	@Override
	public List<Request> FindRequestByRoomId(Long id) {
		return requestRespository.findRequestsByRoomsId(id);
	}
}

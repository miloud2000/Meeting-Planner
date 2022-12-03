package meeting.palnner.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestTosave {
 
	private java.sql.Date date;
	private int startingHour ;
	private int endingHour;
	private int nbrPersons;
	private Long meetingid;
}

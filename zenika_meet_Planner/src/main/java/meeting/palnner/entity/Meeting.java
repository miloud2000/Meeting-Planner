package meeting.palnner.entity;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data   //  @Getter @Setter @RequiredArgsConstructor @ToString
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "meeting")
public class Meeting {
	
	@Id
	@SequenceGenerator(
			name="meeting_seq",
			sequenceName = "meeting_seq",
			allocationSize = 1
	)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "meeting_seq"
	)
    //@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;	// id of the meeting
	
	@Column(name = "type", unique = true)
	private String type;   // the type of the meeting
	
	@ManyToMany(fetch = FetchType.LAZY,
		      cascade = {
		          CascadeType.PERSIST,
		          CascadeType.MERGE
		      })
	@JoinTable(name = "meeting_equipments",joinColumns = { @JoinColumn(name = "meeting_id") },inverseJoinColumns = { @JoinColumn(name = "equipment_id") })
	private Set<Equipment> equipments = new HashSet<>();
	
    
    @OneToMany(mappedBy = "meeting", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Request> requests;
    
    public void addRequest(Request request){
    	requests.add(request);
    	request.setMeeting(this);
    }
}

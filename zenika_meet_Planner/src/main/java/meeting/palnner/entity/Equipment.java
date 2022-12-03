package meeting.palnner.entity;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data   // equal to  @Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode à la classe
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "equipment")
public class Equipment {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;	// id of the equipment
	
	@Column(name = "name", unique = true)
	private String name;   // name of the equipment
	
	@ManyToMany(fetch = FetchType.LAZY,
		      cascade = {
		          CascadeType.PERSIST,
		          CascadeType.MERGE
		      },
		      mappedBy = "equipments")
	@JsonIgnore
	private Set<Meeting> meetings = new HashSet<>();
	
	
	@ManyToMany(fetch = FetchType.LAZY,
		      cascade = {
		          CascadeType.PERSIST,
		          CascadeType.MERGE
		      },
		      mappedBy = "equipments")
	@JsonIgnore
	private Set<Room> rooms = new HashSet<>();
}

package training360.employeejpa;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data//lombok: legenerálja a gettereket, settereket
@NoArgsConstructor
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private LocalDateTime createdAt;

	private LocalDateTime modifiedAt;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Skill> skills = new ArrayList<>();

	public Employee(String name) {
		this.name = name;
	}

	@PrePersist
	public void initTimes(){
		createdAt = LocalDateTime.now();
		modifiedAt = LocalDateTime.now();
	}

	@PreUpdate
	public void setModifiedtime(){
		modifiedAt = LocalDateTime.now();
	}
}

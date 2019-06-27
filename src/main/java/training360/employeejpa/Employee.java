package training360.employeejpa;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data//lombok: legenerálja a gettereket, settereket
@NoArgsConstructor
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	public Employee(String name) {
		this.name = name;
	}

}

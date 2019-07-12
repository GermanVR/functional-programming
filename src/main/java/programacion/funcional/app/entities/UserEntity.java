package programacion.funcional.app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

	private String nombre;
	private String apellido;
	private Integer edades;
	private UserAddressEntity address;

}

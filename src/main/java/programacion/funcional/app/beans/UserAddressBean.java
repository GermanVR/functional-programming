package programacion.funcional.app.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAddressBean {

	private Integer id;
	private String nombre;
	private String colonia;
	private String municipio;
	private String cp;
	private String country;

	private AdressComplementBean adressComplement;
}

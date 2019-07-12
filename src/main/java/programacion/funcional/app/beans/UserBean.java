/**
 * 
 */
package programacion.funcional.app.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author German Vazquez Renteria
 *
 */

@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class UserBean {

	private String nombre;
	private String apellido;
	private Integer edad;
	private UserAddressBean address;

	public static void referenciaEstatico(String lol) {
		log.info("Probando refEst" + lol);
	}

	public void referenciaParticular() {
		log.info("Probando refParticular");
	}

	public void mostrarNombre() {
		log.info(nombre);
	}

	public UserBean(String nombre) {
		this.nombre = nombre;
	}

}

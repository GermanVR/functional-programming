package programacion.funcional.app;

import java.util.Arrays;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import programacion.funcional.app.beans.UserBean;
import programacion.funcional.app.service.IUserBean;

/**
 * @author German Vazquez Renteria
 *
 */
@Slf4j
public class App {
	public static void main(String[] args) {

		List<String> lista = Arrays.asList("A", "B", "C", "D", "E");
		lista.stream().forEach(a -> System.out.println(a));
		lista.stream().forEach(log::info);
//		Trabajo tL = () -> User.referenciaEstatico("Algo");
//		tL.accion();
//		User user = new User("Alberto");
//
////		Trabajo tMR = User::referenciaEstatico;
////		tMR.accion();
//
//		Trabajo trabajol2 = () -> user.referenciaParticular();
//		trabajol2.accion();
//
//		Trabajo t = user::referenciaParticular;
//		t.accion();
//
//		TrabajoString ts = (palabra) -> palabra.toUpperCase();
//		TrabajoString tsRm = String::toUpperCase;
//		System.out.println(tsRm.accion("Algo"));
//
//		List<User> userl = new ArrayList<>();
//		userl.add(new User("Alberto"));
//		userl.add(new User("Juan"));
//		userl.add(new User("Pedro"));
//		userl.add(new User("Paco"));
//
//		userl.forEach(nombre -> nombre.mostrarNombre());
//		userl.forEach(User::mostrarNombre);
//
		IUserBean a = new IUserBean() {

			@Override
			public UserBean crear(String nombre) {
				return new UserBean();
			}
		};

		IUserBean userAsLambda = nombre -> new UserBean(nombre);
		userAsLambda.crear("algo");
		log.info(userAsLambda.toString());
		IUserBean UserAsMethodReference = UserBean::new;
		log.info("value is: {}", UserAsMethodReference);
	}
}

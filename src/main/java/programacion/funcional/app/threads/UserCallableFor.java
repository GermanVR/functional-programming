package programacion.funcional.app.threads;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

import lombok.extern.slf4j.Slf4j;
import programacion.funcional.app.beans.UserBean;

/**
 * @author German Vazquez Renteria
 *
 */
@Slf4j
public class UserCallableFor implements Callable<Long> {

	private List<UserBean> listUsers;

	public UserCallableFor(List<UserBean> listUsers) {
		this.listUsers = listUsers;
	}

	@Override
	public Long call() throws Exception {
		Instant init = Instant.now();
		for (UserBean a : listUsers) {
			if (a.getEdad() > 80) {
				a.setApellido("Hollister");
				a.setNombre("forEach");
				if (a.getEdad() < 90) {
//					log.info("Bean forEach es: {}", a);
					a.setEdad(new Random().nextInt(100));
				}
			}
		}
		Instant end = Instant.now();
		return Duration.between(init, end).toMillis();
	}
}

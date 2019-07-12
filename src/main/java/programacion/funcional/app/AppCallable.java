package programacion.funcional.app;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import lombok.extern.slf4j.Slf4j;
import programacion.funcional.app.helpers.UserHelper;
import programacion.funcional.app.threads.UserCallableFor;
import programacion.funcional.app.threads.UserCallableStream;

/**
 * @author German Vazquez Renteria
 *
 */
@Slf4j
public class AppCallable {

	public static void main(String[] args) {

		for (int i = 0; i < 100; i++) {
			ExecutorService servicio = Executors.newFixedThreadPool(2);

			UserHelper us = new UserHelper();
			us.setUpUserBean();

			final Callable<Long> task = new UserCallableFor(us.getListUsers());
			final Callable<Long> task2 = new UserCallableStream(us.getListUsers());
			Future<Long> resultado1 = servicio.submit(task);
			Future<Long> resultado2 = servicio.submit(task2);
			try {
				log.info("Total ForEach 1 is: {}", resultado1.get());
				log.info("Total Stream 2 is: {}", resultado2.get());
			} catch (InterruptedException | ExecutionException e) {
				log.error("Fail to: {}", e.getMessage(), e);
				Thread.currentThread().interrupt();
			} finally {
				servicio.shutdown();
			}
		}
	}
}

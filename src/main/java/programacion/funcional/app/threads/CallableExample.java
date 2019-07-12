package programacion.funcional.app.threads;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.concurrent.Callable;

import lombok.extern.slf4j.Slf4j;

/**
 * @author German Vazquez Renteria
 *
 */
@Slf4j
public class CallableExample implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		log.info("Into to Call of Callable");
		Instant timeStart = Instant.now();
		Integer total = 0;
		for (int i = 0; i < 10000000; i++) {
			total += i + new Random().nextInt(1000);
		}
		log.info("Total is: {}", total);
		Instant timeEnd = Instant.now();
		Duration fin = Duration.between(timeStart, timeEnd);
		log.info("Time is: {}", Thread.currentThread().getName());
		return (int) fin.toMillis();
	}

}

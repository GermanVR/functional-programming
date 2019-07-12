package programacion.funcional.app;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

/**
 * @author German Vazquez Renteria
 *
 */
@Slf4j
public class AppDicctionaryOfHash {

	public static void main(String[] args) {

		Map<String, Integer> dic = new HashMap<>();

		dic.put("A", 1);
		dic.put("B", 2);
		dic.put("C", 3);
		dic.put("D", 4);
		dic.put("E", 5);
		dic.put("F", 6);
		dic.put("0", 7);
		dic.put("1", 8);
		dic.put("2", 9);
		dic.put("3", 10);
		dic.put("4", 11);
		dic.put("5", 12);
		dic.put("6", 13);
		dic.put("7", 14);
		dic.put("8", 15);
		dic.put("9", 16);

		String hash = "185F8DB32271FE25F561A6FC938B2E264306EC304EDA518007D1764826381969";
		Integer sumHash = 0;
		Instant init = Instant.now();
		log.info("Init is: {}", init);
		for (Integer posicion = 0; posicion < hash.length(); posicion++) {
			log.info("Caracter: " + hash.charAt(posicion));
			log.info("Posicion+++++: " + posicion);
			log.info("Dic es: " + dic.get(String.valueOf(hash.charAt(posicion))));
			sumHash += dic.get(String.valueOf(hash.charAt(posicion))) * (posicion + 1);
		}
		log.info("Suma de hash= " + sumHash);
		Integer xbinary = 0;

		for (Integer i = 0; i < hash.length(); i++) {
			if (Character.isDigit(hash.charAt(i))) {
				xbinary += hash.charAt(i);
			}
		}
		Instant end = Instant.now();
		log.info("XBinary is: {}", xbinary);
		log.info("HashCompuesto: " + xbinary + "" + sumHash);
		log.info("Duration is: {} Miliseconds", Duration.between(init, end).toMillis());
	}
}

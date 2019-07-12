package programacion.funcional.app;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.extern.slf4j.Slf4j;
import programacion.funcional.app.beans.UserBean;

/**
 * @author German Vazquez Renteria
 *
 */
@Slf4j
public class StreamConcepts {

	private static List<UserBean> listUsers = null;

	public static final Function<String, String> convertir = String::toUpperCase;

	public static void main(String[] args) {

		setUpUser();

		imprimirLista();

		map();

		filter();

		findFirst();

		flatMap();

		peek();

		skipAndLimit();

		sorted();

		minAndMax();

		distinct();

		allMatchAnyMatchNoneMatch();

		sumAverangeRange();

		reduce();

		joining();

		toSet();

		summarizingDouble();

		partitioningBy();

		groupingBy();

		mapping();

		streamParalell();

		log.info(convertir.apply("Hello Sr"));
		
		
	}

	private static void streamParalell() {
		log.info("-----------------Stream Paralelo-----------------");
		List<String> nombres = Arrays.asList("Pedro1", "Pedro2", "Pedro3", "Pedro4");
		Instant tiempo1 = Instant.now();
		nombres.stream().forEach(StreamConcepts::convertirAMayusculas);
		Instant tiempo2 = Instant.now();
		log.info("Time without parallell is: {}", Duration.between(tiempo1, tiempo2));
		nombres.parallelStream().forEach(StreamConcepts::convertirAMayusculas);
		Instant tiempo3 = Instant.now();
		log.info("Time with parallel is: {}", Duration.between(tiempo2, tiempo3));
	}

	private static String convertirAMayusculas(String nombre) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			log.error("{}", e.getMessage(), e);
			Thread.currentThread().interrupt();
		}
		return nombre.toUpperCase();
	}

	private static void mapping() {
		log.info("-----------------mapping-----------------");
		List<String> personas = listUsers.stream().collect(Collectors.mapping(UserBean::getNombre, Collectors.toList()));
		personas.stream().forEach(log::info);
	}

	private static void groupingBy() {
		log.info("-----------------GroupingBy-----------------");
		Map<Character, List<UserBean>> grupoAlfabetico = listUsers.stream().collect(Collectors.groupingBy(e -> new Character(e.getNombre().charAt(0))));
		grupoAlfabetico.get('A').stream().forEach(e -> log.info(e.getNombre()));
		grupoAlfabetico.get('J').stream().forEach(e -> log.info(e.getNombre()));
		grupoAlfabetico.get('C').stream().forEach(e -> log.info(e.getNombre()));
	}

	private static void partitioningBy() {
		log.info("-----------------partitioningBy-----------------");
		List<Integer> numeros = Arrays.asList(5, 7, 34, 56, 2, 3, 67, 4, 98);
		Map<Boolean, List<Integer>> partitioningByNumbers = numeros.stream().collect(Collectors.partitioningBy(e -> e > 10));
		partitioningByNumbers.get(true).stream().forEach(e -> log.info("Valor True Partitioning: {}", e));
		partitioningByNumbers.get(false).stream().forEach(e -> log.info("Valor False Partitioning: {}", e));
	}

	private static void map() {
		log.info("-----------------MAP-----------------");
		List<String> lista = listUsers.stream().map(UserBean::getNombre).collect(Collectors.toList());
		lista.forEach(log::info);
	}

	private static void filter() {
		log.info("-----------------FILTER-----------------");
		List<UserBean> listFilter = listUsers.stream().filter(a -> !a.getNombre().equalsIgnoreCase("Albe")).filter(a -> !a.getApellido().equalsIgnoreCase("Algo")).collect(Collectors.toList());
		listFilter.stream().forEach(a -> log.info(a.toString()));
	}

	private static void findFirst() {
		log.info("-----------------FindFirst-----------------");
		UserBean findFirst = listUsers.stream().filter(a -> !a.getNombre().equalsIgnoreCase("Alber")).filter(a -> a.getApellido().contains("Algo")).findFirst().orElse(null);
		String string = findFirst != null ? findFirst.toString() : null;
		log.info("Info is: {} ", string);
	}

	private static void flatMap() {
		log.info("-----------------FlatMap-----------------");
		List<List<String>> nombresVariasListas = new ArrayList<>(Arrays.asList(new ArrayList<>(Arrays.asList("Juan", "Pedro", "Octavio")), new ArrayList<>(Arrays.asList("Carmen", "Alexa"))));
		List<String> unicaLista = nombresVariasListas.stream().flatMap(Collection::stream).collect(Collectors.toList());
		unicaLista.stream().forEach(log::info);
	}

	private static void peek() {
		setUpUser();
		log.info("-----------------Peek-----------------");
		List<UserBean> usersw2 = listUsers.stream().peek(e -> e.setApellido(e.getNombre() + " Apellido")).filter(a -> !a.getNombre().equals("Juan")).collect(Collectors.toList());
		usersw2.stream().forEach(a -> log.info("{}", a));
		log.info("-----------------Count-----------------");
		Long coutUser = listUsers.stream().filter(a -> !a.getNombre().contains("erto")).count();
		log.info("Count is: {}", coutUser);
	}

	private static void skipAndLimit() {
		log.info("-----------------Skip y Limit-----------------");
		String[] abc = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j" };
		List<String> abcFilter = Arrays.stream(abc).skip(2).limit(4).collect(Collectors.toList());
		abcFilter.stream().forEach(log::info);
	}

	private static void sorted() {
		log.info("-----------------Sorted-----------------");
		setUpUser();
		imprimirLista();
		listUsers = listUsers.stream().sorted(Comparator.comparing(UserBean::getNombre)).collect(Collectors.toList());
		log.info("");
		listUsers.stream().forEach(a -> log.info(a.toString()));
	}

	private static void minAndMax() {
		log.info("-----------------Min & Max-----------------");
		UserBean userMin = listUsers.stream().min(Comparator.comparing(UserBean::getEdad)).orElse(null);
		UserBean userMax = listUsers.stream().max(Comparator.comparing(UserBean::getEdad)).orElse(null);
		log.info("Edad Minima de User es: {}", userMin != null ? userMin.getEdad() : null);
		log.info("Edad Maxima de User es: {}", userMax != null ? userMax.getEdad() : null);
	}

	private static void distinct() {
		log.info("-----------------Distinct-----------------");
		String[] abc2 = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "a", "c", "a", "a", "a", "a", "a", };
		List<String> abcFilter1 = Arrays.stream(abc2).distinct().collect(Collectors.toList());
		abcFilter1.stream().forEach(log::info);
	}

	private static void allMatchAnyMatchNoneMatch() {
		log.info("-----------------AllMatch , AnyMatch,noneMatch-----------------");
		List<Integer> listaNumeros = Arrays.asList(1000, 520, 8520, 965320, 210);
		Boolean allMatch = listaNumeros.stream().allMatch(e -> e > 300);
		log.info("allMatch {}", allMatch);
		Boolean allAnyMatch = listaNumeros.stream().anyMatch(e -> e > 100000);
		log.info("allAnyMatch {}", allAnyMatch);
		Boolean noneMatch = listaNumeros.stream().noneMatch(e -> e > 300);
		log.info("noneMatch {}", noneMatch);
	}

	private static void sumAverangeRange() {
		log.info("-----------------Sum Averange Range-----------------");
		Double averange = listUsers.stream().mapToInt(UserBean::getEdad).average().orElse(0);
		log.info("Averange {}", averange);
		Integer sum = listUsers.stream().mapToInt(UserBean::getEdad).sum();
		log.info("Sum {}", sum);
		log.info("range {}", IntStream.range(0, 100).sum());
	}

	private static void reduce() {
		log.info("-----------------Reduce-----------------");
		Integer numero = listUsers.stream().map(UserBean::getEdad).reduce(0, Integer::sum);
		log.info("Reduce is: {}", numero);
	}

	private static void joining() {
		log.info("-----------------Joining-----------------");
		String names = listUsers.stream().map(UserBean::getNombre).collect(Collectors.joining(" - "));
		log.info("Joining is: {}", names);
	}

	private static void toSet() {
		log.info("-----------------toSet-----------------");
		Set<String> setNames = listUsers.stream().map(UserBean::getNombre).collect(Collectors.toSet());
		setNames.stream().forEach(log::info);
	}

	private static void summarizingDouble() {
		log.info("-----------------summarizingDouble-----------------");
		DoubleSummaryStatistics statistics = listUsers.stream().collect(Collectors.summarizingDouble(UserBean::getEdad));
		log.info("Statistics is: {}", statistics);
	}

	private static void setUpUser() {
		listUsers = new ArrayList<>();
		listUsers.add(new UserBean("Alberto"));
		listUsers.add(new UserBean("Juan"));
		listUsers.add(new UserBean("Pedro"));
		listUsers.add(new UserBean("Alberto"));
		listUsers.add(new UserBean("China"));
	}

	private static void imprimirLista() {
		listUsers.stream().forEach(user -> {
			user.setApellido("Algo mas");
			user.setEdad(new Random().nextInt(100));
			log.info(user.toString());
		});
	}
}

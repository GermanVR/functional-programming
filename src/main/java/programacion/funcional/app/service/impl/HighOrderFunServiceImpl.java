package programacion.funcional.app.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import programacion.funcional.app.StreamConcepts;
import programacion.funcional.app.service.IHighOrderFunService;

/**
 * @author German Vazquez Renteria
 *
 */
@Slf4j
public class HighOrderFunServiceImpl {

	public static void main(String[] args) {

		IHighOrderFunService service = new IHighOrderFunService() {

			@Override
			public Double sumar(Double a, Double b) {
				return a + b;
			}
		};
		log.info("The sum of Numbers is: {} ", service.sumar(3.0, 4.0));

		IHighOrderFunService serviceLambda = (a, b) -> a + b;
		log.info("The Sum From Lambda Expresion is: {}", serviceLambda.sumar(5.0, 3.0));

		log.info("Value to Mayus is {}", StreamConcepts.convertir.apply("Exito"));

		List<Integer> numeros = Arrays.asList(6, 5, 7, -7, 8, 2, -5, -41, -7, 81, -32, 6);
		log.info("Value of Numbers is: {}", numeros);

		BiFunction<List<Integer>, Predicate<Integer>, List<Integer>> filtrar;
		filtrar = (lista, predicado) -> lista.stream().filter(predicado::test).collect(Collectors.toList());
		log.info("Value for BiFunction is {} ", filtrar.apply(numeros, e -> e > 0));

		List<String> nombres = Arrays.asList("Juan", "Pedro", "Carmen", "Nayelli");
		HighOrderFunServiceImpl a = new HighOrderFunServiceImpl();
		a.filtrar(nombres, log::info, 10);
	}

	public void filtrar(List<String> lista, Consumer<String> consumer, Integer numMax) {
		lista.stream().filter(logicaPredicado(numMax)).forEach(consumer);
	}

	public Predicate<String> logicaPredicado(Integer numMaximoCaracteres) {
		return e -> e.length() < numMaximoCaracteres;
	}

}

package programacion.funcional.app.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.modelmapper.ModelMapper;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import programacion.funcional.app.beans.AdressComplementBean;
import programacion.funcional.app.beans.UserAddressBean;
import programacion.funcional.app.beans.UserBean;
import programacion.funcional.app.beans.UserBeanExtend;
import programacion.funcional.app.entities.AdressComplementEntity;
import programacion.funcional.app.entities.UserAddressEntity;
import programacion.funcional.app.entities.UserEntity;

@Slf4j
public class UserHelper {

	ModelMapper modelMapper = new ModelMapper();
	@Getter
	private List<UserBean> listUsers = new ArrayList<>();

	public UserBean toUser(UserBean user) {
		UserBean us = null;
		if (user != null) {
			us = new UserBean();
			us.setApellido(user.getApellido());
			us.setEdad(user.getEdad());
			us.setNombre(user.getNombre());
		}
		return us;
	}

	public UserBeanExtend toUserProp(UserBean user, String Descripcion) {
		UserBeanExtend us = modelMapper.map(user, UserBeanExtend.class);
		us.setDescripcion(Descripcion);
		return us;
	}

	public UserEntity toEntity(UserBean usrB) {
		UserEntity a = null;
		if (usrB != null) {
			a = modelMapper.map(usrB, UserEntity.class);
			a.setEdades(usrB.getEdad());

		}
		return a;
	}

	public UserEntity toEntityOld(UserBean usrB) {
		UserEntity a = null;
		if (usrB != null) {
			a = new UserEntity();
			a.setAddress(toEntityOld(usrB.getAddress()));
			a.setApellido(usrB.getApellido());
			a.setEdades(usrB.getEdad());
			a.setNombre(usrB.getNombre());
		}
		return a;
	}

	public UserAddressEntity toEntityOld(UserAddressBean s) {
		UserAddressEntity a = null;
		if (s != null) {
			a = new UserAddressEntity();
			a.setAdressComplement(toEntityOld(s.getAdressComplement()));
			a.setColonia(s.getColonia());
			a.setCountry(s.getCountry());
			a.setCp(s.getCp());
			a.setId(s.getId());
			a.setMunicipio(s.getMunicipio());
			a.setNombre(s.getNombre());
		}
		return a;
	}

	public AdressComplementEntity toEntityOld(AdressComplementBean b) {
		AdressComplementEntity a = new AdressComplementEntity();
		if (b != null) {
			a.setComplement(b.getComplement());
			a.setId(b.getId());
		}
		return a;
	}

//	public static void main(String[] args) {
//		UserHelper e = new UserHelper();
//		e.setUpUserBean();
//		Long timeStart = System.currentTimeMillis();
//		listUsers.stream().filter(a -> a.getEdad() > 80).filter(a -> a.getEdad() < 90).forEach(a -> {
//			a.setApellido("Lacoste");
//			a.setNombre("stream");
//		});
//		Long timeEnd = System.currentTimeMillis();
//		Long timeStart2 = System.currentTimeMillis();
//		for (UserBean a : listUsers) {
//			if (a.getEdad() > 80) {
//				a.setApellido("Hollister");
//				a.setNombre("forEach");
//				if (a.getEdad() < 90) {
////					log.info("Bean forEach es: {}", a);
//					a.setEdad(new Random().nextInt(100));
//				}
//			}
//		}
//		Long timeEnd2 = System.currentTimeMillis();
//
//		log.info("************************Tiempo Total Stream es: {}", (timeEnd - timeStart));
//		log.info("************************Tiempo Total Old ForEach es: {}", (timeEnd2 - timeStart2));
//
////		log.info("---------To Entity--------------");
////		List<UserEntity> listEntity = listUsers.stream().map(e::toEntity).collect(Collectors.toList());
////		listEntity.stream().forEach(a -> {
////			log.info("Entity es: {}", a);
////		});
//	}

	public void setUpUserBean() {
		for (int i = 0; i < 1000000; i++) {
			UserBean a = new UserBean();

			a.setApellido("Alberto");
			a.setEdad(new Random().nextInt(100));
			a.setNombre("Juan " + i + 1);

			UserAddressBean address = new UserAddressBean();
			address.setColonia("Morelos");
			address.setCountry("Mexico");
			address.setCp("55120");
			address.setId(new Random().nextInt(10000));
			address.setMunicipio("Cuahutemoc");
			address.setNombre("Nombre " + i + 1);

			AdressComplementBean ac = new AdressComplementBean();
			ac.setComplement("Complemento " + i + 1);
			ac.setId(new Random().nextInt(10000));
			address.setAdressComplement(ac);
			a.setAddress(address);
			listUsers.add(a);
		}
	}

}

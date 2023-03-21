package cpt202.project.pizzaorederingsys;

import com.mysql.cj.Session;
import cpt202.project.pizzaorederingsys.models.Customer;
import cpt202.project.pizzaorederingsys.models.ShopManager;
import org.hibernate.SessionFactory;;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.security.auth.login.Configuration;

@SpringBootTest
class PizzaOrederingSysApplicationTests {

	@Test
	void testSave() {
		ShopManager s=new ShopManager("shop","abcd");
		s.setDescription("s1");


		Customer t=new Customer("cus","abcde");



	}

}

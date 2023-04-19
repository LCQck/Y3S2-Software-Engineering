package cpt202.project.pizzaorderingsys;

import cpt202.project.pizzaorderingsys.models.Customer;
import cpt202.project.pizzaorderingsys.repositories.CategoryRepo;
import cpt202.project.pizzaorderingsys.repositories.CustomerRepo;
import cpt202.project.pizzaorderingsys.repositories.PizzaRepo;
import cpt202.project.pizzaorderingsys.repositories.ShopmangRepo;
import cpt202.project.pizzaorderingsys.security.SecurityUserDetailsService;
import cpt202.project.pizzaorderingsys.services.CategoryService;
import cpt202.project.pizzaorderingsys.services.PizzaService;
;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class PizzaOrderingSysApplicationTests {

	@Autowired
	private PizzaService pizzaService;
	@Autowired
	private PizzaRepo pizzaRepo;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private SecurityUserDetailsService securityUserDetailsService;
	@Autowired
	private ShopmangRepo shopmangRepo;
	@Autowired
	private CustomerRepo customerRepo;

//	@Test
//	void testShopSave() {
//		GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_SHOP_MANAGER");
//		List<GrantedAuthority> authorities = new ArrayList<>();
//		authorities.add(authority);
//		ShopManager s=new ShopManager(
//				"shop",
//				"abcd",
//				false,
//				"street0",
//				authorities);
//		ShopManager s1=new ShopManager(
//				"shop1",
//				"abcd",
//				false,
//				"street0");
//		s1.setAuthorities(authorities);
//		securityUserDetailsService.createShopManager(s);
//		securityUserDetailsService.createShopManager(s1);
//
//	}
//	@Test
//	void testCustomerSave() {
//		GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_CUSTOMER");
//		List<GrantedAuthority> authorities = new ArrayList<>();
//		authorities.add(authority);
//		Customer c=new Customer(
//				"shop",
//				"abcd",
//				false,
//				"street0",
//				authorities);
//		Customer c1=new Customer(
//				"shop1",
//				"abcd",
//				false,
//				"street0");
//		c1.setAuthorities(authorities);
//		securityUserDetailsService.createCustomer(c);
//		securityUserDetailsService.createCustomer(c1);
//
//	}

    @Test
    void testCustomerReset() {
        securityUserDetailsService.deleteUser("shop1");

        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_CUSTOMER");
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(authority);
        Customer c1=new Customer(
                "shop1",
                "abcdefg",
                false,
                "street0");
        c1.setAuthorities(authorities);
        securityUserDetailsService.createCustomer(c1);
    }


//	@Test
//	void testPizzaSave(){
//
//		Category beef = new Category();
//		beef.setLabel("beef");
//		categoryService.newCategory(beef);
//		Category chicken = new Category();
//		chicken.setLabel("chicken");
//		categoryService.newCategory(chicken);
//		Category chicken1 = new Category();
//		chicken1.setLabel("chicken");
//		categoryService.newCategory(chicken1);
//
//		Pizza pizza1= new Pizza();
//		pizza1.setName("pizza1");
//		pizza1.setCategory(beef);
//		pizzaService.newPizza(pizza1);
//
//		Pizza pizza2= new Pizza();
//		pizza2.setName("pizza2");
//		pizza2.setCategory(beef);
//		pizzaService.newPizza(pizza2);
//
//		Pizza pizza3= new Pizza();
//		pizza3.setName("pizza3");
//		pizza3.setCategory(chicken);
//		pizzaService.newPizza(pizza3);
//
//		Pizza pizza4= new Pizza();
//		pizza4.setName("pizza4");
//		pizza4.setCategory(chicken);
//		pizzaService.newPizza(pizza4);
//
//	}
//
//	@Test
//	void testfindPizza(){
//		List<Pizza> pizzaList1 = pizzaService.getPizzaListByLabel("beef");
//		System.out.println(pizzaList1.toString());
//		List<Pizza> pizzaList2 = pizzaService.getPizzaList();
//		System.out.println(pizzaList2.toString());
//	}
//
//	@Test
//	void testfindCategory(){
//		List<Category> categoryList = categoryService.getCategoryList();
//		categoryList.stream().map(
//				(item) -> {System.out.println(item.printPizzaInCategory());
//					return null;
//				});
//		Category category1 = categoryService.loadCategoryBylabel("chicken");
//		System.out.println(category1.printPizzaInCategory());
//	}

}

package cpt202.project.pizzaorderingsys;

import cpt202.project.pizzaorderingsys.models.*;
import cpt202.project.pizzaorderingsys.repositories.*;
import cpt202.project.pizzaorderingsys.security.SecurityUserDetailsService;
import cpt202.project.pizzaorderingsys.services.*;
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
	private UserService userService;
	@Autowired
	private ShopmangRepo shopmangRepo;
	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private ShopcartDetailService shoppingCartDetails;
	@Autowired
	private ShopCartService shopCartService;
	@Autowired
	private ShopCartRepo shopCartRepo;
	@Autowired
	private  ShopCartDetailsRepo shopCartDetailsRepo;



	/*@Test
	void testShopSave() {
		GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_SHOP_MANAGER");
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(authority);
		ShopManager s=new ShopManager(
				"shop",
				"abcd",
				false,
				"street0",
				authorities);
		ShopManager s1=new ShopManager(
				"shop1",
				"abcd",
				false,
				"street0");
		s1.setAuthorities(authorities);
		securityUserDetailsService.createShopManager(s);
		securityUserDetailsService.createShopManager(s1);

	}*/
	@Test
	void testCustomerSave() {
		GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_CUSTOMER");
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(authority);
		Customer c=new Customer(
				"customer",
				"abcd",
				false,
				"street0",
				authorities);
		Customer c1=new Customer(
				"customer1",
				"abcd",
				false,
				"street0");
		c1.setAuthorities(authorities);
		securityUserDetailsService.createCustomer(c);
		securityUserDetailsService.createCustomer(c1);

	}

	@Test
	void testPizzaSave(){

		Category beef = new Category();
		beef.setLabel("beef");
		categoryService.newCategory(beef);
		Category chicken = new Category();
		chicken.setLabel("chicken");
		categoryService.newCategory(chicken);
//		Category chicken1 = new Category();
//		chicken1.setLabel("chicken");
//		categoryService.newCategory(chicken1);

		Pizza pizza1= new Pizza();
		pizza1.setName("pizza1");
		pizza1.setCategory(beef);
		pizzaService.newPizza(pizza1);

		Pizza pizza2= new Pizza();
		pizza2.setName("pizza2");
		pizza2.setCategory(beef);
		pizzaService.newPizza(pizza2);

		Pizza pizza3= new Pizza();
		pizza3.setName("pizza3");
		pizza3.setCategory(chicken);
		pizzaService.newPizza(pizza3);

		Pizza pizza4= new Pizza();
		pizza4.setName("pizza4");
		pizza4.setCategory(chicken);
		pizzaService.newPizza(pizza4);

	}

	@Test
	void testfindPizza(){
		List<Pizza> pizzaList1 = pizzaService.getPizzaListByLabel("beef");
		System.out.println(pizzaList1.toString());
		List<Pizza> pizzaList2 = pizzaService.getPizzaList();
		System.out.println(pizzaList2.toString());
	}

	@Test
	void testfindCategory(){
		List<Category> categoryList = categoryService.getCategoryList();
		categoryList.stream().map(
				(item) -> {System.out.println(item.printPizzaInCategory());
					return null;
				});
		Category category1 = categoryService.loadCategoryBylabel("chicken");
		System.out.println(category1.printPizzaInCategory());
	}

	/*@Test
	void testShoppingCartDetail() {
//		Category beef = new Category();
//		beef.setLabel("beef");
//		categoryService.newCategory(beef);
//		Category chicken = new Category();
//		chicken.setLabel("chicken");
//		categoryService.newCategory(chicken);
		List<Pizza> pizzaList1 = pizzaService.getPizzaListByLabel("beef");
		List<Pizza> pizzaList2 = pizzaService.getPizzaListByLabel("chicken");
		Pizza pizza1 = pizzaList1.get(0);
		Pizza pizza2 = pizzaList2.get(1);
		Pizza pizza3 = pizzaList2.get(1);


		Size size1 = new Size();
		size1.setInch(7);
		size1.setPrice(10);
		Size size2 = new Size();
		size2.setInch(8);
		size2.setPrice(20);


		Taste taste1 = new Taste();
		taste1.setName("taste1");
		taste1.setPrice(10);


		GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_CUSTOMER");
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(authority);
		Customer c=new Customer(
				"customer",
				"abcd",
				false,
				"street0",
				authorities);
		securityUserDetailsService.createCustomer(c);
		ShoppingCart shoppingCart1 = new ShoppingCart();
		shoppingCart1.setCustomerId(c);
		shopCartService.newShopCart(shoppingCart1);
		ShoppingCartDetails d1= new ShoppingCartDetails();
		shoppingCartDetails.setShopcartDetail(pizza1,size1,taste1,2,shoppingCart1);
		shoppingCartDetails.setShopcartDetail(pizza2,size2,taste1,1,shoppingCart1);
		shoppingCartDetails.setShopcartDetail(pizza3,size2,taste1,3,shoppingCart1);

	}*/

}

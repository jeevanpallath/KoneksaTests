package demopages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class YourCartPage extends GUIBase {	
	private By cartItems = By.xpath("//*[@id='cart_contents_container']//div[@class='inventory_item_name']");
	//Absolute path is used here for demo purpose
	private By checkoutBtn = By.xpath("/html/body/div/div[2]/div[3]/div/div[2]/a[2]");

	private WebDriver driver;
	private List<String> cartCheckOutList = new ArrayList<String>();

	public YourCartPage() {
		super();
	}

	public YourCartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	//Go through each Item in checkout cart and add to List for further verification
	public YourCartPage checkCartList() {
		List<WebElement> cartList = driver.findElements(cartItems);
		
		System.out.println("Count of items in Cart to checkout : "  + cartList.size());
		System.out.println("Following items found in checkout Cart");
		
		for(WebElement cartItem : cartList) {
			String itemNameToConfirm = cartItem.getText() ;
			System.out.println(itemNameToConfirm);
			
			cartCheckOutList.add(itemNameToConfirm);
		}
		getExtentReporter().pass("Your Cart", "Item Count :" + cartList.size());
		cartCheckOutList.sort(null);	// for validation purpose
		return this;
	}

	//Clicking Checkout button
	public YourCartPage checkOut() {
		driver.findElement(checkoutBtn).click();
		getExtentReporter().pass("Your Cart CheckOut Button", "Clicked");
		return this;
	}

	public List<String> getCartCheckOutList() {
		return cartCheckOutList;
	}


}

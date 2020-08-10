package demopages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ProductsPage extends GUIBase {	
	private By counter = By.cssSelector("div.inventory_item");
	private By byLabel = By.cssSelector("div.inventory_item_name");
	private By dropDownSort = By.cssSelector(".product_sort_container");
	
	private By addToCartBtn = By.cssSelector("button.btn_inventory");
	private By cartIconBtn = By.xpath("//*[@id='header_container']//*[@id='shopping_cart_container']");
	
	private String sortByValue = "lohi";	//lowest price to high
	private int minItemCount = 3;

	private WebDriver driver;
	private List<String> cartList = new ArrayList<String>();

	public ProductsPage() {
		super();
	}

	public ProductsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	//Count the number of items and print item names
	public ProductsPage countNumberOfItems() {
		List<WebElement> allElements = driver.findElements(counter);   

		int elementsCount = allElements.size(); 
		System.out.println("Count of Items in Inventory : " + elementsCount);

		for(int i = 0 ; i < elementsCount ; i++ ) {
			WebElement label  = allElements.get(i).findElement(byLabel);
			System.out.println(String.format("Item %d :%s", i+1, label.getText()));
		}
		getExtentReporter().pass("Count Products", "Product count :" +elementsCount);
		return this;
	}

	public ProductsPage sortItems() {
		sortItemsBy(this.sortByValue);		
		return this;
	}

	//Sort the items using drop down list using Criteria passed
	public ProductsPage sortItemsBy(String sortBasedOn) {

		Select sortCombo = new Select(getElementAfterFluentWait(dropDownSort));
		sortCombo.selectByValue(sortBasedOn);

		System.out.println("\nItems sorted by " + sortCombo.getFirstSelectedOption().getText());
		getExtentReporter().pass("Sort Products", sortByValue);
		return this;
	}

	public ProductsPage addToCart() {		
		addToCart(minItemCount ) ;
		return this;
	}

	//Adding items to Cart
	public ProductsPage addToCart(int numberOfItems) {
		if( numberOfItems <= 0) {
			System.out.println("The count of items, add to Cart should be +ve");
			return this;
		}

		List<WebElement> allElements = driver.findElements(counter);        
		int elementsCount = allElements.size(); 

		for(int i = 0 ; i < elementsCount ; i++ ) {
			WebElement label  = allElements.get(i).findElement(byLabel);
			String itemName = label.getText();

			getCartList().add(itemName);        	
			System.out.println(String.format("Adding Item to Cart : %s", itemName ));

			WebElement btn = allElements.get(i).findElement(addToCartBtn);
			btn.click();
			
			--numberOfItems;
			if(0 == numberOfItems) {
				break;	//We added desired items so exit loop
			}
		}
		getExtentReporter().pass("Adding Products to Cart", "Items added");
		getCartList().sort(null);	//This is for validation purpose
		return this;
	} 
	
	//Clicking on the Cart Icon button
	public ProductsPage goToCart() {
		System.out.println("\nMoving items to check out Cart");
		WebElement cartIcon = getElementAfterFluentWait(cartIconBtn);
		
		cartIcon.click();
		getExtentReporter().pass("Cart Icon", "Clicked");
		return this;
	}

	public List<String> getCartList() {
		return cartList;
	}

}

import lombok.Getter;
import lombok.Setter;

public class Computer {
	@Setter
	private String name;
	
	@Getter
	private int price;
	
	@Getter
	@Setter
	private String model;
}

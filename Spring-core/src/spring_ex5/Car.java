package spring_ex5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Car {
	private Tire tire;
	
	public Tire getTire() {
		return tire;
	}
	
	@Autowired
	public void setTire(Tire tire) {
		this.tire = tire;
	}
}

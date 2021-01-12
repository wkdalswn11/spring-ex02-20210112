package spring_ex4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Car {
	private Tire tire;
	
	@Autowired // 버전에따라 생성자가 하나일때는 tire가 이미 bean이므로 autowired를 생략해도 된다  
			   // 현재 우리버전은 생략가능하다
	public Car(Tire tire) {
		this.tire = tire;
	}
	
	public Tire getTire() {
		return tire;
	}
	
	
}

package spring_ex3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component // < 이걸 쓰면 얘는 spring이 관리하는 bean이다 라는 걸 정의 한 것.
public class Car {
	private Tire tire;

	public Tire getTire() {
		return tire;
	}
	
	@Autowired // < 이걸쓰면 자동으로 연결하라는 뜻임.
	public void setTire(Tire tire) {
		this.tire = tire;
	}
	
	
}

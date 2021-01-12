package spring_ex4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		ApplicationContext context 
			= new ClassPathXmlApplicationContext("spring_ex4/context.xml");
		
		Object b1 = context.getBean("car");
		Object b2 = context.getBean("tire");
		
		System.out.println(b1);
		System.out.println(b2);
		
//		Car c1 = (Car) b1;
		
		System.out.println(((Car) b1).getTire());
	}
}

package spring_ex5;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		ApplicationContext context 
			= new ClassPathXmlApplicationContext("spring_ex5/context.xml");
		
		Car c1 = (Car) context.getBean("car");
		System.out.println(c1.getTire());
	}
}

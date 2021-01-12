package spring_ex1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		ApplicationContext context 
			= new ClassPathXmlApplicationContext("spring_ex1/context.xml");
		
		
		Object c = context.getBean("car");
		Object t = context.getBean("tire");
		
		System.out.println(c);
		System.out.println(t);
		
		Car c1 = (Car) c;
		System.out.println(c1.getTire());
	}
}

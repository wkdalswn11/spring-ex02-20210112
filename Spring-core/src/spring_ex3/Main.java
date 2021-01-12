package spring_ex3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		ApplicationContext context 
			= new ClassPathXmlApplicationContext("spring_ex3/context.xml");
											// @Component를 사용했을때 
											// context.xml 에 context:component-scan 을 사용했을때
		Object b1 = context.getBean("car"); // class이름을따라감 맨앞자가 소문자로바뀜
		System.out.println(b1);
		
		Object b2 = context.getBean("tire");
		System.out.println(b2);
		
		Car c1 = (Car) b1;
		System.out.println(c1.getTire());
	}
}

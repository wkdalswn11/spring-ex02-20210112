
public class Main {
	public static void main(String[] args) {
		Object o = new Object();
		
		System.out.println(o);
		System.out.println(o.toString());
		
		Book book = new Book();
		book.setTitle("java");
		book.setPage(300);
		
		System.out.println(book.getTitle());
		System.out.println(book.getPage());
		System.out.println(book.toString());
		
		System.out.println("=====================");
		
		Car c1 = new Car();
//		c1.setName("kia");
		System.out.println(c1.getName());
		
		System.out.println("=====================");
		House h1 = new House();
		h1.setName("home");
//		System.out.println(h1.getName());
		
		System.out.println("=====================");
		Computer c2 = new Computer();
		c2.setName("myComputer");
//		c2.setPrice("300");
		c2.setModel("i7");
		
//		System.out.println(c2.getName());
		System.out.println(c2.getPrice());
		System.out.println(c2.getModel());
		
		System.out.println("=====================");
		
		Desk d1 = new Desk();
		d1.toString();
		System.out.println(d1.toString());
		
		
	}
}

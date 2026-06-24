
public class FactoryMethod {
    public static void main(String[] args) {
        String type = "basic";
        BurgerFactory myFactory = new SinghBurger();
        Burger burger = myFactory.createBurger(type);
        if (burger != null) {
            burger.prepare();
        }
    }
}

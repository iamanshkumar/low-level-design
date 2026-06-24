public class SimpleFactory {
    public static void main(String[] args) {
        String type = "standard";
        BurgerFactory myBurgerFactory = new BurgerFactory();

        Burger burger = myBurgerFactory.createBurger(type);

        if(burger!=null){
            burger.prepare();
        }
    }
}

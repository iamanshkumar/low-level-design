public class StrategyDesignPattern {
    public static void main(String[] args) {
        Robot robot1 = new CompanionRobot(new NormalWalk() , new NormalTalk() , new NoFly());
        robot1.walk();
        robot1.fly();
        robot1.talk();
        robot1.projection();

        Robot robot2 = new WorkerRobot(new NoWalk(), new NoTalk(), new NormalFly());
        robot2.walk();
        robot2.talk();
        robot2.fly();
        robot2.projection();
    }
}
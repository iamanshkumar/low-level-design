abstract class Robot {
    protected Walkable walkingBehaviour;
    protected Talkable talkingBehaviour;
    protected Flyable flyingBehaviour;

    public Robot(Walkable walkingBehaviour , Talkable talkingBehaviour , Flyable flyingBehaviour){
        this.walkingBehaviour = walkingBehaviour;
        this.flyingBehaviour = flyingBehaviour;
        this.talkingBehaviour = talkingBehaviour;
    }

    public void walk(){
        walkingBehaviour.walk();
    }

    public void talk(){
        talkingBehaviour.talk();
    }

    public void fly(){
        flyingBehaviour.fly();
    }

    public abstract void projection();
}
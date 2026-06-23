class CompanionRobot extends Robot {
    public CompanionRobot(Walkable walkingBehaviour , Talkable talkingBehaviour , Flyable flyingBehaviour){
        super(walkingBehaviour , talkingBehaviour , flyingBehaviour);
    }

    public void projection(){
        System.out.println("Displaying friendly companion features");
    }
}
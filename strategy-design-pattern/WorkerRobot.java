class WorkerRobot extends Robot {
    public WorkerRobot(Walkable walkingBehaviour, Talkable talkingBehaviour, Flyable flyingBehaviour){
        super(walkingBehaviour , talkingBehaviour , flyingBehaviour);
    }

    public void projection(){
        System.out.println("Displaying worker efficiency stats");
    }
}
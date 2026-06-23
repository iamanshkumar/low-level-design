# Strategy Design Pattern Implementation

## Overview
This project demonstrates the **Strategy Design Pattern** - a behavioral design pattern that enables selecting an algorithm's behavior at runtime. Instead of implementing a single algorithm directly, the code receives runtime instructions as to which algorithm to employ, allowing flexibility and extensibility.

## Problem Statement
Different types of robots have different behaviors for walking, talking, and flying. Instead of hardcoding these behaviors into each robot class or using complex inheritance hierarchies, the Strategy Pattern allows us to:
- Define families of algorithms (behaviors)
- Encapsulate each one separately
- Make them interchangeable
- Let the client choose which algorithm to use at runtime

## Architecture

### 1. Strategy Interfaces (Contracts)
Define what each behavior family must implement:

- **`Walkable`** - Interface for walking behaviors
  ```java
  interface Walkable {
      void walk();
  }
  ```

- **`Talkable`** - Interface for talking behaviors
  ```java
  interface Talkable {
      void talk();
  }
  ```

- **`Flyable`** - Interface for flying behaviors
  ```java
  interface Flyable {
      void fly();
  }
  ```

### 2. Concrete Strategy Implementations

#### Walking Behaviors
- **`NormalWalk`** - Implements normal walking capability
- **`NoWalk`** - Implements the inability to walk

#### Talking Behaviors
- **`NormalTalk`** - Implements normal talking capability
- **`NoTalk`** - Implements the inability to talk

#### Flying Behaviors
- **`NormalFly`** - Implements normal flying capability
- **`NoFly`** - Implements the inability to fly

### 3. Context Class
**`Robot`** - Abstract base class that uses the strategy interfaces
- Holds references to all behavior strategies
- Delegates behavior execution to the strategies
- Can be configured with different strategies at runtime

```java
abstract class Robot {
    protected Walkable walkingBehaviour;
    protected Talkable talkingBehaviour;
    protected Flyable flyingBehaviour;
    
    public Robot(Walkable walkingBehaviour, Talkable talkingBehaviour, Flyable flyingBehaviour) {
        this.walkingBehaviour = walkingBehaviour;
        this.flyingBehaviour = flyingBehaviour;
        this.talkingBehaviour = talkingBehaviour;
    }
    
    // Delegate to strategies
    public void walk() { walkingBehaviour.walk(); }
    public void talk() { talkingBehaviour.talk(); }
    public void fly() { flyingBehaviour.fly(); }
    
    // Abstract method for subclasses
    public abstract void projection();
}
```

### 4. Concrete Robot Types

- **`CompanionRobot`** - A robot designed as a companion
  - Displays friendly companion features
  - Can be configured with any combination of behaviors

- **`WorkerRobot`** - A robot designed for work efficiency
  - Displays worker efficiency stats
  - Can be configured with any combination of behaviors

### 5. Client
**`StrategyDesignPattern`** - Main class demonstrating the pattern

```java
public class StrategyDesignPattern {
    public static void main(String[] args) {
        // CompanionRobot with: walking, talking, but no flying
        Robot robot1 = new CompanionRobot(
            new NormalWalk(),
            new NormalTalk(),
            new NoFly()
        );
        robot1.walk();        // "Walking normally"
        robot1.fly();         // "Cannot fly"
        robot1.talk();        // "Talking normally"
        robot1.projection();  // "Displaying friendly companion features"

        // WorkerRobot with: no walking, no talking, but flying capability
        Robot robot2 = new WorkerRobot(
            new NoWalk(),
            new NoTalk(),
            new NormalFly()
        );
        robot2.walk();        // "Cannot walk"
        robot2.talk();        // "Cannot talk"
        robot2.fly();         // "Flying normally"
        robot2.projection();  // "Displaying worker efficiency stats"
    }
}
```

## How It Works

1. **Strategy Selection**: At runtime, when creating a robot, we pass the desired strategy implementations as constructor arguments
2. **Delegation**: The robot delegates behavior execution to the injected strategies
3. **Runtime Flexibility**: Behaviors can be changed by passing different strategy implementations
4. **Open/Closed Principle**: New behaviors can be added without modifying existing robot classes

## Example Output

```
Walking normally
Cannot fly
Talking normally
Displaying friendly companion features

Cannot walk
Cannot talk
Flying normally
Displaying worker efficiency stats
```

## Key Benefits

✅ **Flexibility** - Change behavior at runtime without modifying robot classes
✅ **Extensibility** - Add new behaviors by creating new strategy implementations
✅ **Reusability** - Strategies can be reused across different robot types
✅ **Maintainability** - Each behavior is isolated in its own class
✅ **Testability** - Strategies can be tested independently
✅ **Open/Closed Principle** - Open for extension, closed for modification

## Design Pattern Structure

```
┌──────────────────────────────────┐
│         Strategy Interfaces       │
│  Walkable | Talkable | Flyable   │
└──────────────────────────────────┘
              ▲              ▲
              │              │
         Implementations  Implementations
              │              │
    ┌─────────┴────────┐     │
    │                  │     │
┌───────┐  ┌────────┐ ┌──────────┐
│Normal │  │NoWalk  │ │NormalFly │
│Walk   │  │        │ │NoFly     │
└───────┘  └────────┘ └──────────┘

           ┌─────────────┐
           │    Robot    │ (Context)
           │  (Abstract) │
           └─────────────┘
                  ▲
        ┌─────────┴──────────┐
        │                    │
   ┌──────────────┐    ┌────────────────┐
   │CompanionRobot│    │ WorkerRobot    │
   └──────────────┘    └────────────────┘
```

## Real-World Applications

- **Payment Processing**: Different payment strategies (Credit Card, PayPal, Bitcoin, etc.)
- **Sorting Algorithms**: QuickSort, MergeSort, BubbleSort - selected based on data size
- **Compression**: Different compression algorithms (ZIP, RAR, 7z)
- **Transportation**: Different route calculation strategies (Fastest, Shortest, Cheapest)
- **Logging**: Different logging strategies (File, Database, Cloud)

## Running the Code

```bash
javac *.java
java StrategyDesignPattern
```

## File Structure

```
strategy-design-pattern/
├── Robot.java                 # Abstract context class
├── Walkable.java             # Strategy interface
├── Talkable.java             # Strategy interface
├── Flyable.java              # Strategy interface
├── NormalWalk.java           # Concrete strategy
├── NoWalk.java               # Concrete strategy
├── NormalTalk.java           # Concrete strategy
├── NoTalk.java               # Concrete strategy
├── NormalFly.java            # Concrete strategy
├── NoFly.java                # Concrete strategy
├── CompanionRobot.java       # Concrete context
├── WorkerRobot.java          # Concrete context
├── StrategyDesignPattern.java # Client
└── README.md                 # This file
```

## When to Use Strategy Pattern

Use the Strategy Pattern when:
- You have multiple algorithms for a specific task
- You want to avoid conditional statements for algorithm selection
- You need to switch between algorithms at runtime
- You want to make algorithms interchangeable
- You need to decouple algorithm implementation from the context

---

**Design Pattern Type**: Behavioral Pattern
**Complexity**: Medium
**Use Case**: Runtime algorithm selection and behavior flexibility

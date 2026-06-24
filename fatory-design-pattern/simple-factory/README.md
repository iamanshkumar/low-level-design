# Simple Factory Pattern

## Overview
The Simple Factory is the most straightforward approach to object creation. It uses a single factory class with a method that instantiates different products based on input parameters. While simple, it's not technically considered a true "design pattern" in the Gang of Four sense, but rather a programming idiom.

## Problem It Solves
Instead of clients directly instantiating burger classes with `new BasicBurger()`, `new StandardBurger()`, etc., all creation logic is centralized in one factory class. This provides:
- Single point of burger creation
- Easy maintenance of creation logic
- Reduced coupling between client and concrete burger classes

## Architecture

### Class Diagram
```
┌─────────────────────────────────┐
│ SimpleFactory (Client)          │
└─────────────────────────────────┘
              │
              │ creates
              ▼
┌─────────────────────────────────┐
│ BurgerFactory                   │
│ + createBurger(type): Burger    │
└─────────────────────────────────┘
              │
              │ returns
              ▼
         ┌─────────┐
         │ Burger  │ (interface)
         └─────────┘
              ▲
       ┌──────┼──────┐
       │      │      │
┌────────────┐│ ┌────────────┐ ┌──────────────┐
│BasicBurger ││ │StandardBurger│ │ PremiumBurger│
└────────────┘│ └────────────┘ └──────────────┘
```

## Code Example

### Burger Interface
```java
interface Burger {
    void prepare();
}
```

### Concrete Implementations
```java
class BasicBurger implements Burger {
    @Override
    public void prepare() {
        System.out.println("Preparing Basic Burger with bun, patty, and ketchup!");
    }
}

class StandardBurger implements Burger {
    @Override
    public void prepare() {
        System.out.println("Preparing Standard Burger with bun, patty, cheese, and lettuce!");
    }
}

class PremiumBurger implements Burger {
    @Override
    public void prepare() {
        System.out.println("Preparing Premium Burger with gourmet bun, premium patty, cheese, lettuce, and secret sauce!");
    }
}
```

### Factory Class
```java
class BurgerFactory {
    public Burger createBurger(String type) {
        if (type.equalsIgnoreCase("basic")) {
            return new BasicBurger();
        } else if (type.equalsIgnoreCase("standard")) {
            return new StandardBurger();
        } else if (type.equalsIgnoreCase("premium")) {
            return new PremiumBurger();
        } else {
            System.out.println("Invalid burger type!");
            return null;
        }
    }
}
```

### Client Code
```java
public class SimpleFactory {
    public static void main(String[] args) {
        String type = "standard";
        BurgerFactory myBurgerFactory = new BurgerFactory();
        
        Burger burger = myBurgerFactory.createBurger(type);
        
        if (burger != null) {
            burger.prepare();
        }
    }
}
```

### Output
```
Preparing Standard Burger with bun, patty, cheese, and lettuce!
```

## Execution Flow

```
1. Client creates BurgerFactory instance
2. Client calls createBurger("standard")
3. Factory checks type and returns StandardBurger instance
4. Client calls prepare() on the returned burger
5. Burger prepares itself
```

## Advantages

✅ **Simplicity**: Easy to understand and implement
✅ **Centralized Creation**: All object creation in one place
✅ **Reduced Client Coupling**: Clients don't know about concrete burger classes
✅ **Quick Implementation**: Fast to code for simple scenarios
✅ **Easy Maintenance**: Single place to update creation logic

## Disadvantages

❌ **Violates Open/Closed Principle**: Must modify factory for new burger types
❌ **Scalability Issues**: Factory class becomes bloated with many conditionals
❌ **Static/Single Class**: Cannot have multiple factory variants
❌ **Not Extensible**: Hard to add new behavior without changing existing code
❌ **Not a True Pattern**: Limited by its simplicity

## Real-World Issues

### Problem: Adding New Burger Type
If you add a new `SpicyBurger`:
```java
class BurgerFactory {
    public Burger createBurger(String type) {
        if (type.equalsIgnoreCase("basic")) {
            return new BasicBurger();
        } else if (type.equalsIgnoreCase("standard")) {
            return new StandardBurger();
        } else if (type.equalsIgnoreCase("premium")) {
            return new PremiumBurger();
        } else if (type.equalsIgnoreCase("spicy")) {  // ← Must modify factory
            return new SpicyBurger();
        } else {
            return null;
        }
    }
}
```

The factory keeps growing! This violates the Open/Closed Principle.

## Use Cases

Use Simple Factory when:
- **Small Projects**: Few product types expected
- **Rapid Prototyping**: Getting something working quickly
- **Simple Scenarios**: Creating objects of similar complexity
- **Educational Purpose**: Learning basic factory concepts
- **Legacy Systems**: Retrofitting basic factory into existing code

## When NOT to Use

Don't use Simple Factory when:
- You expect to add many new product types
- Different products need different creation logic
- You need multiple factory variants
- Extensibility is important
- You need to follow SOLID principles strictly

## Common Variations

### Using Enums
```java
enum BurgerType {
    BASIC(BasicBurger::new),
    STANDARD(StandardBurger::new),
    PREMIUM(PremiumBurger::new);
    
    private final Supplier<Burger> constructor;
    
    BurgerType(Supplier<Burger> constructor) {
        this.constructor = constructor;
    }
    
    public Burger create() {
        return constructor.get();
    }
}

// Client
Burger burger = BurgerType.STANDARD.create();
```

### Using Reflection
```java
class BurgerFactory {
    public Burger createBurger(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            return (Burger) clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            return null;
        }
    }
}
```

## Comparison with Other Factory Patterns

| Aspect | Simple Factory | Factory Method | Abstract Factory |
|--------|---|---|---|
| **Extensibility** | Poor | Good | Excellent |
| **Number of Classes** | 1 factory | Multiple factories | Multiple factories |
| **Principle Adherence** | Poor | Good | Excellent |
| **Complexity** | Low | Medium | High |
| **Typical Use** | Simple apps | Extensible designs | Complex ecosystems |

## Conclusion

Simple Factory is a practical starting point for encapsulating object creation. However, for systems expecting growth and change, consider Factory Method or Abstract Factory patterns for better maintainability and flexibility.

**Best for**: Simple scenarios where extensibility isn't critical
**Avoid for**: Large systems expecting frequent additions

---

**Pattern Type**: Idiom (not a true GoF pattern)
**Complexity**: Low
**SOLID Principles**: Violates Open/Closed Principle

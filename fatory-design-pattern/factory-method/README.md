# Factory Method Pattern

## Overview
Factory Method is a true creational design pattern (part of Gang of Four patterns) that defines an interface for creating objects, but lets subclasses decide which concrete class to instantiate. Each concrete factory is responsible for creating specific product variants, allowing extensibility without modifying existing code.

## Problem It Solves
Different restaurant chains (SinghBurger vs KingBurger) may want to create their own variations of burgers:
- SinghBurger creates regular burgers
- KingBurger creates wheat-based burgers

Instead of one monolithic factory, Factory Method allows each chain to have its own factory that creates its specific burger variants. This supports the Open/Closed Principle - you can add new restaurant chains without modifying existing code.

## Architecture

### Class Diagram
```
┌──────────────────┐
│ FactoryMethod    │ (Client)
│   (Client)       │
└──────────────────┘
         │
         │ depends on
         ▼
    ┌─────────────────────────┐
    │ BurgerFactory           │ (Interface)
    │ + createBurger(): Burger│
    └─────────────────────────┘
         ▲          ▲
         │          │ implements
         │          │
    ┌────────────┐ ┌────────────┐
    │SinghBurger │ │ KingBurger │ (Concrete Factories)
    └────────────┘ └────────────┘
         │              │
         │ creates      │ creates
         ▼              ▼
    ┌─────────────┐ ┌──────────────┐
    │   Burger    │ │    Burger    │
    │  (Regular)  │ │   (Wheat)    │
    └─────────────┘ └──────────────┘
         ▲              ▲
    ┌────┼────┐    ┌────┼────┐
    │    │    │    │    │    │
   Basic│Standard│ Basic│Standard│ Premium
   Burger│Burger │Wheat │Wheat  │ Wheat
        │Premium│       │       │ Burger
        │Burger │       │       │
```

## Code Example

### Product Interface
```java
interface Burger {
    void prepare();
}
```

### Concrete Products (Regular)
```java
class BasicBurger implements Burger {
    public void prepare() {
        System.out.println("Preparing Basic Burger with bun, patty, and ketchup!");
    }
}

class StandardBurger implements Burger {
    public void prepare() {
        System.out.println("Preparing Standard Burger with bun, patty, cheese, and lettuce!");
    }
}

class PremiumBurger implements Burger {
    public void prepare() {
        System.out.println("Preparing Premium Burger with gourmet bun, premium patty, cheese, lettuce, and secret sauce!");
    }
}
```

### Concrete Products (Wheat)
```java
class BasicWheatBurger implements Burger {
    public void prepare() {
        System.out.println("Preparing Basic Wheat Burger with bun, patty, and ketchup!");
    }
}

class StandardWheatBurger implements Burger {
    public void prepare() {
        System.out.println("Preparing Standard Wheat Burger with bun, patty, cheese, and lettuce!");
    }
}

class PremiumWheatBurger implements Burger {
    public void prepare() {
        System.out.println("Preparing Premium Wheat Burger with gourmet bun, premium patty, cheese, lettuce, and secret sauce!");
    }
}
```

### Factory Interface
```java
interface BurgerFactory {
    Burger createBurger(String type);
}
```

### Concrete Factories
```java
class SinghBurger implements BurgerFactory {
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

class KingBurger implements BurgerFactory {
    public Burger createBurger(String type) {
        if (type.equalsIgnoreCase("basic")) {
            return new BasicWheatBurger();
        } else if (type.equalsIgnoreCase("standard")) {
            return new StandardWheatBurger();
        } else if (type.equalsIgnoreCase("premium")) {
            return new PremiumWheatBurger();
        } else {
            System.out.println("Invalid burger type!");
            return null;
        }
    }
}
```

### Client Code
```java
public class FactoryMethod {
    public static void main(String[] args) {
        String type = "basic";
        BurgerFactory myFactory = new SinghBurger();  // Can also be new KingBurger()
        
        Burger burger = myFactory.createBurger(type);
        
        if (burger != null) {
            burger.prepare();
        }
    }
}
```

### Output
```
Preparing Basic Burger with bun, patty, and ketchup!
```

## Execution Flow

```
1. Client creates a factory instance (SinghBurger or KingBurger)
2. Client calls createBurger(type) on the factory
3. Factory checks type and returns appropriate burger
4. Client calls prepare() on the returned burger
5. Burger prepares itself with its specific ingredients
```

## Key Concepts

### Polymorphism
```java
// Both are valid! Polymorphism in action
BurgerFactory factory1 = new SinghBurger();
BurgerFactory factory2 = new KingBurger();

// Client doesn't care which concrete factory is used
Burger burger1 = factory1.createBurger("basic");
Burger burger2 = factory2.createBurger("basic");
```

### Dependency Inversion
The client depends on abstractions (interfaces), not concrete implementations.

## Advantages

✅ **Open/Closed Principle**: Add new factories without modifying existing code
✅ **Extensibility**: Easy to add new product families
✅ **Loose Coupling**: Client depends on abstract factory and product interfaces
✅ **Encapsulation**: Each factory encapsulates its creation logic
✅ **Runtime Selection**: Choose factory at runtime
✅ **Polymorphism**: Multiple factories can implement the same interface

## Disadvantages

❌ **More Classes**: Requires more classes/interfaces than Simple Factory
❌ **Slight Complexity**: Requires understanding of interfaces and implementations
❌ **Single Family per Factory**: Each factory creates only one family of products
❌ **Overkill for Simple Cases**: May be over-engineered for simple scenarios

## Real-World Scenario: Adding a New Restaurant Chain

### New VeggieBurger Chain
```java
class VeggieBurger implements BurgerFactory {
    public Burger createBurger(String type) {
        if (type.equalsIgnoreCase("basic")) {
            return new BasicVeggieBurger();
        } else if (type.equalsIgnoreCase("standard")) {
            return new StandardVeggieBurger();
        } else if (type.equalsIgnoreCase("premium")) {
            return new PremiumVeggieBurger();
        }
        return null;
    }
}
```

**No modification needed to existing code!** The client can simply use:
```java
BurgerFactory factory = new VeggieBurger();
Burger burger = factory.createBurger("premium");
burger.prepare();
```

This demonstrates true extensibility without the Open/Closed Principle violation that Simple Factory had.

## Design Principles Followed

✅ **Open/Closed Principle**: Open for extension (new factories), closed for modification
✅ **Dependency Inversion Principle**: Depend on abstractions, not concrete classes
✅ **Single Responsibility Principle**: Each factory has one reason to change

## Use Cases

Use Factory Method when:
- You have multiple related product families
- You want to avoid hard-coding product types
- You need runtime factory selection
- You expect new product families to be added frequently
- Each product family needs different creation logic
- You want to follow SOLID principles

## Real-World Examples

1. **Database Drivers**: Different databases (MySQL, PostgreSQL, Oracle) with different connection factories
2. **UI Frameworks**: Different OS (Windows, Mac, Linux) with different UI component factories
3. **Document Formats**: Different document types (PDF, Word, Excel) with different parsers
4. **Payment Gateways**: Different payment processors (Stripe, PayPal, Square) with different transaction handlers
5. **Game Development**: Different enemy types with different creation factories

## Comparison Table

| Aspect | Simple Factory | Factory Method | Abstract Factory |
|--------|---|---|---|
| **Extensibility** | Poor | Good | Excellent |
| **Number of Factories** | 1 | Multiple | Multiple |
| **Product Families** | Single | Single | Multiple |
| **SOLID Compliance** | Poor | Good | Excellent |
| **Complexity** | Low | Medium | High |
| **Best For** | Simple apps | Extensible designs | Complex ecosystems |

## Common Mistakes

### ❌ Wrong: Over-complicating Simple Scenarios
```java
// Overkill for just 2-3 product types
interface BurgerFactory {
    Burger createBurger(String type);
}

class SimpleBurgerFactory implements BurgerFactory {
    // ...
}
```

### ✅ Correct: Use when You Have Multiple Factories
```java
interface BurgerFactory {
    Burger createBurger(String type);
}

class SinghBurger implements BurgerFactory { /* ... */ }
class KingBurger implements BurgerFactory { /* ... */ }
class VeggieBurger implements BurgerFactory { /* ... */ }
```

## Step-by-Step Implementation Guide

1. **Define Product Interface**: Create `Burger` interface
2. **Create Concrete Products**: Implement different burger types
3. **Define Factory Interface**: Create `BurgerFactory` interface with `createBurger()` method
4. **Implement Concrete Factories**: Create `SinghBurger`, `KingBurger`, etc.
5. **Use in Client**: Reference factories through interface

## Conclusion

Factory Method is a powerful pattern that promotes extensibility and follows SOLID principles. It's the bridge between the simplicity of Simple Factory and the complexity of Abstract Factory. Use it when you need multiple related product families with the freedom to add more without modifying existing code.

**Best for**: Medium to large systems with extensible product families
**Evolution**: Simple Factory → Factory Method → Abstract Factory

---

**Pattern Type**: Creational Design Pattern (Gang of Four)
**Complexity**: Medium
**SOLID Principles**: ✅ Follows all SOLID principles

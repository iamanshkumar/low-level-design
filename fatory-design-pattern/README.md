# Factory Design Patterns

This project demonstrates three variations of the **Factory Design Pattern**, a creational design pattern that provides interfaces for creating families of related or dependent objects without specifying their concrete classes.

## Table of Contents
1. [Simple Factory](#simple-factory)
2. [Factory Method](#factory-method)
3. [Abstract Factory](#abstract-factory)

---

## Simple Factory

### Overview
The Simple Factory is the most straightforward factory implementation. It uses a single factory class with a method that returns different object types based on input parameters. While not technically a design pattern (it's more of a programming idiom), it's a practical approach for object creation.

### Architecture

#### Components
- **`Burger`** - Interface defining the product contract
- **`BurgerFactory`** - Centralized factory class with a `createBurger()` method
- **Concrete Products**:
  - `BasicBurger` - Basic burger with bun, patty, and ketchup
  - `StandardBurger` - Standard burger with cheese and lettuce
  - `PremiumBurger` - Premium burger with gourmet ingredients and secret sauce

#### Code Structure
```
SimpleFactory
├── Burger (interface)
├── BurgerFactory (factory class)
├── BasicBurger
├── StandardBurger
└── PremiumBurger
```

#### Implementation
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
            return null;
        }
    }
}
```

#### Client Usage
```java
BurgerFactory myBurgerFactory = new BurgerFactory();
Burger burger = myBurgerFactory.createBurger("standard");
burger.prepare();  // "Preparing Standard Burger with bun, patty, cheese, and lettuce!"
```

### Advantages
✅ Simple and easy to understand
✅ Centralizes object creation logic
✅ Reduces coupling between client and concrete classes

### Disadvantages
❌ Violates Open/Closed Principle - modifying factory requires changes to existing code
❌ Single factory becomes bloated with many conditionals as products increase
❌ Not a true design pattern - limited extensibility

### Use Cases
- Simple applications with few product types
- Quick prototyping
- When you don't need runtime factory selection

---

## Factory Method

### Overview
Factory Method is a true design pattern that defines an interface for creating objects, but lets subclasses decide which class to instantiate. Each factory is responsible for creating specific product variants, allowing for better extensibility.

### Architecture

#### Components
- **`Burger`** - Interface for products
- **`BurgerFactory`** - Interface defining the factory contract with `createBurger()` method
- **Concrete Factories**:
  - `SinghBurger` - Creates regular burgers (BasicBurger, StandardBurger, PremiumBurger)
  - `KingBurger` - Creates wheat burgers (BasicWheatBurger, StandardWheatBurger, PremiumWheatBurger)
- **Concrete Products**: Various burger implementations for each factory

#### Code Structure
```
FactoryMethod
├── Burger (interface)
├── BurgerFactory (interface - factory)
├── SinghBurger (concrete factory)
├── KingBurger (concrete factory)
├── BasicBurger, StandardBurger, PremiumBurger
├── BasicWheatBurger, StandardWheatBurger, PremiumWheatBurger
└── FactoryMethod (client)
```

#### Implementation
```java
interface BurgerFactory {
    Burger createBurger(String type);
}

class SinghBurger implements BurgerFactory {
    public Burger createBurger(String type) {
        if (type.equalsIgnoreCase("basic")) {
            return new BasicBurger();
        } else if (type.equalsIgnoreCase("standard")) {
            return new StandardBurger();
        } else if (type.equalsIgnoreCase("premium")) {
            return new PremiumBurger();
        }
        return null;
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
        }
        return null;
    }
}
```

#### Client Usage
```java
BurgerFactory myFactory = new SinghBurger();  // or new KingBurger()
Burger burger = myFactory.createBurger("basic");
burger.prepare();
```

### Advantages
✅ Follows Open/Closed Principle - extend by creating new factories
✅ Each factory encapsulates its creation logic
✅ Easy to add new factory variants without modifying existing code
✅ Promotes loose coupling between client and products
✅ Supports runtime factory selection

### Disadvantages
❌ More classes to manage compared to Simple Factory
❌ Slight complexity increase for simple scenarios
❌ Requires creating a new factory for each product variant family

### Use Cases
- Multiple related product families
- Need to extend factories without modifying existing code
- Runtime factory selection is important
- Different product variants from different sources

### Key Difference from Simple Factory
- Simple Factory: One factory class creates all products
- Factory Method: Multiple factory classes (one per product family)

---

## Abstract Factory

### Overview
Abstract Factory provides an interface for creating families of related or dependent objects without specifying their concrete classes. It's useful when a system needs to work with multiple families of products that must be used together.

### Architecture

#### Components
- **`Burger`** - Interface for burger products
- **`GarlicBread`** - Interface for garlic bread products
- **`MealFactory`** - Abstract factory interface defining methods to create both burgers and garlic breads
- **Concrete Factories**:
  - `SinghBurger` - Creates regular burgers and garlic breads
  - `KingBurger` - Creates wheat burgers and wheat garlic breads
- **Concrete Products**:
  - Burger products: BasicBurger, StandardBurger, PremiumBurger
  - Garlic Bread products: BasicGarlicBread, CheeseGarlicBread
  - Wheat variants: BasicWheatBurger, StandardWheatBurger, PremiumWheatBurger, BasicWheatGarlicBread, CheeseWheatGarlicBread

#### Code Structure
```
AbstractFactory
├── Burger (interface)
├── GarlicBread (interface)
├── MealFactory (abstract factory interface)
├── SinghBurger (concrete factory)
├── KingBurger (concrete factory)
├── Burger implementations
├── GarlicBread implementations
└── AbstractFactory (client)
```

#### Implementation
```java
interface MealFactory {
    Burger createBurger(String type);
    GarlicBread createGarlicBread(String type);
}

class SinghBurger implements MealFactory {
    public Burger createBurger(String type) {
        // Creates SinghBurger's burger variants
    }
    
    public GarlicBread createGarlicBread(String type) {
        // Creates SinghBurger's garlic bread variants
        if (type.equalsIgnoreCase("basic")) {
            return new BasicGarlicBread();
        } else if (type.equalsIgnoreCase("cheese")) {
            return new CheeseGarlicBread();
        }
        return null;
    }
}

class KingBurger implements MealFactory {
    public Burger createBurger(String type) {
        // Creates KingBurger's burger variants
    }
    
    public GarlicBread createGarlicBread(String type) {
        // Creates KingBurger's garlic bread variants
        if (type.equalsIgnoreCase("basic")) {
            return new BasicWheatGarlicBread();
        } else if (type.equalsIgnoreCase("cheese")) {
            return new CheeseWheatGarlicBread();
        }
        return null;
    }
}
```

#### Client Usage
```java
String burgerType = "basic";
String garlicBreadType = "cheese";

MealFactory mealFactory = new SinghBurger();  // or new KingBurger()

Burger burger = mealFactory.createBurger(burgerType);
GarlicBread garlicBread = mealFactory.createGarlicBread(garlicBreadType);

burger.prepare();         // "Preparing Basic Burger..."
garlicBread.prepare();    // "Preparing Cheese Garlic Bread..."
```

### Advantages
✅ Ensures products from the same family work together consistently
✅ Decouples client from concrete product implementations
✅ Easy to switch entire product families at runtime
✅ Enforces constraints between related products
✅ Follows Open/Closed and Dependency Inversion Principles

### Disadvantages
❌ Significant complexity increase
❌ More classes and interfaces to manage
❌ Difficult to add new product types to existing families
❌ Overkill for simple scenarios with few product families

### Use Cases
- Cross-platform UI frameworks (Windows, Mac, Linux controls)
- Database access layers (different databases, different drivers)
- Theme systems (light theme vs dark theme components)
- Restaurant chains with different food preparation styles
- Document editors supporting different formats (PDF, Word, etc.)

### Key Difference from Factory Method
- Factory Method: Creates ONE family of objects
- Abstract Factory: Creates MULTIPLE families of related objects
- Abstract Factory ensures products from the same family are used together

---

## Comparison Table

| Aspect | Simple Factory | Factory Method | Abstract Factory |
|--------|---|---|---|
| **Complexity** | Low | Medium | High |
| **Extensibility** | Poor | Good | Excellent |
| **Open/Closed Principle** | ❌ No | ✅ Yes | ✅ Yes |
| **Number of Classes** | Few | Medium | Many |
| **Product Families** | Single | Single | Multiple |
| **Related Objects** | No | No | Yes - must be together |
| **Runtime Selection** | Limited | Good | Excellent |
| **Use Case** | Simple apps | Extensible products | Complex product ecosystems |

---

## Visual Representation

### Simple Factory
```
Client → BurgerFactory → Burger (interface)
                            ├── BasicBurger
                            ├── StandardBurger
                            └── PremiumBurger
```

### Factory Method
```
Client → BurgerFactory (interface)
            ├── SinghBurger → {BasicBurger, StandardBurger, PremiumBurger}
            └── KingBurger → {BasicWheatBurger, StandardWheatBurger, PremiumWheatBurger}
```

### Abstract Factory
```
Client → MealFactory (interface)
    ├── SinghBurger → Burger + GarlicBread
    │                 ├── BasicBurger + BasicGarlicBread
    │                 ├── StandardBurger + CheeseGarlicBread
    │                 └── PremiumBurger + ...
    │
    └── KingBurger → Burger + GarlicBread
                     ├── BasicWheatBurger + BasicWheatGarlicBread
                     ├── StandardWheatBurger + CheeseWheatGarlicBread
                     └── PremiumWheatBurger + ...
```

---

## Directory Structure

```
fatory-design-pattern/
├── simple-factory/
│   ├── SimpleFactory.java          # Client
│   ├── Burger.java                 # Product interface
│   ├── BurgerFactory.java          # Simple factory
│   ├── BasicBurger.java            # Concrete product
│   ├── StandardBurger.java         # Concrete product
│   └── PremiumBurger.java          # Concrete product
│
├── factory-method/
│   ├── FactoryMethod.java          # Client
│   ├── Burger.java                 # Product interface
│   ├── BurgerFactory.java          # Factory interface
│   ├── SinghBurger.java            # Concrete factory 1
│   ├── KingBurger.java             # Concrete factory 2
│   ├── BasicBurger.java            # Concrete products
│   ├── StandardBurger.java
│   ├── PremiumBurger.java
│   ├── BasicWheatBurger.java       # Wheat variants
│   ├── StandardWheatBurger.java
│   └── PreimumWheatBurger.java
│
└── abstract-factory/
    ├── AbstractFactory.java        # Client
    ├── Burger.java                 # Product interface 1
    ├── GarlicBread.java            # Product interface 2
    ├── MealFactory.java            # Abstract factory interface
    ├── SinghBurger.java            # Concrete factory 1
    ├── KingBurger.java             # Concrete factory 2
    ├── BasicBurger.java            # Burger products
    ├── StandardBurger.java
    ├── PremiumBurger.java
    ├── BasicGarlicBread.java       # Garlic bread products
    ├── CheeseGarlicBread.java
    ├── BasicWheatBurger.java       # Wheat burger variants
    ├── StandardWheatBurger.java
    ├── PremiumWheatBurger.java
    ├── BasicWheatGarlicBread.java  # Wheat garlic bread variants
    └── CheeseWheatGarlicBread.java
```

---

## Running the Code

### Simple Factory
```bash
cd simple-factory/
javac *.java
java SimpleFactory
```

### Factory Method
```bash
cd factory-method/
javac *.java
java FactoryMethod
```

### Abstract Factory
```bash
cd abstract-factory/
javac *.java
java AbstractFactory
```

---

## When to Use Each Pattern

### Use Simple Factory When:
- Building a simple application with few product types
- Rapid prototyping is priority
- You don't anticipate frequent additions of new product types
- Simplicity is more important than extensibility

### Use Factory Method When:
- You have multiple related product families
- You want to allow subclasses to decide which products to create
- You want to avoid tight coupling between clients and products
- New product families might be added in the future
- Each factory specializes in creating one family of products

### Use Abstract Factory When:
- Your system needs to work with multiple families of related products
- Products from the same family must be used together
- You need to enforce consistency across related objects
- You want to hide the concrete implementations of entire product families
- The system should be independent of how products are created

---

## Key Takeaways

1. **Factory Patterns** are creational patterns that abstract object creation
2. **Simple Factory** is practical but doesn't scale well
3. **Factory Method** enables extensibility through subclassing
4. **Abstract Factory** manages multiple product families and ensures consistency
5. Choose based on complexity and extensibility requirements

---

**Design Pattern Type**: Creational Pattern
**Complexity Level**: Simple Factory (Low), Factory Method (Medium), Abstract Factory (High)
**Use Cases**: Object creation abstraction and encapsulation

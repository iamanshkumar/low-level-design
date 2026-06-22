# Low-Level Design (LLD) Case Studies

Welcome to the Low-Level Design (LLD) repository. This repository is dedicated to learning and practicing clean code, object-oriented analysis and design (OOAD), and SOLID design principles. It provides concrete examples demonstrating the transition from poor design practices to highly modular, extensible, and maintainable systems.

---

## 🏗️ Repository Architecture

This repository is structured into isolated case studies. Each case study typically contains two implementations of the same problem statement:
1. `bad-design`: Code that violates SOLID principles, uses tight coupling, and is difficult to extend.
2. `good-design`: Refactored code that adheres to clean coding guidelines, utilizes design patterns, and decouples concerns.

### Project Structure
```text
lld/
├── README.md                   # This root documentation file
├── .env                        # Local configuration file (ignored from Git)
├── .gitignore                  # Git exclusion rules (ignores *.class and bad-design)
└── documentEditor/             # Case Study: Text Document Editor
    ├── README.md               # Detailed case study documentation
    ├── bad-design/             # Non-SOLID, tightly coupled implementation
    └── good-design/            # SOLID, pattern-oriented refactored implementation
```

---

## 🧩 Core Architectural & Design Principles

Every system in this repository is designed and evaluated based on standard object-oriented design paradigms:

### 1. SOLID Principles
* **S**ingle Responsibility Principle (**SRP**): A class should have only one reason to change.
* **O**pen/Closed Principle (**OCP**): Software entities should be open for extension but closed for modification.
* **L**iskov Substitution Principle (**LSP**): Derived classes must be completely substitutable for their base classes.
* **I**nterface Segregation Principle (**ISP**): Clients should not be forced to depend on interfaces they do not use.
* **D**ependency Inversion Principle (**DIP**): Depend on abstractions, not on concretions.

### 2. Key Design Patterns Demonstrated
* **Composite Pattern**: Treating individual objects and compositions of objects uniformly (used for managing nested document structures).
* **Strategy Pattern**: Decoupling algorithms or persistence mechanisms from their execution contexts.
* **Dependency Injection (DI)**: Passing dependencies (like database or file storage connections) via constructors rather than hardcoding them inside classes.

---

## 📝 Case Study: Document Editor

The first case study is a **Document Editor** system that supports multiple document element types (Text, Images, Tab Spaces, Newlines) and various persistence layers (File Storage, Database Storage).

For a complete breakdown of how this system was refactored from a poorly designed implementation to an enterprise-ready system, refer to:
👉 **[documentEditor README.md](file:///Users/anshkumar/Documents/lld/documentEditor/README.md)**

---

## 🚀 Compiling and Running the Code

To run the examples locally, you must have Java Development Kit (JDK) 8 or higher installed.

### Running the Good Design Example
1. Navigate to the `good-design` directory:
   ```bash
   cd documentEditor/good-design
   ```
2. Compile all Java files:
   ```bash
   javac *.java
   ```
3. Run the client class:
   ```bash
   java DocumentEditorClient
   ```

### Running the Bad Design Example
1. Navigate to the `bad-design` directory:
   ```bash
   cd documentEditor/bad-design
   ```
2. Compile all Java files:
   ```bash
   javac *.java
   ```
3. Run the client class:
   ```bash
   java DocumentEditorClient
   ```

---

## 🛠️ Contribution and Practice Guidelines

When writing new LLD scenarios, follow this step-by-step workflow:
1. **Define the Requirements:** Keep the domain simple (e.g., parking lot, movie booking, document editor).
2. **Implement the "Bad" Design:** Write the code directly without abstraction. Observe how adding a new requirement requires editing multiple files and breaks existing logic.
3. **Refactor using Patterns:** Introduce interfaces for polymorphic behavior, extract responsibilities, and inject dependencies.
4. **Compare:** Document the differences in the local `README.md` to solidify your system design intuition.

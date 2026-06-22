# Low-Level Design (LLD) Case Studies

![Language](https://img.shields.io/badge/Language-Java-orange?style=flat-square&logo=java)
![Paradigm](https://img.shields.io/badge/Paradigm-OOP-blue?style=flat-square)
![Principles](https://img.shields.io/badge/Principles-SOLID-green?style=flat-square)
![Status](https://img.shields.io/badge/Status-Active-brightgreen?style=flat-square)


Welcome to the Low-Level Design (LLD) repository. This repository is dedicated to learning and practicing clean code, object-oriented analysis and design (OOAD), and SOLID design principles. It provides concrete examples of highly modular, extensible, and maintainable systems built from real problem statements.

---

## Core Architectural & Design Principles

### 1. SOLID Principles
* **S**ingle Responsibility Principle (**SRP**): A class should have only one reason to change.
* **O**pen/Closed Principle (**OCP**): Software entities should be open for extension but closed for modification.
* **L**iskov Substitution Principle (**LSP**): Derived classes must be completely substitutable for their base classes.
* **I**nterface Segregation Principle (**ISP**): Clients should not be forced to depend on interfaces they do not use.
* **D**ependency Inversion Principle (**DIP**): Depend on abstractions, not on concretions.

### 2. Key Design Patterns Demonstrated
* **Composite Pattern**: Treating individual objects and compositions of objects uniformly.
* **Strategy Pattern**: Decoupling algorithms or persistence mechanisms from their execution contexts.
* **Dependency Injection (DI)**: Passing dependencies via constructors rather than hardcoding them inside classes.

---

## Case Studies

| # | Problem | Patterns Used | Status |
|---|---------|---------------|--------|
| 1 | [Document Editor](./documentEditor/README.md) | Composite, Strategy, Dependency Injection | ✅ Done |

---

## 🚀 Compiling and Running

Requires JDK 8+. Navigate to the `good-design` directory of any case study, then:

```bash
javac *.java
java <ClientClassName>
```
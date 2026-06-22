# Case Study: Document Editor

A text document editor system built using core OOP principles and SOLID design. The system supports multiple document element types and pluggable persistence layers — designed so that adding a new element type or storage mechanism requires **zero changes** to existing classes.

---

## 📁 Folder Structure

```text
documentEditor/
├── README.md
└── good-design/
    ├── DocumentEditorClient.java   # Entry point / usage demo
    ├── DocumentEditor.java         # Core editor — addText(), addImage()
    ├── Document.java               # Model — holds list of DocumentElements
    ├── DocumentElement.java        # Abstract base for all elements
    ├── TextElement.java            # Concrete element — text
    ├── ImageElement.java           # Concrete element — image
    ├── NewLineElement.java         # Concrete element — newline
    ├── TabSpaceElement.java        # Concrete element — tab space
    ├── Persistence.java            # Abstract persistence layer
    ├── FileStorage.java            # Concrete persistence — file system
    └── DBStorage.java              # Concrete persistence — database
```

---

## 🧩 Design Patterns Used

### 1. Composite Pattern
`Document` holds a `Vector<DocumentElement>`. All element types (`TextElement`, `ImageElement`, `NewLineElement`, `TabSpaceElement`) extend the abstract `DocumentElement` and implement `render()`. The editor and renderer work against the `DocumentElement` abstraction — they never need to know which concrete type they're dealing with.

### 2. Strategy Pattern
`Persistence` is an abstract class with a `save(String data)` method. `FileStorage` and `DBStorage` are concrete strategies. `DocumentEditor` holds a `Persistence` reference injected at construction time — swapping storage requires no change to the editor logic.

### 3. Dependency Injection
Both `Document` and `Persistence` are injected into `DocumentEditor` via constructor. No `new` calls inside the editor. This makes the system trivially testable and loosely coupled.

---

## 🗂️ Class Diagram

```
                    <<abstract>>
                  DocumentElement
                    + render()
                   ↑           ↑
           TextElement     ImageElement
           + render()      + render()
           
           NewLineElement  TabSpaceElement
           + render()      + render()


         <<model>>
         Document  ────────────────(1..*)──► DocumentElement
         - elements: Vector<DocumentElement>
         + addElement(DocumentElement el)
         + getElements()


         <<abstract>>
         Persistence
         + save(String data)
              ↑            ↑
        FileStorage     DBStorage
        + save()        + save()


         DocumentEditor
         - doc: Document
         - storage: Persistence
         + addText(String text)
         + addImage(String path)

         DocumentRenderer
         - doc: Document
         + render()

         Client ──────────────────────────► DocumentEditor
```

---

## ✅ SOLID Principles Applied

| Principle | How |
|-----------|-----|
| **SRP** | `Document` only manages elements. `DocumentEditor` only handles edit operations. `Persistence` only handles saving. |
| **OCP** | New element types (e.g., `VideoElement`) can be added by extending `DocumentElement` — no existing class changes. |
| **LSP** | Any `DocumentElement` subclass can be stored in `Document` and rendered without breaking the system. |
| **ISP** | `DocumentElement` exposes only `render()`. Elements don't implement storage or editor concerns. |
| **DIP** | `DocumentEditor` depends on the `Persistence` abstraction, not on `FileStorage` or `DBStorage` directly. |

---

## 🚀 Running the Code

Requires JDK 8+.

```bash
cd good-design
javac *.java
java DocumentEditorClient
```
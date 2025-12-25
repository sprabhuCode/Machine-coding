# Rate Limiting System – Low Level Design (LLD)

## 📌 Overview

This project implements a **rate limiting library** designed to be plugged into an API service to control incoming requests.

The library:

* Supports **per-user** and **per-endpoint** rate limits
* Supports **multiple rate limiting algorithms**
* Is **extensible, configurable, and cleanly separated** using LLD best practices
* Focuses on **design clarity**, not infrastructure (no Redis, no networking)

This design is suitable for **LLD interviews** and demonstrates strong separation of concerns and use of design patterns.

---

## 🎯 Problem Statement

> Design a rate limiting library that can be used by an API service to restrict incoming requests.
> The library should support per-user and per-endpoint limits, and allow different rate limiting strategies to be configured dynamically.

---

## 🧠 High-Level Design Principles

* **Separation of Concerns**

    * Configuration ≠ Runtime State ≠ Enforcement Logic
* **Open/Closed Principle**

    * New algorithms can be added without changing existing code
* **Strategy Pattern**

    * Each rate limiting algorithm is encapsulated in its own strategy
* **Factory Pattern**

    * Strategy selection is done dynamically based on configuration
* **Clean Boundaries**

    * No HTTP, framework, or infrastructure logic inside the core library

---

## 📂 Project Structure

```
org.example
├── controller
│     └── RateLimitController
│
├── dto
│     ├── RequestContext
│     └── RateLimitResponse
│
├── config
│     └── RateLimitConfig
│
├── enums
│     ├── RateLimitStrategyType
│     ├── Status
│     └── UserType
│
├── service
│     ├── RateLimitService
│     └── ConfigProvider
│
├── strategy
│     ├── RateLimittingStrategy
│     ├── TokenBucketStrategy
│     └── (other strategies can be added)
│
├── state
│     ├── RateLimitState
│     └── TokenBucketState
│
├── store
│     ├── RateLimitStore
│     └── InMemoryRateLimitStateStore
│
└── Main
```

---

## 🧩 Core Concepts

### 1️⃣ RateLimitConfig (Static Rules)

Represents **what is allowed**.

```java
public class RateLimitConfig {
    String endpoint;
    Integer maxRequest;
    Long timeWindow;
    RateLimitStrategyType strategy;
}
```

* Immutable
* Loaded at startup
* Does **not** change at runtime
* Does **not** store counters or timestamps

---

### 2️⃣ RateLimitState (Runtime Usage)

Represents **what has already happened** for a specific key (e.g. `userId:endpoint`).

Example for Token Bucket:

* Available tokens
* Last refill timestamp

State is:

* Mutable
* Algorithm-specific
* Managed by strategies

---

### 3️⃣ RateLimittingStrategy (Behavior)

Defines **how rate limiting is evaluated**.

```java
public interface RateLimittingStrategy {
    Status evaluateRequest(
        RateLimitConfig config,
        RequestContext requestContext
    );
}
```

Each algorithm (Token Bucket, Fixed Window, etc.) has its own implementation.

---

### 4️⃣ RateLimitStore (State Persistence)

Abstracts **where runtime state is stored**.

```java
public interface RateLimitStore {
    RateLimitState getState(String key);
    void updateState(String key, RateLimitState state);
    void remove(String key);
}
```

* Keeps strategies storage-agnostic
* In-memory implementation provided
* Can later be replaced with Redis or DB (out of scope for LLD)

---

### 5️⃣ StrategyFactory (Enum → Strategy Mapping)

Maps configuration (`enum`) to actual strategy objects.

```text
RateLimitStrategyType.TOKEN_BUCKET → TokenBucketStrategy
```

This keeps:

* Configuration lightweight
* Behavior decoupled
* System extensible

---

## 🔄 Request Flow

1. API receives a request
2. `RequestContext` is created (user, endpoint, user type)
3. `ConfigProvider` resolves applicable `RateLimitConfig`
4. `StrategyFactory` selects the correct strategy
5. Strategy:

    * Fetches state from `RateLimitStore`
    * Evaluates the request
    * Updates state if allowed
6. `RateLimitResponse` is returned (`ALLOWED` / `DENIED`)

---

## 📌 Example Scenario

**Rule**
User `U1` can call `/login` only **1 time per second**

**Flow**

* First request → token available → ALLOWED
* Second request within same second → no token → DENIED
* After 1 second → token refilled → ALLOWED

Config remains unchanged, state evolves per request.

---

## 🏗️ Extensibility

### Add a new algorithm

1. Create a new `RateLimittingStrategy` implementation
2. Add corresponding state class (if needed)
3. Register it in `StrategyFactory`

No existing code needs modification.

---

## 🚫 Out of Scope (Intentionally)

* Distributed systems (Redis, Lua, etc.)
* Thread safety
* HTTP status codes
* Cloud / infra concerns

These belong to **HLD**, not LLD.

---

## 🎤 Interview Takeaway

> “The design cleanly separates configuration, runtime state, and enforcement logic.
> It uses Strategy and Factory patterns to support multiple algorithms while keeping the system extensible and easy to reason about.”

---

## ✅ Status

✔ LLD complete
✔ Interview-ready
✔ Clean abstractions
✔ Easily extensible

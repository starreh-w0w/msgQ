# msgQ

[![Java](https://img.shields.io/badge/language-Java-blue.svg)](https://www.java.com/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
![Topics](https://img.shields.io/badge/topics-pub--sub%2C%20in--memory%2C%20message--queue%2C%20thread--safe-blue)
![Status](https://img.shields.io/badge/status-active-brightgreen)

> 📨 **msgQ** is a lightweight **in-memory message streaming service** supporting multiple topics, producers, and consumers. It ensures **message order within partitions**, offers **thread safety**, and enables real-time data streaming for local environments and testing scenarios.

---

## ⚙️ Features

### 🧵 Topics
- ✅ Create new topics with configurable number of partitions
- ✏️ Edit topics
- ❌ Delete topics

### 🏭 Producers
- 📤 Publish messages to topics
- 🔁 Partitioning based on key:
  ```java
  int partitionIndex = key.hashCode() % partitionCount;
  ```

### 🙋 Consumers
- 🔔 Subscribe and unsubscribe to specific topics
- 📥 Consume messages from assigned partitions
- 📌 Tracks offset for each consumer per partition to avoid reprocessing

### 💬 Messages
- 🧾 Messages are composed of a `key` and a `payload`
- 🔄 Messages are **ordered** within the same partition

### 🚀 Concurrency & Thread Safety
- Supports multiple **producers and consumers concurrently**
- Internally synchronized structures for **safe concurrent access**

---

## 🧠 Design Overview

```
                      +--------------------------+
                      |      Topic: payments     |
                      |      (3 partitions)      |
                      +--------------------------+
                        |         |         |
                        ▼         ▼         ▼
                      [P0]      [P1]      [P2]
                        |         |         |
      ┌─────────────────┘         |         └─────────────────┐
      ▼                           ▼                           ▼
[Consumer A]                [Consumer B]                 [Consumer C]
(Offset per P0)            (Offset per P1)              (Offset per P2)
```

- Partitions allow parallelism.
- Each consumer reads from its offset, maintaining idempotent behavior.
- Producers are not aware of consumers, following **decoupled pub-sub** architecture.

---

## 📦 Getting Started

### Requirements
- Java 17+
- Maven (for building)

### Clone the Repository
```bash
git clone https://github.com/starreh-w0w/msgQ.git
cd msgQ
```

### Run
You can use `Demo` class interact with topics, producers, and consumers.

---

## 📌 Use Cases

- 🛠️ Local development mock for Kafka-style systems
- 📊 Event-driven architecture simulations
- 📚 Educational purposes for understanding pub-sub, queues, offsets

---

## 🛠️ Roadmap

- [ ] Persistent storage (e.g., WAL, disk)
- [ ] Message TTL / expiry support
- [ ] Dead-letter and retry queues
- [ ] Web dashboard for message and topic visualization
- [ ] Consumer groups and message rebalancing

---

## 🤝 Contributing

Contributions, issues and feature requests are welcome!  
Feel free to check the [issues page](https://github.com/starreh-w0w/msgQ/issues).

---

## 📄 License

Distributed under the MIT License. See [`LICENSE`](LICENSE) for more information.

---

## 👨‍💻 Author

Made with ❤️ by **Starreh**

---

> Inspired by Apache Kafka, designed for simplicity and local use-cases 🚀

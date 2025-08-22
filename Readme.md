# 🏢 Company Manager API

A RESTful API developed with Spring Boot to manage key business resources: **Departments**, **Employees**, and **Addresses**.

---

## 🎯 Objective

Build a well-structured API that implements best practices, including:

- ✅ Feature-based structure (package-by-feature).
- ✅ Clean layers (**Controller → Service → Repository**).
- ✅ DTOs and mapping.
- ✅ Global error handling with `@ControllerAdvice`.
- ✅ Validations with Bean Validation.
- ✅ JPA relationships (`@OneToMany`, `@ManyToOne`, `@OneToOne`).
- ✅ Pagination and sorting with `Pageable`.
- ✅ Transactions with `@Transactional`.
- ✅ JPQL and native queries.
- ✅ Adherence to SOLID principles.

---

## 💻 Technologies Used

- ☕ Java 17+ and Spring Boot 3+
- 🌐 Spring Web, Spring Data JPA
- 🔄 Maven
- ✅ Bean Validation (Jakarta Validation)
- 🗄️ Database **MySQL**
- 📖 Lombok

---

## 📂 Project Structure

```plaintext
src/main/java/com/mycompany/companymanager
├── department/
│   ├── controller/
│   ├── dto/
│   ├── model/
│   ├── repository/
│   └── service/
├── employee/
├── address/
├── config/
└── exception/
```
---

## 🚀 Setup & Run

```bash
  git clone https://github.com/martinfiguerola/company-management-api.git
  cd company-management-api
  mvn clean install
  mvn spring-boot:run
```
API will be available at: http://localhost:8080

---

## 📄 License
This project is open-source and available under the MIT License.
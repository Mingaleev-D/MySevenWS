Тестовое задание Android

## 📱 Описание
Тестовое задание представляет собой Android-приложение, реализованное с использованием **Jetpack Compose** и архитектуры **MVVM**. В нём реализованы 6 экранов, взаимодействующих с удалённым API, включая регистрацию, авторизацию, отображение ближайших кофеен (в виде списка и на карте), меню выбранной кофейни и оформление заказа.

---

## 🛠️ Стек технологий

- 🧱 **Jetpack Compose** — декларативная UI-система
- 🔄 **MVVM (ViewModel, StateFlow)**
- 🌐 **Retrofit2 + OkHttp** — сетевые запросы
- 🧵 **Kotlin Coroutines** — асинхронность
- 🧭 **Navigation Component** — навигация
- 🗺️ **Yandex Maps SDK** — карта кофеен (экран 4)
- 📦 **Hilt (Dagger)** — внедрение зависимостей

---


---

## 🔐 API

- Регистрация: `POST /auth/register`
- Авторизация: `POST /auth/login`
- Список кофеен: `GET /locations`
- Меню: `GET /location/{id}/menu`

> 📍 Дополнительное: геолокация используется для расчёта расстояния до кофеен.

> 🗺️ Подключена **Яндекс Карта** для отображения кофеен (экран 4)

---

## 🖼️ Экраны

<img width="144" height="308" alt="Image" src="https://github.com/user-attachments/assets/e751b891-ee61-4027-afde-351b2afbade8" />
<img width="179" height="384" alt="Image" src="https://github.com/user-attachments/assets/da6ae84f-8120-4fbe-bb2b-296fd3815fb0" />
<img width="179" height="388" alt="Image" src="https://github.com/user-attachments/assets/66246b9a-2cb2-4765-9e81-508c798c602e" />
<img width="180" height="387" alt="Image" src="https://github.com/user-attachments/assets/13bc6be5-86a2-4031-90a0-4db13bdbf04d" />
<img width="179" height="386" alt="Image" src="https://github.com/user-attachments/assets/a7921d75-d151-4ef4-a44f-9bb2096a9e4f" />
<img width="177" height="385" alt="Image" src="https://github.com/user-attachments/assets/d9c1ca01-654a-4e99-92a1-9013ded2dbde" />
<img width="179" height="383" alt="Image" src="https://github.com/user-attachments/assets/2ecc937e-fa44-4289-a43e-caa4c2b35da0" />

---

## 🚀 Как запустить

1. Клонируйте проект:

```bash
git clone [https://github.com/Mingaleev-D/MySevenWS.git](https://github.com/Mingaleev-D/MySevenWS/tree/master)

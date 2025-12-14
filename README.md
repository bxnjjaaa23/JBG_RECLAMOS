# 📱 JBG Reclamos

Aplicación Android desarrollada para la asignatura **Desarrollo de Aplicaciones Móviles**.  
Permite a los usuarios **crear, visualizar y gestionar reclamos** de forma simple e intuitiva, utilizando tecnologías modernas del ecosistema Android.

---

## 👥 Integrantes del Equipo

- **Genesis Manque**
- **Benjamín Arriaza**
- **José Castillo**

---

## 🧩 Descripción del Proyecto

**JBG Reclamos** es una aplicación móvil Android desarrollada en **Kotlin** usando **Jetpack Compose** como framework de UI.

La app permite:
- Registrar reclamos asociados a un usuario
- Adjuntar evidencia (foto)
- Obtener ubicación GPS
- Visualizar reclamos en una lista
- Eliminar reclamos existentes

Toda la información se almacena localmente usando **Room (SQLite)**, siguiendo el patrón **MVVM**.

---

## 🛠️ Tecnologías Utilizadas

### 📱 Frontend (Android)
- **Kotlin**
- **Jetpack Compose**
- **Material 3**
- **StateFlow**
- **ViewModel**
- **Navigation Compose**

### 💾 Persistencia de Datos
- **Room (SQLite)**
- DAO + Entity + Database
- DbProvider

### 📍 Sensores y Servicios
- Cámara (Camera Intent)
- GPS (FusedLocationProvider)
- Geocodificación con OpenStreetMap (Nominatim)

### 🧪 Testing
- **JUnit**
- **Coroutines Test**
- **Tests unitarios de ViewModel**
- **Tests instrumentados (AndroidTest)**

---

## ✨ Funcionalidades Principales

- ✅ Inicio de sesión (correo)
- ✅ Crear reclamos
- ✅ Validaciones de campos obligatorios
- ✅ Adjuntar foto como evidencia
- ✅ Obtener ubicación actual
- ✅ Mostrar dirección a partir de coordenadas
- ✅ Listar reclamos por usuario
- ✅ Eliminar reclamos
- ✅ Soporte modo oscuro / claro
- ✅ Diseño moderno con Material 3

---

## 🧱 Arquitectura

El proyecto sigue el patrón **MVVM**:

UI (Compose Screens)
│
├── ViewModel
│ ├── StateFlow
│ └── Lógica de negocio
│
├── Room
│ ├── Entity
│ ├── DAO
│ └── Database

Separación clara entre:
- UI
- Lógica
- Persistencia

---

## 🧪 Pruebas

Se implementaron pruebas para:

- ViewModel (`ClaimsViewModelTest`)
- Validación de creación de reclamos
- Manejo de errores
- Inserción de datos válidos

Herramientas usadas:
- JUnit
- kotlinx-coroutines-test

---

## ▶️ Cómo Ejecutar el Proyecto

### Requisitos
- Android Studio (última versión)
- JDK 17
- Dispositivo físico o emulador

### Pasos
1. Clonar el repositorio
   ```bash
   git clone https://github.com/bxnjjaaa23/JBG_RECLAMOS.git

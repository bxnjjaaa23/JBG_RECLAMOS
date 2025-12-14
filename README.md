# 📱 JBG Reclamos

Aplicación Android desarrollada para la asignatura **Desarrollo de Aplicaciones Móviles (DSY1105)**.  
Permite a los usuarios **crear, visualizar y gestionar reclamos**, incorporando evidencia fotográfica y ubicación GPS, integrándose con un **backend propio** mediante microservicios REST.

---

## 👥 Integrantes del Equipo

- **Genesis Manque**
- **Benjamín Arriaza**
- **José Castillo**

---

## 🧩 Descripción del Proyecto

**JBG Reclamos** es una aplicación móvil Android desarrollada en **Kotlin**, utilizando **Jetpack Compose** como framework principal de interfaz gráfica.

La aplicación permite a los usuarios:
- Crear reclamos asociados a su correo
- Adjuntar evidencia mediante fotografías
- Obtener y mostrar la ubicación actual
- Visualizar reclamos en una lista
- Eliminar reclamos existentes

La app utiliza una arquitectura **MVVM**, integrando **persistencia local con Room (SQLite)** y comunicación con un **backend propio** para la gestión externa de datos, asegurando escalabilidad y separación de responsabilidades.

---

## 🛠️ Tecnologías Utilizadas

### 📱 Desarrollo Android
- Kotlin
- Jetpack Compose
- Material Design 3
- Navigation Compose
- ViewModel
- StateFlow
- Retrofit

### 💾 Persistencia de Datos
- Room (SQLite)
    - Entity
    - DAO
    - Database

### 🌐 Backend
- Backend propio con microservicios REST
- API REST
- Persistencia externa de datos
- Operaciones CRUD completas

### 📍 Sensores y Servicios Nativos
- Cámara (Intents nativos)
- GPS (FusedLocationProvider)
- Geocodificación con OpenStreetMap (Nominatim)
- OSMDroid para visualización de mapas

### 🧪 Testing
- JUnit
- kotlinx-coroutines-test
- Tests unitarios
- Tests instrumentados (androidTest)

---

## ✨ Funcionalidades Principales

- ✅ Registro e identificación por correo
- ✅ Creación de reclamos
- ✅ Validación de campos obligatorios
- ✅ Adjuntar imagen como evidencia
- ✅ Obtención de ubicación GPS
- ✅ Conversión de coordenadas a dirección
- ✅ Visualización de reclamos por usuario
- ✅ Eliminación de reclamos
- ✅ Integración con backend mediante microservicios
- ✅ Diseño responsivo
- ✅ Soporte para modo claro y modo oscuro
- ✅ Interfaz moderna con Material Design 3

---

## 👤 Roles de Usuario

La aplicación contempla los siguientes roles funcionales:

- **Usuario registrado**: puede crear, visualizar y eliminar sus propios reclamos.
- **Usuario autenticado propietario**: solo puede gestionar reclamos asociados a su correo.
- **Usuario sin registro**: no tiene acceso a funcionalidades internas.
- **Sistema**: gestiona validaciones, persistencia y control de acceso a datos.

Los permisos se controlan a nivel de lógica del ViewModel y backend.

---

## 🧱 Arquitectura del Proyecto

El proyecto sigue el patrón **MVVM (Model - View - ViewModel)**:

UI (Jetpack Compose)
│
├── ViewModel
│ ├── StateFlow
│ └── Lógica de negocio
│
├── Data
│ ├── Entity
│ ├── DAO
│ ├── Room Database
│ └── Repositorios
│
├── Network
│ └── Retrofit / API Services


Esta arquitectura permite:
- Código limpio y mantenible
- Separación clara de responsabilidades
- Integración sencilla con backend
- Facilidad para pruebas unitarias

---

## 🌐 Backend y Microservicios

La aplicación se integra con un **backend propio desarrollado por el equipo**, el cual expone microservicios REST para la gestión de reclamos.

El backend permite:
- Persistencia externa de datos
- Operaciones CRUD completas
- Comunicación segura con la aplicación móvil

### 🔗 Endpoints Utilizados

#### Autenticación
- `POST /login`
- `POST /register`

#### Reclamos
- `GET /claims`
- `POST /claims`
- `PUT /claims/{id}`
- `DELETE /claims/{id}`

La comunicación entre la app Android y el backend se realiza mediante **Retrofit**.

---

## 🌍 API Externa

- **OpenStreetMap Nominatim**
- URL: https://nominatim.openstreetmap.org
- Uso: Geocodificación inversa para convertir coordenadas GPS en direcciones legibles para el usuario.

---

## 🎞️ Animaciones

La aplicación incorpora **animaciones y transiciones suaves** durante la navegación y cambios de estado, mejorando la experiencia de usuario y la fluidez de la interfaz.

---

## 🧪 Pruebas Implementadas

### Tests Unitarios
- `ClaimsViewModelTest`
    - Validación de campos obligatorios
    - Creación correcta de reclamos
    - Manejo de errores
- Uso de DAOs falsos (Fake DAO) para pruebas desacopladas

### Tests Instrumentados
- Verificación del contexto Android
- Inicialización correcta de componentes

Herramientas utilizadas:
- JUnit
- kotlinx-coroutines-test

---

## 📦 APK Firmado

El APK firmado se encuentra en la siguiente ruta:


La firma se realizó mediante un **keystore (.jks)** generado localmente, el cual **no se incluye en el repositorio por motivos de seguridad**.

---

## ▶️ Cómo Ejecutar el Proyecto

### 🔧 Requisitos
- Android Studio (última versión recomendada)
- JDK 17
- Dispositivo físico o emulador Android (API 26+)

### 🚀 Pasos

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/bxnjjaaa23/JBG_RECLAMOS.git


## 🌐 Backend

El backend de la aplicación se encuentra en el siguiente repositorio:

https://github.com/bxnjjaaa23/ev-backend.git

Este backend expone una API REST propia que permite realizar operaciones CRUD
sobre los reclamos y se comunica con la aplicación Android mediante Retrofit.

## ▶️ Ejecución del Backend

1. Abrir el proyecto en Visual Studio Code
2. Instalar dependencias:
   npm install
3. Ejecutar el servidor:
   npm run dev
   o
   npm start
4. El backend queda disponible en:
   http://localhost:3000

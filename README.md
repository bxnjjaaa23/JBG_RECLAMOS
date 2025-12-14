# 📱 JBG Reclamos

Aplicación Android desarrollada para la asignatura **Desarrollo de Aplicaciones Móviles (DSY1105)**.  
Permite a los usuarios **crear, visualizar y gestionar reclamos**, incorporando evidencia fotográfica y ubicación GPS, utilizando tecnologías modernas del ecosistema Android.

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

Toda la información se almacena localmente usando **Room (SQLite)**, siguiendo una arquitectura **MVVM**, asegurando una correcta separación de responsabilidades.

---

## 🛠️ Tecnologías Utilizadas

### 📱 Desarrollo Android
- **Kotlin**
- **Jetpack Compose**
- **Material Design 3**
- **Navigation Compose**
- **StateFlow**
- **ViewModel**

### 💾 Persistencia de Datos
- **Room (SQLite)**
  - Entity
  - DAO
  - Database
- **DbProvider**

### 📍 Sensores y Servicios
- **Cámara** (Intents nativos)
- **GPS** (FusedLocationProvider)
- **Geocodificación** con OpenStreetMap (Nominatim)
- **OSMDroid** para visualización de mapas

### 🧪 Testing
- **JUnit**
- **kotlinx-coroutines-test**
- **Tests unitarios**
- **Tests instrumentados (androidTest)**

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
- ✅ Diseño responsivo
- ✅ Soporte para modo claro y modo oscuro
- ✅ Interfaz moderna con Material 3

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
│ └── Room Database



Esta arquitectura permite:
- Código limpio y mantenible
- Separación clara de responsabilidades
- Facilidad para pruebas unitarias

---

## 🧪 Pruebas Implementadas

Se desarrollaron pruebas para validar el correcto funcionamiento del sistema:

### Tests Unitarios
- `ClaimsViewModelTest`
  - No permite crear reclamos con campos vacíos
  - Permite crear reclamos con datos válidos
  - Verifica manejo de errores

### Tests Instrumentados
- Verificación del contexto Android
- Inicialización correcta del ViewModel

Herramientas utilizadas:
- **JUnit**
- **kotlinx-coroutines-test**

---

## ▶️ Cómo Ejecutar el Proyecto

### 🔧 Requisitos
- Android Studio (última versión recomendada)
- JDK 17
- Dispositivo físico o emulador Android (API 26+)

### 🚀 Pasos

1. Clonar el repositorio
   ```bash
   git clone https://github.com/bxnjjaaa23/JBG_RECLAMOS.git
   
2. Abrir el proyecto en Android Studio

3. Sincronizar Gradle
   File → Sync Project with Gradle Files
   
4. Ejecutar la aplicación en un dispositivo o emulador
   
##Estado del Proyecto

✅ Funcional
✅ Arquitectura MVVM
✅ Persistencia local con Room
✅ Pruebas implementadas
✅ Diseño moderno y responsivo

##📄 Licencia

Proyecto desarrollado con fines académicos para la asignatura Desarrollo de Aplicaciones Móviles.

📱 JBG Reclamos — Proyecto Android
DuocUc • 2025

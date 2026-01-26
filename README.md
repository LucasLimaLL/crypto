# Crypto - Android App

Este é um aplicativo Android desenvolvido em Java para o projeto Crypto.

## Estrutura do Projeto

- **app/**: Módulo principal do aplicativo Android
  - **src/main/java/com/lucaslima/crypto/**: Código-fonte Java
    - `MainActivity.java`: Activity principal do aplicativo
  - **src/main/res/**: Recursos do aplicativo (layouts, strings, cores, etc.)
  - **src/main/AndroidManifest.xml**: Manifesto do aplicativo Android

## Requisitos

- Java 8 ou superior
- Android SDK (API 24 ou superior)
- Gradle 8.0

## Como Compilar

Para compilar o projeto, use o Gradle wrapper:

```bash
./gradlew build
```

## Estrutura de Pacotes

- `com.lucaslima.crypto`: Pacote principal do aplicativo

## Configuração

- **minSdk**: 24 (Android 7.0)
- **targetSdk**: 34 (Android 14)
- **compileSdk**: 34
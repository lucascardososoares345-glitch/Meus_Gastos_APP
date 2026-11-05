# Sobre o wrapper Gradle

O ZIP original do projeto inclui `gradlew`, `gradlew.bat`, `gradle-wrapper.properties` e um launcher Java local. Se a cópia do GitHub não contiver o `gradle-wrapper.jar`, gere o wrapper em um computador com Gradle 8.13 instalado:

```bash
gradle wrapper --gradle-version 8.13
```

Ou abra o projeto no Android Studio e use a opção de instalação/configuração do Gradle. Não execute `./gradlew` sem o arquivo JAR correspondente.

O wrapper do ZIP foi criado localmente e não é o wrapper oficial. Confira o ambiente antes de usar.

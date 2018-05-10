# WebTech Level Builder

> Ein Level-Editor für das Web-Technologien-Projekt.

[![Build Status][travis-image]][travis-url]

Dieses Repo enthält einen Level Editor, welcher für das Web-Technologien-Projekt
erstellt wurde.

Programmierung erfolgt mit [Java](https://www.java.com/de/) und [Java Swing](https://docs.oracle.com/javase/7/docs/api/javax/swing/package-summary.html).

## Bauen

Linux, OS X & Windows:

[Gradle](https://gradle.org/) muss **NICHT** installiert sein.

```sh
./gradlew shadowJar
```

Die erstellte JAR-Datei befindet sich im `build/libs` Ordner.

## Benutzung

Um die JAR-Datei auszuführen, muss folgender Befehl ausgeführt werden (Die `x` mit der gebauten Version ersetzen).

```sh
java -jar build/libs/webtech-levelbuilder-x.x.x-all.jar
```

## Entwicklung

Für die Entwicklung kann das Projekt einfach in IntelliJ IDEA geöffnet werden.

Bitte nicht vergessen, **Tests** zu schreiben!

## Meta

CodeHat – [@CodeHat](https://twitter.com/CodeHat)

Verfügbar unter GPL-3.0 Lizenz. Siehe `LICENSE` für mehr Informationen.

[https://github.com/Pixelhash](https://github.com/Pixelhash)

## Mitarbeiten

1. Repo forken (<https://github.com/Pixelhash/webtech-level-builder/fork>)
2. Feature Branch erstellen (`git checkout -b feature/fooBar`)
3. Änderungen committen (`git commit -am 'Add some fooBar'`)
4. Branch pushen (`git push origin feature/fooBar`)
5. Eine neue Pull Request erstellen

<!-- Markdown link & img dfn's -->
[travis-image]: https://travis-ci.com/Pixelhash/webtech-level-builder.svg?token=cdDGJnyrMjz1Y73p6Az7&branch=master
[travis-url]: https://travis-ci.com/Pixelhash/webtech-level-builder
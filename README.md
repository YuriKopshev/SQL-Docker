
# README.md

# Название проекта

Скоро deadline

## Начало работы

Дана SUT app-deadline.jar, работающая со схемой  schema.sql

### Prerequisites

Для проведения тестирования, необходимо установить и развернуть окружение:


```
1.Docker Toolbox
2.Oracle VirtualBox
3.IntelliJ IDEA
4.Подготовить файл docker-compose.yml
```

### Установка и запуск

Пошаговый процесс установки и запуска

```
1.Запускем Docker QuickStart(который автоматически запускает и подключает VirtualBox)
2.Открываем IntelliJ IDEA
3.В теминале создаём контейнер (docker-compose -up)
4.Запускаем SUT (java -jar app-deadline.jar -P:jdbc.url=jdbc:mysql://192.168.99.100:3306/app) 
5.Запускаем тестовый файл LoginPageTest (gradle test)
```

## Примечания
Дана инструкция по работе на системе Windows 7(вместе с Docker Toolbox)

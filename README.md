# Back-end часть интернет-магазина
Реализация back-end части интернет-сервиса для размещения объявлений о товарах. Проект реализован на основе front-end части - https://github.com/BizinMitya/front-react-avito

## Основные возможности
Авторизация и аутентификация пользователей.
Распределение ролей между пользователями: пользователь и администратор.
CRUD-операции для объявлений и комментариев: администратор может удалять или редактировать все объявления и комментарии, а пользователи — только свои.
Возможность для пользователей оставлять комментарии под каждым объявлением.
Показ и сохранение картинок объявлений, а также аватарок пользователей.

## Технологии используемые в проекте
- Java 11
- Maven
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security
- Swagger
- PostgreSQL
- Liquibase
## Структура проекта
```
/src/main/
    java/ru/skypro/homework/
        config/                # Конфигурация Spring security
        controller/            # Контроллеры
        dto/                   # Data transfer objects
        generated dto/         # Сгенерированные Data transfer objects
        entity/                # Сущности
        exception/             # Исключеия обрабатываемые в приложении
        filter/                # фильтор применяемый для определения роли пользователя
        mapper/                # Мапперы
        repository/            # Репозитории
        service/               # Интерфейсы сервисов
            impl/              # Их реализация
    resources/                 # Настройки приложения
        liquibase              # Инициализация БД средствами Liquibase
```
## Над проектом работали
- [Денис Хромченко](https://github.com/DenisKhrV)
- [Семён Акимов](https://github.com/BodryjHryap)
- [Никита Кулявец](https://github.com/NikitaKuliav)

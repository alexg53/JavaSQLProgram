.zipp -> .zip -> распаковать, поместить вместе с JavaSQLProgram

"people.db -> table: users";

"documents.db -> table: docs";

БД настроены (добавлены таблицы), для настроки использовались people-db.txt и documents-db.txt

[sqlite.exe] для выхода .quit

java -classpath "sqlite-jdbc-.. ..jar;." JavaSQLProgram
нужно скачать библиотеку sqlite-jdbc-.. ..jar

// set classpath - покажет установленный путь

 //javac JavaSQLProgram.java

java JavaSQLProgram

            C:...>java JavaSQLProgram
            Opened database successfully
            Enter FirstName: Иван
            Enter LastName: Иванов
            Enter patronymic: Иванович
            Enter your birthday: 22.04.1962

Если указать мужской пол, то запросит военный билет.
В конце выполнения программа покажет 2 таблицы: users и docs

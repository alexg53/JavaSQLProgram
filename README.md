не работает (по крайней мере у меня) нужно ставить флаг "-encoding utf-8",
# JavaSQLProgram

.zipp -> .zip -> распаковать, поместить вместе с JavaSQLProgram

"people.db" statement: "users"
"documents.db" statement: "docs"

БД настроены (добавлены таблицы), для настроки использовались people-db.txt и documents-db.txt
[sqlite.exe] для выхода .quit

java -classpath "sqlite-jdbc-.. ..jar;." JavaSQLProgram

// set classpath - покажет установленный путь

 //javac JavaSQLProgram.java
javac -encoding utf-8 JavaSQLProgram.java  // возникали проблемы с кодировкой

java JavaSQLProgram

            C:...>java JavaSQLProgram
            Opened database successfully
            Enter FirstName: Иван
            Enter LastName: Иванов
            Enter patronymic: Иванович
            Enter your birthday: 22.04.1962


#!/bin/sh
java -classpath target/classes:$HOME/.m2/repository/mysql/mysql-connector-java/5.1.21/mysql-connector-java-5.1.21.jar me.kukkii.janken.net.GameManager

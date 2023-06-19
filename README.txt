EP1 - COO - Onitama

Danilo Araújo de Oliveira Romeira, 13725823
Felipe Medeiros de Holanda, 11792478

Foram utlizados os testes em JUnit 5, para compilar e executar no prompt do Windows seguem os passos:

1. javac -d out/ -sourcepath src/ src/ep/main/game/*.java

2. javac -d out/ -sourcepath test/ test/ep/main/game/*.java

2. 
javac -cp "../lib/junit-platform-console-standalone-1.9.3.jar" out -sourcepath src/ src/ep/test/elem/*.java

## Como executar
java -jar lib/junit-platform-console-standalone-1.9.3.jar --class-path out/ --scan-class-path

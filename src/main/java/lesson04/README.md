```
$ cd src/main/java/
$ mkdir lesson04Target
$ javac -d lesson04Target lesson04/*.java
$ cd lesson04Target/
$ jar cfm FileManager.jar ../lesson04/Manifest.txt lesson04/*.class
$ java -jar FileManager.jar
```

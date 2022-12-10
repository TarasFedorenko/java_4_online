echo 'run java_level_2 package with new lib'
cd ./include_library
javac -sourcepath ./java -d build/classes -cp ./library/commons-lang3-3.12.0.jar  src/ua/com/alevel/TooHard.java src/ua/com/alevel/test/Hard.java
cd library
jar fx commons-lang3-3.12.0.jar
cp -rf org ../build/classes
cd ../
jar cvfm build/jar/toohard.jar resourses/MANIFEST.MF -C build/classes .
jar tf build/jar/toohard.jar
java -jar build/jar/toohard.jar

find . -name "*.class" -type f -print0 | xargs -0 /bin/rm -f
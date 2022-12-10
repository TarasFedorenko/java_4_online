#!/bin/sh

echo 'run level 1'

echo 'run simple'
cd ./java/simple
javac Count.java
java Count

cd ../

echo 'run package'
cd ./package
javac ua/com/alevel/Count.java
java ua.com.alevel.Count

cd ../

echo 'run separate packages'
cd ./separate_packages
javac ua/com/alevel/Count.java
java ua.com.alevel.Count

cd ../

echo 'run minimal proj'
cd ./minimal_proj
javac -sourcepath ./src -d build/classes ./src/ua/com/alevel/Count.java
java -cp build/classes ua.com.alevel.Count

cd ../

echo 'run med proj and create simple jar'
cd ./med_proj
javac -sourcepath ./src -d build/classes ./src/ua/com/alevel/Count.java
jar cvfm build/jar/count.jar resourses/MANIFEST.MF -C build/classes .
java -jar build/jar/count.jar

cd ../../

echo 'run level 2 with external library'

cd ./java_level_2

. ./run_level_2.sh


cd ../../

find . -name "*.class" -type f -print0 | xargs -0 /bin/rm -f
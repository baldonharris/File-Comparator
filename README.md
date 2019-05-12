# File-Comparator

A Java based application that can compare two CSV files per column

### Structure
```
dist/   - Contains the runnable jar of the project
src/    - Contains the source code of the project
target/ - Contains the built source code of the project
```

### Build (Generate runnable jar file)
Execute commands in the `root` folder of the project:
```bash
$ javac -cp src src/com/company/*.java -d target/production/File-Comparator/
$ jar cvfm dist/File-Comparator.jar target/production/File-Comparator/META-INF/MANIFEST.MF -C target/production/File-Comparator/ .
```

### Run
Run jar file and see `logs` on run time:
```bash
$ cd dist
$ java -jar File-Comparator.jar
```
Alternatively, you could just run the jar file:
1. Go to `dist` folder
2. Double-click File-Comparator.jar

### Usage
Upon running of jar file there will be sequence of prompts that should appear:
- First prompt: location of the first file
```
Example: C:\first_file.csv
```
- Second prompt: location of the second file
```
Example: C:\second_file.csv
```
- Third prompt: columns to compare in comma-separated format
```
Example: id,first_name,ip_address
```

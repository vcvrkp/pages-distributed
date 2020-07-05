## Instruction to start the Distributed Application from scratch
- Download the project zip file and extract it inside workspace folder
- Create a repository in git with the name pages. Keep everything default, while creating the repository, don't change anything other than default.
- Copy the git remote add origin <path> command and execute it in the directory
- Create a build.gradle file with following content

```groovy
plugins {
	id 'org.springframework.boot' version '2.3.1.RELEASE'
	id 'io.spring.dependency-management' version '1.0.9.RELEASE'
	id 'java'
}

group = 'com.example'
sourceCompatibility = '11'

repositories {
	mavenCentral()
}
```
- Create the gradle ecosystem by using the following commands
```sh
    gradle wrapper --gradle-version 6.4.1 --distribution-type all
```
- Create a .gitignore file with following content
```text
HELP.md
.gradle
build/
!gradle/wrapper/gradle-wrapper.jar
!**/src/main/**
!**/src/test/**

### STS ###
.apt_generated
.classpath
.factorypath
.project
.settings
.springBeans
.sts4-cache

### IntelliJ IDEA ###
.idea
*.iws
*.iml
*.ipr
out/

### NetBeans ###
/nbproject/private/
/nbbuild/
/dist/
/nbdist/
/.nb-gradle/

### VS Code ###
.vscode/
```
- Open the project in Intellij Idea, select the import gradle project option in low right corner and set project SDK to JDK 11
- Create four modules under the root project namely
  * components/business
  * components/category
  * application/business-server
  * application/category-server
  
- This will make it a multi-project gradle. To do this *Right Click* on the root project ---> New ---> Module ---> Select Module as Gradle and Library as Java ---> Enter the module name as mention above
  It would also create the default build.gradle file in each module, which we would change later.
- Create a *settings.gradle* file in the project directory.Add below content to the settings.gradle and remove everything
```groovy
rootProject.name = 'pages-distributed'
include 'components:business'
include 'components:category'
include 'application:business-server'
include 'application:category-server'
```
- Create src/main/java and src/main/resources folder under all the modules. Ensure that both these folders are marked as *Sources Root* and *Resources Root* respectively in all the four modules.
- Create a server.gradle file under application
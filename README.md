# Task Manager

## Spring Boot Application
```
This Spring Boot application demonstrates taskmanager with spring security and JWT authentication using Java 21 and Maven build tool. 
demenostrating the server-side layer through API. Authentication principles and restictions on user roles also defined. 

included are screen shots of the working endpoint and the functionality.
```
## Screen Shots path
```
task-manager/usage_screenshots
```
## Prerequisites
```
- Java 21
- Maven
```
## Installation

1. Clone this repository:

   ```bash
   git clone <repository-url>
   ```

2. Navigate to the project directory:

   ```bash
   cd <project-directory>
   ```

3. Build run docker-compose.yaml to setup tables make sure docker desktop is running:

   ```bash
   docker-compose up -d
   ```

4. Build the project:

   ```bash
   mvn clean install
   ```
5. Run the project:
   ```bash
   java -jar -Dapple.awt.UIElement="true" target/task-manager-1.0-SNAPSHOT.jar -h

## Usage

```
Please make sure that JAVA_HOME and MAVEN_HOME path are set in environment variables as follows:

JAVA_HOME: C:\Program Files\Java\jdk1.8.0_181 (Path till JDK folder only not bin folder) MAVEN_HOME: C:\Users\Amit Joshi\Downloads\apache-maven-3.6.0
```

## Configuration

### Running Docker Desktop
```
To run docker, follow setup:
https://docs.docker.com/desktop/setup/install/windows-install/
```

### install postgres
```
To run postgres installer exe, follow setup:
https://www.postgresql.org/download/
```

You can configure settings in the `application.properties` file.

## Contributors
```
Taahir Garnie.

```

![Admin user login 2025-02-06 175029.png](..%2FusageScreenShots%2FAdmin%20user%20login%202025-02-06%20175029.png)
![complete task 2025-02-06 175833.png](..%2FusageScreenShots%2Fcomplete%20task%202025-02-06%20175833.png)
![create normal user 2025-02-06 174802.png](..%2FusageScreenShots%2Fcreate%20normal%20user%202025-02-06%20174802.png)
![create task admin token 2025-02-06 175432.png](..%2FusageScreenShots%2Fcreate%20task%20admin%20token%202025-02-06%20175432.png)
![create user 2025-02-06 174548.png](..%2FusageScreenShots%2Fcreate%20user%202025-02-06%20174548.png)
![generate Admin token 2025-02-06 174926.png](..%2FusageScreenShots%2Fgenerate%20Admin%20token%202025-02-06%20174926.png)
![update task admin 2025-02-06 175649.png](..%2FusageScreenShots%2Fupdate%20task%20admin%202025-02-06%20175649.png)

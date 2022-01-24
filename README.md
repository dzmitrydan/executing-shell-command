# executing-shell-command
### Description
**Executing Shell Commands with Java**

- To run you need a running Docker with a running shell_command container
- Has Scenario which search log in the Linux files logs

Build tool: **Maven**

#### OS:
##### 1. For Windows: `RunBatFile` - executing `.bat`

##### 2. For Linux: `RunLinuxCommand` - run command

##### 3. For Linux: `RunShFile` - executing `.sh`
* set up `sudo` without a password for the user "ddvubuntu":
   
   `sudo visudo`
   
   `ddvubuntu ALL=(ALL) NOPASSWD: ALL`

* When working with Docker Container, it takes time to execute, so I deleted `sudo shell_command run 6c3c2a225947`
* File `commands-test.txt` contains useful commands


#### SSH connection and Port forwarding
*For a simpler example of a connection, see commit `added ssh connection`* [`2afe4ea`](https://github.com/dzmitrydan/executing-shell-command/commit/b8340b31d45dc0970e51bab37d271e908dc3b5da)


**Used libraries:**

1. Logging **Log4j**
  * Apache Log4j SLF4J 1.8+ Binding
  * Apache Log4j Core


2. Reporting **ReportPortal**
  * Agent Java Cucumber6
  * Logger Java Log4j


3. Reporting
  * Agent Java Cucumber6


4. **Cucumber** + **JUnit 5**
  * Cucumber JVM: Java 8
  * Cucumber JVM: JUnit
  * Cucumber JVM: JUnit Platform Engine
  * JUnit Vintage Engine
  * JUnit Jupiter Engine


5. **Property injection**: Google Guice + Cucumber
  * Google Guice Core Library
  * Cucumber JVM: Guice
  * Governator Core


6. SSH connection
  * JSch


6. other
  * Project Lombok


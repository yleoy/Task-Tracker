#  Task Tracker CLI
A simple application by command line developed in java, for tasks management.   

## 🚀 Features
-  **Add tasks** 
-  **Update tasks** 
-  **Delete tasks** 
-  **Update task status** 
-  **List tasks**:

##  Installation
### 1. Clone the repository:
```bash
git clone https://github.com/SEU_USUARIO/SEU_REPOSITORIO.git
cd task_tracker/src
```
### 2. Compile the source code:
```bash
javac *.java
```
### 3. Run the application:
```bash
java TaskApp <command> [arguments]
```
### 4. How to use
#### Add a new task:
```bash
java TaskApp add "Comprar café"
# Saída: Tarefa adicionada com sucesso! ID: 1
```
#### List all tasks:
```bash
java TaskApp list
```
#### List tasks filtered by status:
```bash
java TaskApp list todo
java TaskApp list in-progress
java TaskApp list done
```
#### Update a task description:
```bash
java TaskApp update 1 "Comprar café em grãos e leite"
```
#### Mark a task as in-progress or done:
```bash
java TaskApp mark-in-progress 1
java TaskApp mark-done 1
```
#### Delete a task:
```bash
java TaskApp delete 1
```
---

Sample solution for the [Task Tracker](https://roadmap.sh/projects/task-tracker) challenge from roadmap.sh.

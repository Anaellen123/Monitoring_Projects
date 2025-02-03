package com.meuprojeto;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProjectManagementSystem {
    private Connection connection;
    private Statement statement;

    public ProjectManagementSystem(String dbName) {
        try {
            // Conectar ao banco de dados SQLite
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbName);
            statement = connection.createStatement();
            createTables();
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    private void createTables() {
        try {
            String createProjectsTable = "CREATE TABLE IF NOT EXISTS projects (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT NOT NULL, " +
                    "description TEXT, " +
                    "start_date TEXT, " +
                    "end_date TEXT, " +
                    "team TEXT, " +
                    "status TEXT)";
            statement.execute(createProjectsTable);

            String createTasksTable = "CREATE TABLE IF NOT EXISTS tasks (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "project_id INTEGER, " +
                    "title TEXT NOT NULL, " +
                    "description TEXT, " +
                    "responsible TEXT, " +
                    "due_date TEXT, " +
                    "status TEXT, " +
                    "FOREIGN KEY (project_id) REFERENCES projects (id))";
            statement.execute(createTasksTable);
        } catch (SQLException e) {
            System.out.println("Erro ao criar tabelas: " + e.getMessage());
        }
    }

    public void addProject(String name, String description, String startDate, String endDate, String team, String status) {
        try {
            String insertProject = "INSERT INTO projects (name, description, start_date, end_date, team, status) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertProject);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setString(3, startDate);
            preparedStatement.setString(4, endDate);
            preparedStatement.setString(5, team);
            preparedStatement.setString(6, status);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar o projeto: " + e.getMessage());
        }
    }

    public void addTaskToProject(String projectName, String title, String description, String responsible, int daysToComplete, String status) {
        try {
            String selectProject = "SELECT id FROM projects WHERE name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectProject);
            preparedStatement.setString(1, projectName);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int projectId = resultSet.getInt(1);
                String dueDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis() + daysToComplete * 86400000L));
                String insertTask = "INSERT INTO tasks (project_id, title, description, responsible, due_date, status) VALUES (?, ?, ?, ?, ?, ?)";
                preparedStatement = connection.prepareStatement(insertTask);
                preparedStatement.setInt(1, projectId);
                preparedStatement.setString(2, title);
                preparedStatement.setString(3, description);
                preparedStatement.setString(4, responsible);
                preparedStatement.setString(5, dueDate);
                preparedStatement.setString(6, status);
                preparedStatement.executeUpdate();
            } else {
                System.out.println("Projeto '" + projectName + "' não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar a tarefa: " + e.getMessage());
        }
    }

    public void viewProjects() {
        try {
            String selectProjects = "SELECT * FROM projects";
            ResultSet projects = statement.executeQuery(selectProjects);
            while (projects.next()) {
                System.out.println("Projeto: " + projects.getString("name"));
                System.out.println("  Descrição: " + projects.getString("description"));
                System.out.println("  Data de Início: " + projects.getString("start_date"));
                System.out.println("  Data de Fim: " + projects.getString("end_date"));
                System.out.println("  Equipe: " + projects.getString("team"));
                System.out.println("  Status: " + projects.getString("status"));
                System.out.println("  Tarefas:");

                String selectTasks = "SELECT * FROM tasks WHERE project_id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(selectTasks);
                preparedStatement.setInt(1, projects.getInt("id"));
                ResultSet tasks = preparedStatement.executeQuery();

                while (tasks.next()) {
                    System.out.println("    Título: " + tasks.getString("title"));
                    System.out.println("      Descrição: " + tasks.getString("description"));
                    System.out.println("      Responsável: " + tasks.getString("responsible"));
                    System.out.println("      Prazo: " + tasks.getString("due_date"));
                    System.out.println("      Status: " + tasks.getString("status"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao visualizar os projetos: " + e.getMessage());
        }
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao fechar a conexão: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        ProjectManagementSystem system = new ProjectManagementSystem("projects.db");

        system.addProject("Project Alpha", "Descrição do Projeto Alpha", "2025-01-01", "2025-06-01", "ADMFIN", "Planejado");
        system.addProject("Project Beta", "Descrição do Projeto Beta", "2025-02-01", "2025-07-01", "ADMPLN", "Em execução");

        system.addTaskToProject("Project Alpha", "Tarefa 1", "Descrição da Tarefa 1", "PLO", 30, "Planejado");
        system.addTaskToProject("Project Beta", "Tarefa 2", "Descrição da Tarefa 2", "CTB", 20, "Abortado");

        system.viewProjects();
        system.close();
    }
}


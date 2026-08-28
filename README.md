# Medicamento

A Java EE (Jakarta EE 10) web application for managing medications, built with **JSF 4 (Jakarta Faces)**, **PrimeFaces**, **WildFly 37**, and **Maven**.

## 📋 Features
- JSF pages with Facelets templating
- PrimeFaces UI components
- Example managed bean (`IndexBean`) with `@ViewScoped` and CDI
- Template-based layout (`template-sistema.xhtml`)
- Basic CSS styling via WebJars and custom stylesheet

## 🚀 Requirements
- **Java 11**
- **Maven 3.8+**
- **WildFly 24** (Jakarta EE 9.1)

## 📦 Project Configuration

This project is configured using Maven. Below are the key configurations from the `pom.xml` file:

### Project Details
- **Group ID**: `br.tec.ici.saude`
- **Artifact ID**: `medicamento`
- **Version**: `0.0.1-SNAPSHOT`
- **Packaging**: `war`
- **Project Name**: `medicamento`
- **Description**: `Projeto medicamento`

### Properties
- **Java Compiler Release**: `11`
- **Source Encoding**: `UTF-8`

### Dependencies
- **Jakarta EE API**: 
  ```xml
  <dependency>
      <groupId>jakarta.platform</groupId>
      <artifactId>jakarta.jakartaee-api</artifactId>
      <version>9.1.0</version>
      <scope>provided</scope>
  </dependency>
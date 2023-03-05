@parallel
Feature: Validar la respuesta de la API Post
  Como usuario
  Quiero validar que la respuesta de la API al realizar un Post se cree de forma exitosa


  Background:
    Given que el usuario realiza una peticion GET a la API y responde docientos

  Scenario:se consume el metodo de post y se crea un nuevo usuario
    Given Se consume la api Post para crear un usuario aleatorio y responde docientos
    Then se valida que el numero de usuario sea superior al anterior y se filtra por su id generado para validar el usuario

  Scenario: se consume el metodo post con un body no valido
    Given se consume la api Post con un body no valido y responde cuatrocientos cuatro
    Then se visualiza el msg de error

  Scenario: se consume el metodo post con un path no valido
    Given se consume la api Post con un path no valido y responde codigo cuatrocientos cuatro
    Then se valida el msg de error para el path no ecnotnrado
@parallel
Feature: Validar la respuesta de la API Update
  Como usuario
  Quiero validar que la respuesta de la API al realizar un Update se cambie la informacion del usuario a actualizar


  Background:
    Given que el usuario realiza una peticion GET a la API y responde docientos

    Scenario: se cambia la informacion de un usuario aleatorio
      Given se consume el servisio de update con un usuario aleatorio y responde docientos
      Then se valida que la informacion cambiada si se aplique
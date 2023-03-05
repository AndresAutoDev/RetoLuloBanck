
@parallel
Feature: Validar la respuesta de la API Delete
  Como usuario
  Quiero validar que la respuesta de la API al realizar un Delete se elimine el usuario de forma satisfactoria


  Background:
    Given que el usuario realiza una peticion GET a la API y responde docientos

    Scenario: Se elimina un usuario aleatorio
      Given se elimina un usuario de forma aleatoria y responde docientos la Api
      Then se valida el id del usuario eliminado y que el numero de usuario registrados halla disminuido en un usuario
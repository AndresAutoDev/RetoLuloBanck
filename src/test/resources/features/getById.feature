@parallel
Feature: Validar la respuesta de la API con id especifico
  Como usuario
  Quiero validar que la respuesta de la API filtrando por un id en especifico sea la correcta

  Background:
    Given que el usuario realiza una peticion GET a la API y responde docientos

  Scenario: se busca un usuario en especifico
    Given Se consume la api Get filtrando por un id aleatorio y responde docientos
    Then se valida que el id del response sea igual al id aleatorio por el que se filtro

    Scenario: se busca por un id no valido
      Given se consume la api get filtrando por un id no valido y reponde codigo cuatrocientos
      Then se valida el msg de error

      Scenario: se busca por un id eliminado
        Given se consume la api de eliminar usuario y se busca el id eliminado
        Then se valida el msg de error de usuario no encontrado
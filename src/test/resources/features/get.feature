@parallel

Feature: Validar la respuesta de la API Get
  Como usuario
  Quiero validar que la respuesta de la API contenga los valores correctos

  Scenario: La respuesta debe contener los valores correctos
    Given que el usuario realiza una peticion GET a la API y responde docientos
    Then la respuesta debe contener el valor de paginas especificadas y del limite

    Scenario: se consume el api get sin el header de app-id
      Given que el usuario realiza una peticion Get sin el header de app-id
      Then se valida el msg de error de app-id missing

  Scenario: se consume el api get con un app-id no valido
    Given que el usuario realiza una peticion Get con un app-id no valido
    Then se valida el msg de error de app-id no existente
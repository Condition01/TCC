var checkout = new DirectCheckout('B2D0B12E73A21E783D130F46AD79A05E5BAC088346EFE8005EF4FEA3636FF41B', false);
  /* Em sandbox utilizar o construtor new DirectCheckout('PUBLIC_TOKEN', false); */            

//4111111111111111 - sucesso VISA
//5362682003164890 - sem saldo
//5555555555554444 - sucesso MASTERCARD

  function generateHash() {
    var cardData = {
        cardNumber: '4111111111111111',
        holderName: 'Bruno Cardoso',
        securityCode: '000',
        expirationMonth: '12',
        expirationYear: '2045'
      };


      checkout.getCardHash(cardData, function(cardHash) {
        /* Sucesso - A variável cardHash conterá o hash do cartão de crédito */
        console.log(cardHash);
    }, function(error) {
        /* Erro - A variável error conterá o erro ocorrido ao obter o hash */
        console.log(error);
    });
  }


 
## Instalando Rabbit-MQ

Primeiramente devemos instalar a ERLang acessando: https://www.erlang.org/news/145 e baixando a versão do nosso SO.
Depois instalar o RABBIT-MQ basta acessar: https://www.rabbitmq.com/install-windows.html#:~:text=Once%20a%20supported%20version%20of,it%20using%20the%20default%20configuration.
baixar o .exe, instalar normalmente. 

Por fim, para usarmos o rabbitmq devemos acessar `<pasta-de-instalação>/sbin` e usarmos o arquivo ***rabbitmqclt.bat***. 

Para visualização do status do nó criado pelo rabbit use o comando: 

```shell
\.rabbitmqctl.bat status
```

Para parar o nó rodando use o comando: 

```shell
\.rabbitmqctl.bat stop
```

Para iniciar o nó use: 

```shell
\.rabbitmqctl.bat start-app
```

Para conseguir usar o console de controle das filas de mensagens pelo browser devemos ativa-lo com o arquivo
***rabbitmq-plugins.bat***. Basta utilizar o comando:

```shell
\rabbitmq-plugins.bat enable rabbitmq_management
```

Em seguida acessar localhost:15672 com o login ***guest*** e senha ***guest***
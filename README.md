Trabalho de Redes 2 // Por Rafael Pinheiro e Vitor Carvalho
Integrantes
● Rafael Pinheiro Jacome de Carvalho, 1010604;
● Vitor Marques Carvalho, 1320692;

Linguagem Utilizada
Foi utilizada a linguagem Java de programação.

Instruções para Execução do Trabalho
Existem duas aplicações, uma para o cliente e outra para o servidor.
Utilizamos a IDE IntelliJ para desenvolver este trabalho. A interface foi feita com Swing.
Caso queira os executáveis, acessar o prompt de comando tendo o diretório raiz do
projeto (ChatClient ou ServerChat) e executar o comando que se encontra no arquivo
pom.xml:
mvn clean compile assembly:single
OBS: Será necessário executar o servidor primeiro para depois usar a aplicação cliente a
fim de se conectar para bate-papo.

Aplicação Servidor
O nome da classe principal é AppServer.java.
Ao executar esta classe, uma janela aparecerá com a lista de usuários conectados (que
será populada conforme os usuários venham a se conectar) com os reus respectivos IDs.
Será possível excluir usuários da sala ao selecionar um item na tabela e apertar o botão
Excluir Usuário.

Aplicação Cliente
O nome da classe principal é AppClient.java.
Ao executar esta classe, informa-se o nome do usuário e o status, depois clica-se em
Conectar. A partir daqui, poderá comunicar-se na sala de bate-papo pública.
Para enviar uma mensagem privada é necessário clicar em um usuário na lista de usuários
e então digitar sua mesagem e clicar em enviar ou apertar a tecla Enter.
O status pode ser alterado a qualquer momento e irá refletir em todos os demais clientes.

Link para Repositório no GitHub
Link: https://github.com/faeljc1/chat-socket.git

Trabalho de Redes 2 // Por Rafael Pinheiro e Vitor Carvalho
Funcionalidades Opcionais
● Interface gráfica para Clientes.
● Interface gráfica para Servidor.
● Exibição de status dos usuários na sala de bate-papo.
# Meetup
## Configurações iniciais

Para rodar esse desafio será necessário  Java 1.8, Maven 3.6, MySQL 8.0 e TomCat 8.5.

Recomendado a utilização de alguma IDE, nesse caso o Eclipse( Pode ser a versão mais atual). 

Com o eclipse aberto você irá importar um novo projeto maven e irá selecionar o diretório desse projeto. O Eclipse irá reconhecer o pom.xml. Após o reconhecimento e so dar finish e o eclipse irá importar o projeto e o maven começará a baixar as dependências necessárias configuradas no pom.xml.

Após importado o projeto a vá para a  classe JPAConfiguration e no método dataSource() possui as configurações de banco. Ajuste para que ele se conecte ao seu banco.

Além disso na mesma classe e no método additionalProperties() tem as configurações do hibernate. Como esse desafio precisa sempre manter os dados estáticos então o hibernate está configurado para toda vez que subir ele irá dropar todas as tabelas e criar novas. Essa configuracao esta na linha 41.
```
properties.setProperty("hibernate.hbm2ddl.auto", "create");
```

Já na classe ServeltSpringMVC tem o metodo onStartup() que seta algumas configurações ao subir a aplicação. Uma delas e o profile.
```
servletContext.setInitParameter("spring.profiles.active", "dev"); 
```
Por default a aplicação está para subir como o profile dev caso queira fazer testes deve trocar para o profile "test".

## Subindo aplicação
No eclipse vai em Window -> Show View -> Other... . Pesquise por server e abra a view de Server.

Na aba do server clique com o botão direito e vai em new -> Server, selecione o TomCat 8.5 e no campo Server runtime environment configure para o diretório onde ele está. Após feito, clique em next e adicione o seu projeto, depois somente clicar em finish.

Feito isso dê um start no seu servidor pela aba do server no eclipse e acesse a página http://localhost:8080/meetup/ quando acessa a página pela primeira vez ele irá fazer a inserção dos dados iniciais.

## Utilizando a aplicação

A aplicação tem a tela de Home onde mostra todos os meetup`s e seu próximo evento. Se um meetup nao tiver um próximo evento com a data maior que a data atual não será evidenciado na home. 

No cabeçalho há a aba de pesquisa onde você pode buscar um meetup por sua cidade evidenciando todos os eventos daquele meetup.

Na card do meetup você pode clicar em saber mais sobre aquele meetup você será redirecionado para uma página com todos os eventos.
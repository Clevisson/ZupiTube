# ZupiTube
Bootcamp_Zup
> Desenvolvimento da api do Youtube em versão MVP(Produto Mínimo Viável)

## Recursos
- [x] [Upload de vídeo em Bucket na aws]
- [x] [Apagar vídeo do Bucket]
- [x] [Obter Url do vídeo ao ser hospedado]
- [ ] [Login, Logout de usuário]
- [ ] [Criar Canal]
- [ ] [Criar comentários no vídeo]


## Instalação

Clone o projeto, faça download e abra no IntelliJ.<br />
## É necessário criar um bucket na aws para testar o código!<br />
## Segue link explicando os passos: https://docs.aws.amazon.com/pt_br/AmazonS3/latest/dev/example-walkthroughs-managing-access-example1.html

## Recursos_Descrição
>Como produto mínimo viável,foi desenvolvida a funcionalidade de hospedar o video.<br /> Através da rota http://localhost:9000/storage/uploadVideo, passando o video na request, com a key "file", ele é enviado para um bucket na aws e retornado sua Url que pode ser acessada para consumo do recurso.<br/>
Os outros recursos como: Usuário, Criar Canal e adicionar comentários no video, estão feitas seus models e Controlers,
mas essas funcionalidades ainda serão desenvolvidas! Aqui, até agora, elas só foram estruturadas. Uma vez que, foi decidido focar em resolver o problema da hospedagem do video.
Que possibilita por exemplo criar videos e servi-los através de sua Url.<br/>
Através da rota http://localhost:9000/storage/deleteVideo é possivel excluir o video hospedado passando sua url.

## Contributing

1. Faça o _fork_ do projeto (<https://github.com/Clevisson/ZupiTube/fork>)
2. Crie uma _branch_ para sua modificação (`git checkout -b feature/fooBar`)
3. Faça o _commit_ (`git commit -am 'Add some fooBar'`)
4. _Push_ (`git push origin feature/fooBar`)
5. Crie um novo Pull Request

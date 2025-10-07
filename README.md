
---

# CAT (Backend)

**Clojure Anonymous Talk (CAT)** é um chat anônimo desenvolvido inteiramente em **Clojure**, com foco em simplicidade, privacidade e comunicação em tempo real.
O projeto foi criado utilizando o **Leiningen** e integra um conjunto robusto de bibliotecas do ecossistema Clojure para oferecer uma aplicação leve, moderna e segura.

---

## Installation

Clone o repositório diretamente do GitHub:

```bash
git clone https://github.com/AntonioV1ctor/cat-backend.git
cd cat-backend
```

---

## Usage

### 1. Inicie o banco de dados

Certifique-se de ter o **Docker** instalado e execute o comando abaixo para iniciar o ambiente de banco de dados:

```bash
docker compose up -d
```

### 2. Rode o projeto

Execute o servidor com o Leiningen:

```bash
lein run
```

Ou, se preferir rodar o JAR gerado:

```bash
java -jar target/uberjar/cat_backend-0.1.0-SNAPSHOT-standalone.jar
```

Após a inicialização, acesse a aplicação em:
**[http://localhost:3000/api/v1/](http://localhost:3000/api/v1/)**

---

## Releases

* Adicionar conexão HTTPS [**False**]
* Modificar a estrutura para o chat ser um WebSocket [**False**]
* Adicionar proxy reverso com **Nginx** [**False**]
* Integrar sistema de análise de qualidade de código (**SonarQube**) [**False**]
* Reorganizar a estrutura do código, separando por responsabilidades e funções [**False**]

---

## License

Copyright © 2025

Este programa e os materiais que o acompanham são disponibilizados sob os termos da
**Eclipse Public License 2.0**, disponível em:
[http://www.eclipse.org/legal/epl-2.0](http://www.eclipse.org/legal/epl-2.0)

O código-fonte também pode estar disponível sob as **Licenças Secundárias** definidas na EPL v2.0, incluindo a
**GNU General Public License**, versão 2, com a exceção **GNU Classpath Exception**, disponível em:
[https://www.gnu.org/software/classpath/license.html](https://www.gnu.org/software/classpath/license.html)

---


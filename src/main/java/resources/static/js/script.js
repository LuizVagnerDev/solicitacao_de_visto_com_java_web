let voltar = document.querySelectorAll('.voltar');
let cadastrar = document.querySelectorAll('.cadastro');
let envio = document.querySelectorAll('.enviar');
let elementos_formulario = document.querySelectorAll('.controle');
let funcionario = document.querySelector('#nome_func');
let funcao = document.querySelector('.funcao');
let cliente = document.querySelector('#nome_do_cliente');
let data_nasc = document.querySelector('#data_nascimento');
let endereco_cliente = document.querySelector('#endereco');
let email_cliente = document.querySelector('#email');
let finalizar_solicitacao = document.querySelector('.finalizar');
let numero_solicitacao = document.querySelector('#num_solicitacao');
let identificacao_cliente = document.querySelector('#id_cliente');
let identificacao_funcionario = document.querySelector('#id_funcionario');
let doc_nao_enviado = document.querySelector('.documento_nao_enviado');
let doc_enviado = document.querySelector('.documento_enviado');


voltar.forEach(botao => {
    botao.addEventListener("click", event => {
        event.preventDefault(); 
        window.location.href = "./index.html";
    });
});

cadastrar.forEach(cadastro =>{
    cadastro.addEventListener('click', event =>{
        event.preventDefault();
    if(event.target.value == "funcionario"){
        window.location.href = "./tela_funcionario.html"; 
    }
    else{
        window.location.href = "./tela_cliente.html";
    }
    })
})

elementos_formulario.forEach(campo_input =>{
    const controle_de_input = campo_input
    controle_de_input.addEventListener('keyup', event =>{
        if(event.target.checkValidity()){
            campo_input.classList.remove('erro');
        }
        else{
            campo_input.classList.add('erro');
        }
    })
})

envio.forEach(cadastrar =>{
    cadastrar.addEventListener('click', event =>{
        event.preventDefault();
        if(cadastrar.value === "enviar_funcionario"){     
            if(funcionario.checkValidity() && funcao.value !== "vazio"){

                const dados = {
                    nome : funcionario.value,
                    cargo : funcao.value,
                };
                fetch('http://localhost:3000/funcionario',{
                    method: 'post',
                    headers:{
                        'Content-type': 'application/json'
                    },
                    body: JSON.stringify(dados)
                })
                .then(response => response.json())
                .then(data =>{
                    alert("Funcionário cadastrado com sucesso")

                    let resposta = confirm("Deseja realizar outro cadastro de funcionário?");
                    if(resposta === false){
                        window.location.href = "./index.html";
                    }
                    else{
                        funcionario.value = "";
                        funcao.value = 'vazio';
                    }
                    
                })
                .catch((error)=>{
                    alert('Erro!', error)
                });
               
            }
            else{
                alert("Os campos em vermelho devem atender aos requisitos, verifique se o cargo está selecionado.")
            }
        }
        else if(cadastrar.value === "enviar_cliente"){
           
            if(cliente.checkValidity() && data_nasc.checkValidity() && endereco_cliente.checkValidity() && email_cliente.checkValidity()){

                const dados_cliente = {
                    nome : cliente.value,
                    data_nascimento : data_nasc.value,
                    endereco : endereco_cliente.value,
                    email : email_cliente.value
                };
                fetch('http://localhost:3000/cliente',{
                    method: 'post',
                    headers:{
                        'Content-type': 'application/json'
                    },
                    body: JSON.stringify(dados_cliente)
                })
                .then(response => response.json())
                .then((data) =>{
                    alert("Cliente cadastrado com sucesso!");
                    let resposta = confirm("Deseja realizar outro cadastro de cliente?");
                if(resposta === false){
                    let resposta2 = confirm("Deseja ir para a página de solicitações?");
                    if(resposta2 === true){
                        window.location.href = "./tela_solicitacao.html";
                    }
                    else{
                        window.location.href = "./index.html";
                        
                    }              
                }
                else{
                    funcionario.value = "";
                    funcao.value = 'vazio';
                }
                })
                .catch((error)=>{
                    alert('Erro!', error)
                });  
               
            }
             else{
                alert("O campo em vermelho devem atender aos requisitos");
            }
        }
    })
})


finalizar_solicitacao.addEventListener('click', event =>{
    event.preventDefault()
    if(numero_solicitacao.checkValidity() && identificacao_cliente.checkValidity() && identificacao_funcionario.checkValidity() && doc_enviado.checked){
        fetch("http://localhost:3000/funcionario")
        .then(response => {
            if (!response.ok) {
                alert('Não há funcionários cadastrados.');
            }
            return response.json(); 
            
        })
        .then(funcionarios => {
            let funcionario_encontrado = false;
            funcionarios.forEach(cadastro => {
                 
                if(cadastro.id == identificacao_funcionario.value){
                    funcionario_encontrado = true;
                    cliente_encontrado = true;
                }
               
            });

           if(!funcionario_encontrado){
            throw new Error("Funcionário não encontrado.")
           }

           return fetch("http://localhost:3000/cliente")
        })
        
        .then(response => {
        if (!response.ok) {
            alert('Não há clientes cadastrados.');
        }
        return response.json(); 
        })

        .then(clientes => {
            let cliente_encontrado = false;
            
            clientes.forEach(cadastro => {
                if (cadastro.id == identificacao_cliente.value) {
                    cliente_encontrado = true;
                }
            });

        if(!cliente_encontrado){
            throw new Error("Cliente não encontrado.")
        }
       
        alert("Solicitaçao enviada com sucesso!");

        let resposta = confirm("Deseja realizar outra solicitação?");
        if(resposta === false){
            window.location.href = "./index.html";
        }else{
            numero_solicitacao.value = "";
            identificacao_cliente.value = "";
            identificacao_funcionario.value ="";
            doc_enviado.checked = false;
        }
           
        })
        .catch(error =>{
            alert("ID do funcionário e/ou do cliente ínválido, por gentileza verifique a informação na lista de cadastros", error)
           })
      
    }    
    else if(numero_solicitacao.checkValidity() && identificacao_cliente.checkValidity() && identificacao_funcionario.checkValidity() && doc_nao_enviado.checked){
        alert("Solicite a documentação necesária ao cliente e escoha a opção (Sim)")

    }
    else{
        alert("Preencha todos os campos seguindo os requisitos.")
    }
    
});

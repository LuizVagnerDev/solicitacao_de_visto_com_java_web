let voltar = document.querySelector('.voltar');


fetch("http://localhost:3000/funcionario")

.then(response => {
    if (!response.ok) {
        throw new Error('Erro na requisição');
    }
    return response.json();
})
.then(data => {
    const newsElement = document.querySelector('#lista_funcionarios');

    data.slice(1).forEach(cadastro => {
        const ul = document.createElement('ul');
        ul.innerHTML = `
            <li>
                <strong>Nome:</strong> ${cadastro.nome} <br>
                <strong>Cargo:</strong> ${cadastro.cargo} <br>
                <strong>ID:</strong> ${cadastro.id} <br>
            </li>
        `;
        newsElement.appendChild(ul);
    });
})
.catch(error => {
    console.error('Erro ao buscar os cadastros:', error);
});

fetch("http://localhost:3000/cliente")

.then(response => {
    if (!response.ok) {
        throw new Error('Erro na requisição');
    }
    return response.json();
})
.then(data => {
    const newsElement = document.querySelector('#lista_clientes');

    data.slice(1).forEach(cadastro => {
        const ul = document.createElement('ul');
        ul.innerHTML = `
            <li>
                <strong>Nome:</strong> ${cadastro.nome} <br>
                <strong>Data de nascimento:</strong> ${cadastro.data_nascimento} <br>
                <strong>Endereço:</strong> ${cadastro.endereco} <br>
                <strong>E-mail:</strong> ${cadastro.email} <br>
                <strong>ID:</strong> ${cadastro.id} <br>
            </li>
            
        `;
        newsElement.appendChild(ul);
    });
})
.catch(error => {
    console.error('Erro ao buscar os cadastros:', error);
});


voltar.addEventListener("click", event => {
    event.preventDefault(); 
    window.location.href = "./index.html";
});

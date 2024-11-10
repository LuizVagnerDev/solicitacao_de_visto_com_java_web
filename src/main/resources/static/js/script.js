let voltar = document.querySelectorAll('.voltar');
let textDanger = document.querySelector('.text-danger');
let botaoFinalizar = document.querySelector('.finalizar');
voltar.forEach(botao => {
    botao.addEventListener("click", event => {
        event.preventDefault(); 
        window.location.href = "./index";
    });
});

if (textDanger) {
    document.addEventListener("DOMContentLoaded", () => {
        Swal.fire({
            title: 'Ops, algo está errado aí!',
            text: 'Verifique se todos os campos atendem os requisitos ',
            icon: 'error',
            confirmButtonText: 'Ok'
        });
    });
}

document.querySelectorAll('.deletar').forEach(botaoDeletar => {
    botaoDeletar.addEventListener('click', event => {
        event.preventDefault();
        let id = botaoDeletar.closest('tr').querySelector('.pegarId').textContent;
        let itemSelecionado = botaoDeletar.getAttribute('data-type');
        
        
        Swal.fire({
            title: 'Deseja realmente deletar?',
            text: 'Esta ação não pode ser desfeita!',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Sim, deletar!'
        }).then((result) => {
            if (result.isConfirmed) {
                if(itemSelecionado === 'funcionario'){
                    `/deletarFuncionario/${id}`;
                    fetch(`/deletarFuncionario/${id}`, { method: 'DELETE' })
                    .then(response => {
                        if (response.ok) {
                            botaoDeletar.closest("tr").remove();
                        }
                        else{
                            Swal.fire({
                                title: 'Existem solicitações vínculadas a esse funcionário.',
                                text: 'Para excluí-lo, primeiro finalize todas as solicitações vinculadas à ele.',
                                icon: 'warning',
                                showCancelButton: true,
                                confirmButtonColor: '#3085d6',
                                cancelButtonColor: '#d33',
                                confirmButtonText: 'Entendi!'
                            });
                        }
                    });
                }
                else if(itemSelecionado === 'cliente'){
                    `/deletarCliente/${id}`;
                    fetch(`/deletarCliente/${id}`, { method: 'DELETE' })
                    .then(response => {
                        if (response.ok) {
                            botaoDeletar.closest("tr").remove();
                        }
                        else{
                            Swal.fire({
                                title: 'Existem solicitações vínculadas a esse cliente.',
                                text: 'Para excluí-lo, primeiro finalize todas as solicitações vinculadas à ele.',
                                icon: 'warning',
                                showCancelButton: true,
                                confirmButtonColor: '#3085d6',
                                cancelButtonColor: '#d33',
                                confirmButtonText: 'Entendi!'
                            });
                        }
                    });
                }
            }
        });
    });
});

document.querySelectorAll('.atualizar').forEach(botaoAtualizar => {
    botaoAtualizar.addEventListener('click', event => {
        event.preventDefault();
        let id = botaoAtualizar.closest('tr').querySelector('.pegarId').textContent;
        let itemSelecionado = botaoAtualizar.getAttribute('data-type');
        
        Swal.fire({
            title: 'Você deseja atualizar as informações?',
            icon: 'question',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Sim, atualizar!'
        }).then((result) => {
            if (result.isConfirmed) {
                if(itemSelecionado === 'funcionario'){
                    window.location.href = `/atualizarFuncionario/${id}`;
                }
                else if(itemSelecionado === 'cliente'){
                    window.location.href = `/atualizarCliente/${id}`;
                }
                else{
                    window.location.href = `/atualizarSolicitacao/${id}`;
                }
            }
           
        });
    });
});

botaoFinalizar.addEventListener('click', event => {
    event.preventDefault();
    let id = botaoFinalizar.closest('tr').querySelector('.pegarId').textContent;
    let itemSelecionado = botaoFinalizar.getAttribute('data-type');
    
    Swal.fire({
        title: 'Toda a documentação do cliente foi entregue?',
        text: 'verifique se o cliente já está com toda a documentação antes de continuar!',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Sim, o processo já foi finalizado!'
    }).then((result) => {
        if (result.isConfirmed) {
            if(itemSelecionado === 'solicitacao'){
                `/deletarSolicitacao/${id}`;
                fetch(`/deletarSolicitacao/${id}`, { method: 'DELETE' })
                        .then(response => {
                            if (response.ok) {
                                botaoFinalizar.closest("tr").remove();
                    }
                });
            }
        }
    });
                
});
       
        

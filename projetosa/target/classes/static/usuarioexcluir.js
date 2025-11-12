
// Adicione um ouvinte de eventos aos botões de exclusão
document.querySelectorAll('.excluir').forEach(function(button) {
    button.addEventListener('click',
    function() {
        if (confirm('Confirma a exclusão do usuário?')) {

            const row = this.closest('tr'); // Obtém a linha atual da tabela

            const medicoId = this.dataset.medicoId;

            // Realize a chamada AJAX para excluir o recurso
            fetch(`/usuario/${usuarioId}`, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                },
            })
            .then(response => {
                if (response.ok) {
                    // A exclusão foi bem-sucedida
                    console.log('Usuário excluída com sucesso.');

                    // Remove a linha da tabela após a exclusão
                    row.remove();
                } else {
                    // A solicitação DELETE falhou
                    console.error('Erro ao excluir Usuário.');
                }
            })
            .catch(error => {
                // Lidar com erros de rede ou outros erros
                console.error('Erro de rede:', error);
            });
        }
    });
});

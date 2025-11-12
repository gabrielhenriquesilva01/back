document.querySelectorAll('.excluir').forEach(function(button) {
    button.addEventListener('click',
    function() {
        if (confirm('Confirma a exclusão do usuário?')) {

            const row = this.closest('tr');

            const medicoId = this.dataset.medicoId;

            fetch(`/usuario/${usuarioId}`, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                },
            })
            .then(response => {
                if (response.ok) {
                    console.log('Usuário excluído com sucesso!');

                    row.remove();
                } else {
                    console.error('Erro ao excluir usuário.');
                }
            })
            .catch(error => {
                console.error('Erro de rede:', error);
            });
        }
    });
});

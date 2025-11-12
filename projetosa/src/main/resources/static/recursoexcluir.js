document.querySelectorAll('.excluir').forEach(function(button) {
    button.addEventListener('click',
        function() {
            if (confirm('Confirma a exclusão do recurso?')) {

                const row = this.closest('tr');

                const medicoId = this.dataset.recursoId;

                fetch(`/recurso/${recursoId}`, {
                    method: 'DELETE',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                })
                    .then(response => {
                        if (response.ok) {
                            console.log('Recurso excluído com sucesso!');

                            row.remove();
                        } else {
                            console.error('Erro ao excluir recurso.');
                        }
                    })
                    .catch(error => {
                        console.error('Erro de rede:', error);
                    });
            }
        });
});

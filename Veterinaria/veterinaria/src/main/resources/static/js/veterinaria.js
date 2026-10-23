document.addEventListener('DOMContentLoaded', function () {
    const modalEliminar = document.getElementById('modalConfirmarEliminar');
    if (modalEliminar) {
        modalEliminar.addEventListener('show.bs.modal', function (evento) {
            const boton = evento.relatedTarget;
            document.getElementById('modalEliminarId').value = boton.getAttribute('data-id');
            document.getElementById('modalEliminarNombre').textContent = boton.getAttribute('data-nombre');
        });
    }

    document.querySelectorAll('.toast').forEach(function (aviso) {
        bootstrap.Toast.getOrCreateInstance(aviso, {delay: 5000}).show();
    });
});

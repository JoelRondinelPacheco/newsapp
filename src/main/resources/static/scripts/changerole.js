const d = document
const $modalBody = d.getElementById('modal-body')
const $options = d.querySelectorAll('.btn-modal')
const $name = d.getElementById('modal-name-email')
const $role = d.getElementById('modal-role')
const $closeBtn = d.getElementById('btn-modal-close')
const $rolesList = d.getElementById('roles-list')
const $roleDefault = d.getElementById('role-default')


for (let i = 0; i < $options.length; i++) {
    $options[i].addEventListener("click", function (e) {
        $name.innerText = e.currentTarget.getAttribute('data-name')
        $role.innerText = `${e.currentTarget.getAttribute('data-role')} ->`
        $rolesList.innerHTML = `<select name="roles">
        <option value="empty" id="role-default" selected>Nuevo Rol</option>
        <option class="modal-open" value="Usuario">Cliente</option>
        <option class="modal-open" value="Periodista">Periodista</option>
        <option class="modal-open" value="Moderador">Moderador</option>
        <option class="modal-open" value="Adminstrador">Adminstrador</option>
    </select>`

    
    })
}

$closeBtn.addEventListener("click", function() {
    $name.innerText = ''
    $role.innerText = ''
    $rolesList.innerHTML = ''


})


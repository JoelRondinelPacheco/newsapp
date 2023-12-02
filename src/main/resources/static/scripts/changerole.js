const d = document
const $modalBody = d.getElementById('modal-body')
const $editBtn = d.querySelectorAll('.btn-modal')
const $name = d.getElementById('moda-user-name')
const $email = d.getElementById('modal-user-email')
const $role = d.getElementById('modal-current-rol')
const $closeBtn = d.getElementById('btn-modal-close')
const $rolesList = d.getElementById('roles-list')
const $roleDefault = d.getElementById('role-default')

const rolesUrl = "http://localhost:8080/utils/roles"
var roles;

const toPascalCase = str =>
  str
    .match(/[A-Z]{2,}(?=[A-Z][a-z]+[0-9]*|\b)|[A-Z]?[a-z]+[0-9]*|[A-Z]|[0-9]+/g)
    .map(x => x.charAt(0).toUpperCase() + x.slice(1).toLowerCase())
    .join('');


async function roles (url) {
    try {
    const res = await fetch(url)
    const json = await res.json();
    console.log(json)
    roles = json
    } catch (err) {
        console.log("Error: " + err);
    }
}

roles(rolesUrl)

$editBtn.forEach(btn => {
    btn.addEventListener("click", (e) => {
        $name.innerHTML = e.currentTarget.getAttribute('data-name')
        $email.innerHTML = e.currentTarget.getAttribute('data-email')
        $role.innerHTML = toPascalCase(e.currentTarget.getAttribute('data-role'))

    })
})


$closeBtn.addEventListener("click", function() {
    $name.innerText = ''
    $role.innerText = ''
    $rolesList.innerHTML = ''


})

